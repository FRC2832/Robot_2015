package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.commands.auton.Global;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Toggles the ingestors between an open and closed position
 */
public class ToggleIngestors extends Command implements Instruction {

	double endTime = 0;
	private boolean sentinel = false;
	private boolean otherSentinel = false;
	@Override
	protected void initialize() {
		if (RobotMap.pneumaticsSolenoidRight.get() == false) {
			if (RobotMap.pneumaticsDoubleSolenoidCClamps.get() == DoubleSolenoid.Value.kReverse) {
				RobotMap.pneumaticsDoubleSolenoidCClamps.set(DoubleSolenoid.Value.kForward);
				endTime = System.currentTimeMillis() + 500;
				sentinel = true;
			} else 
				RobotMap.pneumaticsSolenoidRight.set(true);
				RobotMap.pneumaticsSolenoidLeft.set(false);
		} else {
			if (RobotMap.pneumaticsDoubleSolenoidCClamps.get() == DoubleSolenoid.Value.kReverse) {
				RobotMap.pneumaticsDoubleSolenoidCClamps.set(DoubleSolenoid.Value.kForward);
				endTime = System.currentTimeMillis() + 500;
				otherSentinel = true;
			} else 
				RobotMap.pneumaticsSolenoidRight.set(false);
				RobotMap.pneumaticsSolenoidLeft.set(true);
		}
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() >= endTime);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		if (sentinel) {
			RobotMap.pneumaticsSolenoidRight.set(true);
			RobotMap.pneumaticsSolenoidLeft.set(false);
			sentinel = false;
		} else if (otherSentinel) {
			RobotMap.pneumaticsSolenoidRight.set(false);
			RobotMap.pneumaticsSolenoidLeft.set(true);
			otherSentinel = false;
		}
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
