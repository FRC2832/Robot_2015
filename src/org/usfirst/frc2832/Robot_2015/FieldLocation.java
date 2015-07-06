package org.usfirst.frc2832.Robot_2015;

import org.usfirst.frc2832.Robot_2015.commands.Instruction;

public class FieldLocation implements Instruction {
	
	public double x,y,heading; 
	//+x = downfield, +y = to the right
	//Field grid coords are by meter
	//Ends up being ? wide and ? long
	//Rotationally consistent (i.e., same directions depending on field start
	public FieldLocation(double x, double y, double heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}
	
	public double distanceTo(FieldLocation other) {
		return (Math.sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y)));
	}
	
}
