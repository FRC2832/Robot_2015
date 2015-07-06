package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.FieldLocation;

import edu.wpi.first.wpilibj.command.Command;

public class ParallelCommand implements Instruction 
{
	public Command command;
	public FieldLocation loc;
	public boolean isLoc = false;
	
	public ParallelCommand(Command c)
	{
		command = c;
	}
	public ParallelCommand(FieldLocation f)
	{
		isLoc = true;
		loc = f;
	}
}
