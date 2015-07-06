package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.FieldLocation;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.commands.AutonomousMovePath;
import org.usfirst.frc2832.Robot_2015.commands.CloseFork;
import org.usfirst.frc2832.Robot_2015.commands.Delay;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.ParallelCommand;
import org.usfirst.frc2832.Robot_2015.commands.SetForkPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Auton_Test extends Command implements Pathable
{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Scheduler.getInstance().add(new AutonomousMovePath(getPath()));
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Instruction> getPath() {
		ArrayList<Instruction> path = new ArrayList<>();  
		
			path.add(Robot.startPosition);
			path.add((Instruction)new ParallelCommand(Robot.endPosition));
			path.add((Instruction) new ParallelCommand(new SetForkPosition(0.8)));
			path.add((Instruction) new Delay(1));
			path.add((Instruction) new Delay(1));
			path.add((Instruction) new ParallelCommand(new CloseFork()));
			path.add((Instruction) new ParallelCommand(new SetForkPosition(0.1)));
		
		return path;
	}
	

}
