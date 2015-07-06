package org.usfirst.frc2832.Robot_2015.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc2832.Robot_2015.DriveEncoders;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestHardware extends Command {

	int step = -1;
	boolean isFinished = false;
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("testButton", false);
		SmartDashboard.putString("testOutput:","");
		SmartDashboard.putNumber("testVal", 0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		boolean isPressed = SmartDashboard.getBoolean("testButton");
		SmartDashboard.putNumber("testVal", 0);
		switch(step)
		{
		
		case -1: //precaution
			SmartDashboard.putString("testOutput:", "Put robot on blocks please.");
			if(isPressed != Boolean.FALSE.booleanValue()) reset();
			break;
			
		case 0: //leftfront
			SmartDashboard.putString("testOutput:", "Running left front motor fwd.");
			RobotMap.driveBaseLeftFrontMotor.set(0.5);
			SmartDashboard.putNumber("testVal", RobotMap.driveBaseLeftFrontMotor.getPosition());
			if(isPressed)
			{
				RobotMap.driveBaseLeftFrontMotor.set(0);
				reset();
			}
			break;
		
		case 1: //leftrear
			SmartDashboard.putString("testOutput:", "Running left rear motor fwd.");
			RobotMap.driveBaseLeftRearMotor.set(0.5);
			SmartDashboard.putNumber("testVal", RobotMap.driveBaseLeftRearMotor.getPosition());
			if(isPressed)
			{
				RobotMap.driveBaseLeftRearMotor.set(0);
				reset();
			}
			break;
			
		case 2: //rightfront
			SmartDashboard.putString("testOutput:", "Running right front motor fwd.");
			RobotMap.driveBaseRightFrontMotor.set(0.5);
			SmartDashboard.putNumber("testVal", RobotMap.driveBaseRightFrontMotor.getPosition());
			if(isPressed)
			{
				RobotMap.driveBaseRightFrontMotor.set(0);
				reset();
			}
			break;
			
		case 3: //rightrear
			SmartDashboard.putString("testOutput:", "Running right rear motor fwd.");
			RobotMap.driveBaseRightRearMotor.set(0.5);
			SmartDashboard.putNumber("testVal", RobotMap.driveBaseRightRearMotor.getPosition());
			if(isPressed)
			{
				RobotMap.driveBaseRightRearMotor.set(0);
				reset();
			}
			break;
		
		case 4: //all wheels
			ArrayList<Double> vals = new ArrayList<>();
			
			vals.add(  (RobotMap.driveBaseLeftFrontMotor.getEncPosition()  / RobotMap.ENCODER_PULSE_PER_METER));  
			vals.add(  (RobotMap.driveBaseLeftRearMotor.getEncPosition()   / RobotMap.ENCODER_PULSE_PER_METER));  
			vals.add(-((RobotMap.driveBaseRightFrontMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER))); 
			vals.add(-((RobotMap.driveBaseRightRearMotor.getEncPosition()  / RobotMap.ENCODER_PULSE_PER_METER))); 
			Collections.sort(vals);
			
			SmartDashboard.putString("testOutput:", "Running all wheels fwd.\n"
					+ "shortest encoder: " + vals.get(0) 
					+  "\tfarthest encoder: " + vals.get(3));
			break;
		
		case 5: //raise lift
			Scheduler.getInstance().add(new SetForkPosition(0.5));
			SmartDashboard.putString("testOutput:", "Moving lift to 0.5 meters.");
			if(isPressed) reset();
			break;
		
		case 6: //lower lift
			Scheduler.getInstance().add(new SetForkPosition(0));
			SmartDashboard.putString("testOutput:", "Moving lift to 0 meters.");
			if(isPressed) reset();
			break;
		
		case 7: //clamps
			Scheduler.getInstance().add(new CClampsForward());
			SmartDashboard.putString("testOutput:", "Rotating clamp fwd.");
			if(isPressed) reset();
			break;
		
		case 8: //clamps
			Scheduler.getInstance().add(new CClampsReverse());
			SmartDashboard.putString("testOutput:", "Rotating clamp back.");
			if(isPressed) reset();
			break;	
			
		default:
			SmartDashboard.putString("testOutput:", "Done :)");
			isFinished = true;
			break;
		
		
		
		}

	}
	
	private void reset()
	{
		step++;
		SmartDashboard.putBoolean("testButton",false);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
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
