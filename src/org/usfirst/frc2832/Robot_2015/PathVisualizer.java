package org.usfirst.frc2832.Robot_2015;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.PickUpObject;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Point;

public class PathVisualizer {
	
	private static double a, b;
	
	private static NIVision.Image img = null;
	
	public static NIVision.Image makeImage(ArrayList<Instruction> path) {
		if (img == null)
			img = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		
		
		int white = 0xFFFFFFFF;
		
		// Field is 8.2m by 7m
		int w = 256, h = 256;
		
		// Conversion from meter coordinates on field to pixels on image
		a = w / 7.0;
		b = h / 8.2;
		
		NIVision.imaqSetImageSize(img, w, h);
		
		// Field outline
		line(img, 7.0, 0.0, 1.8, 0.0, white);
		line(img, 0.0, 1.8, 1.8, 0.0, white);
		
		line(img, 7.0, 8.2, 1.8, 8.2, white);
		line(img, 0.0, 6.4, 1.8, 8.2, white);
		
		line(img, 0.0, 1.8, 0.0, 6.4, white);
		
		FieldLocation currentLocation = null;
		for(Instruction inst : path) {
			if (inst instanceof FieldLocation) {
				// Draw line connecting waypoints
				if (currentLocation != null)
					line(img, currentLocation.x, currentLocation.y, ((FieldLocation) inst).x, ((FieldLocation) inst).y, white);
				currentLocation = (FieldLocation) inst;
			} else {
				// If no location given yet, then find the first one
				if (currentLocation == null) {
					for(Instruction fieldLocation : path) {
						if (fieldLocation instanceof FieldLocation) {
							currentLocation = (FieldLocation) fieldLocation;
							break;
						}
					}
				}
				
				// Give symbols of color here
				if (inst instanceof PickUpObject)
					drawLocation(img, currentLocation, white);
			}
		}
		
		return (img);
	}
	
	private static void drawLocation(NIVision.Image img, FieldLocation loc, int color) {
		rect(img, loc.x - 0.2, loc.y - 0.2, 0.4, 0.4, color);
		line(img, loc.x, loc.y, 
				loc.x + 0.4 * Math.cos(-loc.heading / 180 * Math.PI),
				loc.y - 0.4 * Math.sin(-loc.heading / 180 * Math.PI), color);
	}
	
	private static void rect(NIVision.Image img, double x, double y, double w, double h, int color) {
		line(img, x, y, x + w, y, color);
		line(img, x, y, x, y + h, color);
		line(img, x + w, y, x + w, y + h, color);
		line(img, x, y + h, x + w, y + h, color);
	}
	
	private static void line(NIVision.Image img, double x1, double y1, double x2, double y2, int color) {
		NIVision.imaqDrawLineOnImage(img, img, NIVision.DrawMode.DRAW_INVERT, 
				new Point((int) (a * x1), (int) (b * y1)), 
				new Point((int) (a * x2), (int) (b * y2)), color);
	}
	
}
