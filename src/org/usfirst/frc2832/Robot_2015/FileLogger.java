package org.usfirst.frc2832.Robot_2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileLogger {
	private static File file;
	private static PrintWriter writer;
	
	static {
		try {
			file = new File("C:/log.txt");
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void log(String data) {
		writer.println(data + "\r\n");
	}
}
