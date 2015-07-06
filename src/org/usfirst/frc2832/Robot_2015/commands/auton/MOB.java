package org.usfirst.frc2832.Robot_2015.commands.auton;

import org.usfirst.frc2832.Robot_2015.commands.CClampsForward;
import org.usfirst.frc2832.Robot_2015.commands.MoveWithRecklessDisregard;

import edu.wpi.first.wpilibj.command.CommandGroup;
	
public class MOB extends CommandGroup
{
	public MOB()
	{
		addParallel(new CClampsForward());
		addParallel(new MoveWithRecklessDisregard(2.2,0));
		addSequential(new MoveWithRecklessDisregard(-2.2, 0));
	}
}
