package org.usfirst.frc2832.Robot_2015.commands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.DriveEncoders;
import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.TrajectoryController;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Command to move forward a specific distance at a specific speed.
 */
public class  MoveDistance extends Command {
	private ArrayList<Double> initialEncoderVals;
	private double initialAngle;
	
	private TrajectoryController tc;
	private double target;
	private long endTime;
	
	/**
	 * Constructs a new MoveDistance command.
	 * @param forwardDistance Distance to go forwards; Relative; not field-centric
	 * @param speed The speed to travel, from 0.1 to 1.0.
	 */
    public MoveDistance(double forwardDistance, double speed) {
    	target = forwardDistance;
    	tc = new TrajectoryController(forwardDistance, speed / 2, 0.2, speed, 2, -.8);
    	endTime = System.currentTimeMillis() + 5000;
    }
    
    protected void initialize() {
    	initialAngle = RobotMap.imu.getYaw();
    	initialEncoderVals = DriveEncoders.getInitialEncoderValues();
    }
    
    protected void execute() {
    	double dist = DriveEncoders.getEncoderDistance(initialEncoderVals);
    	double speed = tc.get(dist);
    	
    	Preferences.getInstance().putDouble("MoveDistanceDist", dist);
    	Preferences.getInstance().putDouble("MoveDistanceSpeed", speed);
    	Preferences.getInstance().putDouble("MoveDistanceTarget", target);
    	
    	// 2 m/s
    	double conv = 2 * RobotMap.DRIVE_TRAIN_CONVERSION;
    	
    	RobotMap.driveBaseDriveTrain.mecanumDrive_Cartesian(
				0.0, -speed * conv, 0.0, RobotMap.imu.getYaw() - initialAngle);
    }

    protected boolean isFinished() {
    	double dist = DriveEncoders.getEncoderDistance(initialEncoderVals);
    	
    	
    	// Within 0.15 meters
    	//MUST BE GREATER THAN .12!
    	return (Math.abs(dist - target) < 0.15); //|| (System.currentTimeMillis() >= endTime);
    }

	@Override
	protected void end() {
		
	}
	@Override
	protected void interrupted() {
		
	}
}
