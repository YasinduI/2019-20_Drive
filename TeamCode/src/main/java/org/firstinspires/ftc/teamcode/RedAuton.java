package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "FullRed Auton")


public class RedAuton extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        Grabbers(800,grabMin,800);
        LiftArm(600,1,600);
        StopLiftArm(100);
        DriveForward(800,800);
        strafeRight(400,400);
        DriveForward(650,650);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,platformMax,1500);
        DriveForward(80,80);
        DriveBackward(1250,1250);
        turnRight(4500,4500);
        StopRobot(100);
        Platform(500,platformMin,500);
        DriveForward(3500,3500);
        StopRobot(100);
        Grabbers(800,grabMin,900);
        strafeRight(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeLeft(1000,1000);
        DriveBackward(900,900);
        StopRobot(100);
        LiftArm(2000,liftdownvalue(),2000);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();

        telemetry.addLine("path complete");
        telemetry.update();


    }
}