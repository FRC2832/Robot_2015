package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.CANTalonRobotDrive;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Robot-oriented mecanum movement. Requires Robot.driveBase
 */
public class  MecanumROD extends Command {
	
	public double prevXStrafe = 0;
	public double prevYStrafe = 0;
	public double gain = 0.225;
	
	public MecanumROD() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Zero the robot drive for driving straight
    	((CANTalonRobotDrive) RobotMap.driveBaseDriveTrain).zero();
    	Preferences.getInstance().putDouble("gain", 0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	for(int i = 0; i < 6; i ++)
    		Preferences.getInstance().putDouble("JoystickAxis" + i, Robot.oi.joystick.getRawAxis(i));
    		
    	double maxSpeed = 2;
    	double conv = RobotMap.DRIVE_TRAIN_CONVERSION * maxSpeed;
    	
    	double xStrafe = conv * Robot.oi.joystick.getRawAxis(0);
    	double yStrafe = conv * Robot.oi.joystick.getRawAxis(1);
    	double spin = conv * Robot.oi.joystick.getRawAxis(4);
    	
    	
    		xStrafe = prevXStrafe * (1 - gain) + xStrafe * gain;
    		yStrafe = prevYStrafe * (1 - gain) + yStrafe * gain;
    		
    	
    	RobotMap.driveBaseDriveTrain.mecanumDrive_Cartesian(
        			xStrafe, yStrafe, spin, 0);
    	
    	prevXStrafe = xStrafe;
    	prevYStrafe = yStrafe;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
