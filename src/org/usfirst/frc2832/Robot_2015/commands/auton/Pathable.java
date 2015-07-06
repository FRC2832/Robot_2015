package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.commands.Instruction;

public interface Pathable {
	public ArrayList<Instruction> getPath();
}
