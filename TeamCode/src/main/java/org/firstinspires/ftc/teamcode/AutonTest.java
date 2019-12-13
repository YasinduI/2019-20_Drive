package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutonTest")


public class AutonTest extends AutonHardware  {

    @Override
    public void runOpMode() {
        waitForStart();
        DriveForward(200,0);
        DriveBackward(200,0);
        strafeLeft(200,0);
        strafeRight(200,0);
        turnLeft(200,0);
        turnRight(200,0);
        platformDown(200,platformMax,0);
        platformUp(200,platformMin,0);
        gripClose(200,grabMin,0);
        gripOpen(200,grabMax,0);
        StopRobot(1000);

        

    }
}