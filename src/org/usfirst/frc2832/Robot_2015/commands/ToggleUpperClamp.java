package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.commands.auton.Global;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Toggles the upper clamp between an open and closed position
 */
public class ToggleUpperClamp extends Command implements Instruction {

	@Override
	protected void initialize() 
	{
		if (RobotMap.pneumaticsDoubleSolenoidUpperClamp.get() == Value.kReverse) 
		{
			RobotMap.pneumaticsDoubleSolenoidUpperClamp.set( DoubleSolenoid.Value.kForward );
		} 
		else 
		{
			RobotMap.pneumaticsDoubleSolenoidUpperClamp.set( DoubleSolenoid.Value.kReverse );
		}
	}

	@Override
	protected void execute() {		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
