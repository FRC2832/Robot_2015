//This Command Closes the Ingestor

package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Closes the ingestor using pneumatics. Ends immediately.
 */
public class  CloseIngestor extends Command  implements Instruction {
	/**
	 *Closes the ingestor using pneumatics. Ends immediately.
	 */
    public CloseIngestor() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.pneumaticsSolenoidRight.set(false);
    	RobotMap.pneumaticsSolenoidLeft.set(true);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
}
}
