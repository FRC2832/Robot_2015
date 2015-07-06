package org.usfirst.frc2832.Robot_2015.commands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.DriveEncoders;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveWithRecklessDisregard extends Command
{

	
	private ArrayList<Double> initialEncoderVals;
	private double angle;
	private double dist;
	
	//Documentation is for CHUMPS
	public MoveWithRecklessDisregard(double dist, double angle)
	{
		this.angle = angle;
		this.dist = dist;
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		initialEncoderVals = DriveEncoders.getInitialEncoderValues();
		
	}

	@Override
	protected void execute() {
		RobotMap.driveBaseDriveTrain.mecanumDrive_Cartesian(1, 0, angle, RobotMap.imu.getYaw());
		
	}

	@Override
	protected boolean isFinished() {
		double _dist = DriveEncoders.getEncoderDistance(initialEncoderVals);
    	
    	
    	// Within 0.15 meters
    	//MUST BE GREATER THAN .12!
    	return (Math.abs(_dist - dist) < 0.15);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
