package org.usfirst.frc2832.Robot_2015;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tInstances;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CANTalonRobotDrive extends RobotDrive {
	
	private PIDController pid = null;
	
	private double shiftVal = 0.0;
	
	private class RotationShifter implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			shiftVal = output;
			
	        SmartDashboard.putNumber("Rotation Correction", output);

		}
	}
	
	public CANTalonRobotDrive(SpeedController frontLeftMotor,
			SpeedController rearLeftMotor, 
			SpeedController frontRightMotor,
			SpeedController rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	}
	
	public void zero() {
		if (pid != null)
			pid.setSetpoint(RobotMap.imu.getYaw());
	}
	
	public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle) {
		if (pid == null) {
			pid = new PIDController(10.0, 0.0, 0.0, RobotMap.imu, new RotationShifter());
			
			pid.setContinuous(true);
			pid.setInputRange(-180, 180);
			pid.setOutputRange(-0.8 * RobotMap.ENCODER_PULSE_PER_METER, 0.8 * RobotMap.ENCODER_PULSE_PER_METER);
			
			zero();
			
			pid.enable();
		}
		
		
        if(!kMecanumCartesian_Reported) {
            UsageReporting.report(tResourceType.kResourceType_RobotDrive, getNumMotors(), tInstances.kRobotDrive_MecanumCartesian);
            kMecanumCartesian_Reported = true;
        }
        double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compensate for gyro angle.
        double rotated[] = rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];
        
        SmartDashboard.putNumber("Rotation", rotation);
        
        if (Math.abs(rotation) < 0.01) {
        	// Not rotating; our target heading needs to be kept
        	rotation = shiftVal;
        } else {
        	// Turning; update setpoint
        	SmartDashboard.putNumber("Start Heading", RobotMap.imu.getYaw());
        	pid.setSetpoint(RobotMap.imu.getYaw());
        }
        
        double wheelSpeeds[] = new double[kMaxNumberOfMotors];
        wheelSpeeds[MotorType.kFrontLeft.value] = xIn + yIn + rotation;
        wheelSpeeds[MotorType.kFrontRight.value] = -xIn + yIn - rotation;
        wheelSpeeds[MotorType.kRearLeft.value] = -xIn + yIn + rotation;
        wheelSpeeds[MotorType.kRearRight.value] = xIn + yIn - rotation;

        m_frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * m_invertedMotors[MotorType.kFrontLeft.value] * m_maxOutput, m_syncGroup);
        m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * m_invertedMotors[MotorType.kFrontRight.value] * m_maxOutput, m_syncGroup);
        m_rearLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * m_invertedMotors[MotorType.kRearLeft.value] * m_maxOutput, m_syncGroup);
        m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * m_invertedMotors[MotorType.kRearRight.value] * m_maxOutput, m_syncGroup);

        if (m_syncGroup != 0) {
            CANJaguar.updateSyncGroup(m_syncGroup);
        }

        if (m_safetyHelper != null) m_safetyHelper.feed();
    }

}
