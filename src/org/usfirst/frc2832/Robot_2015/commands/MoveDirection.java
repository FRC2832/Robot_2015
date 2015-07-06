package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.FieldLocation;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the robot in a direction; it turns the robot a certain
 * direction, and then moves forward.  Distance may be given either
 * in direction change and distance, or x and y components relative.
 * @author Brendan
 *
 */
public class MoveDirection extends CommandGroup {
	
	/**
	 * 
	 * @param angle The degrees to turn; positive is clockwise
	 * @param distance The distance to go after the turn, in meters
	 */
	public MoveDirection(double angle, double distance, double rotateSpeed, double driveSpeed) {
		addCommands(angle, distance, rotateSpeed, driveSpeed, angle, 0);
	}
	
	/**
	 * @param start The degrees to turn; positive is clockwise
	 * @param end The distance to go after the turn, in meters
	 */
	public MoveDirection(FieldLocation start, FieldLocation end, double rotateSpeed, double driveSpeed) {
		double dx = end.x - start.x;
		double dy = end.y - start.y;
		
		double angle = Math.atan2(dy, dx) / Math.PI * 180;
		
		double distance = Math.sqrt(dx * dx + dy * dy);
		
		// Instead of 180 degree turn, just go backwards
		if (180 - Math.abs(angle) < 1.0) {
			distance *= -1;
			angle = 0;
		}
			
		addCommands(angle, distance, rotateSpeed, driveSpeed, start.heading, end.heading);
	}
	
	private void addCommands(double angle, double distance, double rotateSpeed, double driveSpeed, double startAngle, double endAngle) {
		// 5.0s timeout
		double turnAngle1 = angle - startAngle;
		double turnAngle2 = endAngle - angle;
		
		if(Math.abs(turnAngle1) > 2) {
			addSequential(new Rotate(turnAngle1, rotateSpeed), 3.0);
			addSequential(new Delay(0.1));
		}
		
		if(Math.abs(distance) > 0.1) {
			addSequential(new MoveDistance(distance, driveSpeed), 4.0);
			addSequential(new Delay(0.1));
		}
			
		if(Math.abs(turnAngle1) > 2) 
			addSequential(new Rotate(turnAngle2, rotateSpeed), 3.0);
	}
}
