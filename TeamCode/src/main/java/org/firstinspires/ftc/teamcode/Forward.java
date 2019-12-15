package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Forward")


public class Forward extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        DriveForward(800,800);

        telemetry.addLine("path complete");
        telemetry.update();


    }
}
