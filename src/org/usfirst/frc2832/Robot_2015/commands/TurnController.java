package org.usfirst.frc2832.Robot_2015.commands;
import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Preferences;

public class TurnController implements PIDOutput {
	public void pidWrite(double output) {
		Preferences.getInstance().putDouble("Yaw", RobotMap.imu.getYaw());
		Preferences.getInstance().putDouble("PIDOutput", output);
		//RobotMap.driveBaseDriveTrain.arcadeDrive(0.0, -output);		
		
		// 2 m/s
    	double conv = 2 * RobotMap.DRIVE_TRAIN_CONVERSION;
		
		RobotMap.driveBaseDriveTrain.mecanumDrive_Cartesian(0, 0, conv * output, RobotMap.imu.getYaw());
	}
	
}