package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutonTest")


public class AutonTest extends AutonHardware  {

    @Override
    public void runOpMode() {
        waitForStart();
        DriveForward(200);
        DriveBackward(200);
        strafeLeft(200);
        strafeRight(200);
        turnLeft(200);
        turnRight(200);

        

    }
}