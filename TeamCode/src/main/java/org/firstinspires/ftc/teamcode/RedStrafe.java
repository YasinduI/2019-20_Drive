package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "LeftStrafe", group = "BackUp")


public class RedStrafe extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        DriveForward(800,800);
        strafeLeft(1000,1000);

        telemetry.addLine("path complete");
        telemetry.update();


    }
}
