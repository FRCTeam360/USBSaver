package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team360.robot.Robot;

public class UsbSave2 extends Command {
    public UsbSave2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.logger.logTime();
    	Robot.logger.logEncoders();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
