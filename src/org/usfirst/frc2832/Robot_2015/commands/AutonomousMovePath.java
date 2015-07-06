package org.usfirst.frc2832.Robot_2015.commands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.FieldLocation;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMovePath extends CommandGroup {
	
	ArrayList<Instruction> path;
	
	public AutonomousMovePath(ArrayList<Instruction> path) {
		this.path = path;
		FieldLocation currentPosition = null;
		
		for(Instruction inst : path) {	
			
			if(inst instanceof ParallelCommand)
			{
				ParallelCommand pc = (ParallelCommand) inst;
				if(pc.isLoc)
				{
					//do same code as below using location in pc
					if (currentPosition != null) {
						double dist = currentPosition.distanceTo(pc.loc);
						addParallel(new MoveDirection(currentPosition, 
								pc.loc, 0.45, Math.min(dist * 0.5, 0.7)));
					}
					currentPosition = pc.loc;
				}
				else //just run command
				addParallel(pc.command);
			}
			
			
			// Move to next location
			if (inst instanceof FieldLocation) {
				if (currentPosition != null) {
					double dist = currentPosition.distanceTo((FieldLocation) inst);
					addSequential(new MoveDirection(currentPosition, 
							(FieldLocation) inst, 0.45, Math.min(dist * 0.5, 0.7)));
				}
				
				currentPosition = (FieldLocation) inst;
			}
			
			if (inst instanceof Command) {
				addSequential((Command) inst);
			}
    		
		}
	}
	
	@Override
	protected void initialize() {
		
	}
}
