package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutonTest")


public class RedAuton extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        LiftArm(1000, 1, 500);
        StopLiftArm();
        DriveForward(2000, 0);
        strafeRight(1000,0);
        DriveForward(1000,0);
        platformDown(1000, 1,0);
        platformUp(1000,0,0);
        DriveBackward(200,0);
        platformDown(1000,1,0);
        DriveBackward(2000, 0);
        turnRight(2000,0);
        platformUp(1000,0,0);
        DriveForward(2000,1);
        strafeRight(1000,0);
        DriveBackward(2000,0);



    }
}