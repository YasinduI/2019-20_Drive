package org.firstinspires.ftc.teamcode.skystone19_20;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "BlueAutonFoundation", group = "Foundation")


public class BlueAutonFoundation extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        Grabbers(800,grabMin,800);
        LiftArm(500,1,500);
        StopLiftArm(100);
        DriveForward(800,800);
        strafeLeft(600,600);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(900,900);
        strafeRight(1500,1500);
        turnLeft(2500,2500);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        strafeLeft(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeRight(1000,1000);
        DriveBackward(900,900);
        StopRobot(10);
        LiftArm(2000,liftdownvalue(),2000);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();

        telemetry.addLine("path complete");
        telemetry.update();


    }
}