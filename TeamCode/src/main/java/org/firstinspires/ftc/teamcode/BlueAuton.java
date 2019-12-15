package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "FullBlueAuton")


public class BlueAuton extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        Grabbers(800,grabMin,800);
        LiftArm(500,1,500);
        StopLiftArm(100);
        DriveForward(800,800);
        strafeLeft(450,450);
        DriveForward(600,600);
        DriveBackward(80,80);
        StopRobot(100);
        Platform(1000,platformMax,1000);
        DriveForward(80,80);
        DriveBackward(2300,2300);
        turnLeft(2550,2500);
        StopRobot(100);
        Platform(500,platformMin,500);
        DriveForward(3500,3500);
        StopRobot(100);
        Grabbers(800,grabMin,900);
        strafeLeft(900,9000);
        StopRobot(100);
        DriveBackward(800,800);
        strafeRight(1150,1150);
        DriveBackward(600,600);
        StopRobot(100);
        LiftArm(2000,liftdownvalue(),2000);
        LiftArm(3000,0.15,2000);
        StopLiftArm(100);
        stop();

        telemetry.addLine("path complete");
        telemetry.update();


    }
}