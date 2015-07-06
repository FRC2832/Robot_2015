//This Command Rotates the C-clamps in Reverse

package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Reverses the C-Clamps to a perpandicular position using pneumatics. Ends immediately.
 */
public class  CClampsReverse extends Command {
	double endTime = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!RobotMap.pneumaticsSolenoidRight.get()) { //if the ingestor is closed
    		RobotMap.pneumaticsSolenoidRight.set(true); 
    		RobotMap.pneumaticsSolenoidLeft.set(false);//open the ingestors
    		endTime = System.currentTimeMillis() + 500;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (System.currentTimeMillis() >= endTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.pneumaticsDoubleSolenoidCClamps.set(DoubleSolenoid.Value.kReverse);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.pneumaticsDoubleSolenoidCClamps.set(DoubleSolenoid.Value.kOff);
    }
}
