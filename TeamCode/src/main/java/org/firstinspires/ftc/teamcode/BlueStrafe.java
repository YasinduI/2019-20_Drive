package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RightStrafe")


public class BlueStrafe extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        DriveForward(800,800);
        strafeRight(1000,1000);

        telemetry.addLine("path complete");
        telemetry.update();


    }
}
