package org.usfirst.frc2832.Robot_2015.commands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.DriveEncoders;
import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Contains a variety of debugging functions. 
 *Mostly affects the SmartDashboard.
 */
public class  DebugHardware extends Command {
	int count = 0;
	double speed = 200;
	
	private SendableChooser motorSelector;
	private ArrayList<Double> initialEncoderValues;
	

	/**
	 *Contains a variety of debugging functions. 
	 *Mostly affects the SmartDashboard.
	 */
    public DebugHardware() {
    	motorSelector = new SendableChooser();
    	motorSelector.addDefault("None", 0);
    	motorSelector.addObject("Left Front", 1);
    	motorSelector.addObject("Right Front", 2);
    	motorSelector.addObject("Right Rear", 3);
    	motorSelector.addObject("Left Rear", 4);
    	motorSelector.addObject("Winch", 5);
    	motorSelector.addObject("Left Roller", 6);
    	motorSelector.addObject("Right Roller", 7);
    	SmartDashboard.putData("Debug Motor", motorSelector);
    	
    	Preferences.getInstance().putDouble("setWinch",RobotMap.forkliftMotor.getPosition());
    	
    	Preferences.getInstance().putDouble("WheelSpeed", speed);
    	RobotMap.forkliftMotor.enableControl();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialEncoderValues = DriveEncoders.getInitialEncoderValues();
    	//SmartDashboard.putNumber("Debug_Count", ++count);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Preferences.getInstance().putDouble("AveragePressure", RobotMap.pneumaticsPressure.getAverageValue());
    	Preferences.getInstance().putDouble("Pressure", RobotMap.pneumaticsPressure.getValue());
    	Preferences.getInstance().putBoolean("LiftLimSwitchFwd", RobotMap.forkliftMotor.isFwdLimitSwitchClosed());
    	Preferences.getInstance().putBoolean("LiftLimSwitchRev", RobotMap.forkliftMotor.isRevLimitSwitchClosed());
    	
    	double dist = DriveEncoders.getEncoderDistance(initialEncoderValues);
    	
    	Preferences.getInstance().putDouble("SensorDistance", dist);
    	Preferences.getInstance().putDouble("LFPosition", RobotMap.driveBaseLeftFrontMotor .getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("LFVelocity", RobotMap.driveBaseLeftFrontMotor .getEncVelocity() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("LRPosition", RobotMap.driveBaseLeftRearMotor  .getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("LRVelocity", RobotMap.driveBaseLeftRearMotor  .getEncVelocity() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("RFPosition", RobotMap.driveBaseRightFrontMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("RFVelocity", RobotMap.driveBaseRightFrontMotor.getEncVelocity() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("RRPosition", RobotMap.driveBaseRightRearMotor .getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER);
    	Preferences.getInstance().putDouble("RRVelocity", RobotMap.driveBaseRightRearMotor .getEncVelocity() / RobotMap.ENCODER_PULSE_PER_METER); 	
    	Preferences.getInstance().putDouble("WinchPosition", RobotMap.forkliftMotor.getPosition());
    	Preferences.getInstance().putDouble("WinchPosRaw", RobotMap.forkliftMotor.getEncPosition());
    	
    	for(int i = 0; i < 8; i++)
    		Preferences.getInstance().putDouble("Current" + i, RobotMap.pdp.getCurrent(i));
    	
    	speed = Preferences.getInstance().getDouble("WheelSpeed", 200);
    	
    	//RobotMap.forkliftMotor.set(SmartDashboard.getNumber("setWinch"));
    	
    	// Run selected motor
    	switch ((Integer) motorSelector.getSelected()) {
    	case 1:
    		RobotMap.driveBaseLeftFrontMotor.set(speed);
    		break;
    	case 2:
    		RobotMap.driveBaseRightFrontMotor.set(speed);
    		break;
    	case 3:
    		RobotMap.driveBaseRightRearMotor.set(speed);
    		break;
    	case 4:
    		RobotMap.driveBaseLeftRearMotor.set(speed);
    		break;
    	case 5:
    		//RobotMap.forkliftMotor.set(speed);
    		break;
    	case 6:
    		RobotMap.leftIngestor.set(speed);
    		break;
    	case 7:
    		RobotMap.rightIngestor.set(speed);
    		break;
    	default:
    	}
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//SmartDashboard.putNumber("Debug_Count", -1);
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	speed = Preferences.getInstance().getDouble("WheelSpeed", 0);
    	
    	RobotMap.driveBaseLeftFrontMotor.set(0);
    	RobotMap.driveBaseLeftRearMotor.set(0);
    	RobotMap.driveBaseRightFrontMotor.set(0);
    	RobotMap.driveBaseRightRearMotor.set(0);
    	RobotMap.leftIngestor.set(0);
    	RobotMap.rightIngestor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	speed = Preferences.getInstance().getDouble("WheelSpeed", 0);
    	
    	RobotMap.driveBaseLeftFrontMotor.set(0);
    	RobotMap.driveBaseLeftRearMotor.set(0);
    	RobotMap.driveBaseRightFrontMotor.set(0);
    	RobotMap.driveBaseRightRearMotor.set(0);
    	RobotMap.leftIngestor.set(0);
    	RobotMap.rightIngestor.set(0);
    	

    }
}
