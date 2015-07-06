package org.usfirst.frc2832.Robot_2015.subsystems;

import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Class for controlling various aspects of the lift.
 */
public class LiftControl {
	
	public final static double pulsePerMeter = 11682/.73;
	public final static double zeroCalib = 0.01137;
	public final static double[] metersToLevel = {zeroCalib, 0.34356 + zeroCalib, 0.64145 + zeroCalib, 0.95121 + zeroCalib, 1.31028 + zeroCalib};
	public static double zeroValue = 0;
	public static double maxValue = 1.3 * pulsePerMeter;
	
	/**
	 * Gives the value of the lift's encoder, in meters.
	 * Takes encoder zeroing into account.
	 * @return the current encoder value
	 */
	public static double getEncoderValue() {
		return (RobotMap.forkliftMotor.getPosition() / pulsePerMeter) - zeroCalib;
	}	
	
	/**
	 * Returns a position based on how many levels you choose to move; it does handle move as well.
	 * @param levelChange The level amt to move up/down
	 * @return The amount of meters high that level is.
	 */
	public static double moveLevels(int levelChange)
	{
		if (levelChange == 0)
			return (RobotMap.forkliftMotor.getPosition());
		
		double height = getEncoderValue();
		
		
		for(int i = 0; i < metersToLevel.length; i++) {
			// Within 5cm; treat as a "close enough"
			if (Math.abs(height - metersToLevel[i]) < 0.05) {
				// At level i
				return (moveToLevel(i + levelChange));
			}
		}
		
		// Failed to find a close one; rather, select one in between
		for(int i = 0; i < metersToLevel.length; i++) {	
			if (height < metersToLevel[i]) {
				// Just below level i
				if (levelChange > 0)
					return (moveToLevel(i - 1 + levelChange));
				else
					return (moveToLevel(i + levelChange));
				
			}
		}
		
		//if forklift goes higher than max level, then still go down
		if(height > metersToLevel[metersToLevel.length - 1] && levelChange < 0)
			return (moveToLevel(metersToLevel.length - 1));
		
			
		return (RobotMap.forkliftMotor.getPosition());
	}
	
	/**
	 * @param newLevel The level amt to move up/down
	 * @return The amount of meters high that level is.
	 */
	public static double moveToLevel(int newLevel)
	{
		if (RobotMap.forkliftMotor.getControlMode() != CANTalon.ControlMode.Position)
			return (RobotMap.forkliftMotor.getPosition());
		
		if(newLevel >= metersToLevel.length)
			newLevel = metersToLevel.length - 1;
		else if(newLevel < 0)
			newLevel = 0;
		
		double target = metersToLevel[newLevel] * pulsePerMeter;
		
		moveToPosition(target);
		
		return (target);
	}
	
	/**
	 * Moves the lift up or down.
	 * @param pos The position to move to, in encoder ticks. Bottom is 0, top is (TODO: Find Top).
	 */
	public static void moveToPosition(double pos) {
		
		if (RobotMap.forkliftMotor.getControlMode() != CANTalon.ControlMode.Position)
			return;
		
		// Make sure not above the top stop
		if(pos > maxValue) 
    		pos = maxValue;
		
		// Make sure not below the bottom stop
		if(pos < 0) 
    		pos = 0;
		
		RobotMap.forkliftMotor.enableControl();
		RobotMap.forkliftMotor.set(pos);
	}

}
