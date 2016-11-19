package org.usfirst.frc.team360.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Logger extends Subsystem {
	String content; //content written to the Log
    FileWriter fw;
    BufferedWriter bw;
	File Log;
	double matchTime;
	double encValRight;
	double encValRightOld;
	double encValLeft;
	double encValLeftOld;
	
	boolean canStop = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	String timeMessage;// the match/ FPGA time string
	String encodersMessage;// the encoder values
	public void initLogger(){
		try {
			canStop = true;
			encValRightOld = 0;
			encValLeftOld = 0;
    		Log = new File("u/newLog2.txt"); //Log location
				
			//if Log doesnt exist, then create it
			if (!Log.exists()) {
				Log.createNewFile();
			}
			fw = new FileWriter(Log.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void logTime(){
		try {
			matchTime = ((double)((int)(Robot.RoboRioTimer.getMatchTime()*100))/100);
    		//content = (Robot.drivetrain.getMotor());
			if(matchTime != -1){
				timeMessage = "Match Time: " + ((Double)matchTime).toString() + (" seconds") + '\n';
			} else {
				timeMessage = "FPGA Time: " + ((Double)((double)((int)(Robot.RoboRioTimer.getFPGATimestamp()*100))/100)).toString() + " seconds" + '\n';
			}
			bw.write(timeMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logEncoders(){
		try{
			encValRight = RobotMap.encR.get();
			encValLeft = RobotMap.encL.get();
			if(encValRight != encValRightOld || encValLeft != encValLeftOld){
				encValLeftOld = encValLeft;
				encValRightOld = encValRight;
				encodersMessage = '\t' + "Right Encoder: " + encValRight + ", Left Encoder: " + encValLeft + '\n';
				bw.write(encodersMessage);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeLogger(){
		try {
			if(canStop){
				bw.close();	
				canStop = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

