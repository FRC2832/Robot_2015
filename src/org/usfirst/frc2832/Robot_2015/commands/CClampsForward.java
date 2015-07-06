//This Command Rotates the C-clamps forward

package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Rotates the C-Clamps forward to a parralel position using pneumatics. Ends immediately.
 */
public class  CClampsForward extends Command {
	

	
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.pneumaticsDoubleSolenoidCClamps.set(DoubleSolenoid.Value.kForward);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.pneumaticsDoubleSolenoidCClamps.set(DoubleSolenoid.Value.kOff);
    }
}
