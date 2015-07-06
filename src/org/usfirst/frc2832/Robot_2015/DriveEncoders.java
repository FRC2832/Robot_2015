package org.usfirst.frc2832.Robot_2015;

import java.util.ArrayList;
import java.util.Collections;

import edu.wpi.first.wpilibj.Preferences;

public class DriveEncoders {
	
	public static ArrayList<Double> getInitialEncoderValues() {
		ArrayList<Double> encoderVals = new ArrayList<>();
	
		encoderVals.add(RobotMap.driveBaseLeftFrontMotor.getEncPosition()  / RobotMap.ENCODER_PULSE_PER_METER);
    	encoderVals.add(RobotMap.driveBaseLeftRearMotor.getEncPosition()   / RobotMap.ENCODER_PULSE_PER_METER);
    	encoderVals.add(RobotMap.driveBaseRightFrontMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER);
    	encoderVals.add(RobotMap.driveBaseRightRearMotor.getEncPosition()  / RobotMap.ENCODER_PULSE_PER_METER);
    	
    	return (encoderVals);
	}
	
	/**
	 * This method gets encoder distance; it <b>does not work with strafe on mecanum!!!</b>
	 * @param initialVals The values of the encoders, as read from getInitialEncoderValues
	 * @return The encoder distance
	 * @see getInitialEncoderValues
	 */
	public static double getEncoderDistance(ArrayList<Double> initialVals) {
		ArrayList<Double> vals = new ArrayList<>();
		
		vals.add(  (RobotMap.driveBaseLeftFrontMotor.getEncPosition()  / RobotMap.ENCODER_PULSE_PER_METER) - initialVals.get(0));  
		vals.add(  (RobotMap.driveBaseLeftRearMotor.getEncPosition()   / RobotMap.ENCODER_PULSE_PER_METER) - initialVals.get(1));  
		vals.add(-((RobotMap.driveBaseRightFrontMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER) - initialVals.get(2))); 
		vals.add(-((RobotMap.driveBaseRightRearMotor.getEncPosition()  / RobotMap.ENCODER_PULSE_PER_METER) - initialVals.get(3))); 
    	
		Preferences.getInstance().putDouble("LFencoder", vals.get(0));    
		Preferences.getInstance().putDouble("LRencoder", vals.get(1));  
		Preferences.getInstance().putDouble("RFencoder", vals.get(2));    
		Preferences.getInstance().putDouble("RRencoder", vals.get(3));  
		
		Collections.sort(vals);
    	return (vals.get(1) + vals.get(2))/2;
    }
}
