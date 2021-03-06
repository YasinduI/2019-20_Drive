package org.firstinspires.ftc.teamcode.skystone19_20;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RightStrafe", group = "BackUp")


public class BlueStrafe extends AutonHardware {

    @Override
    public void runOpMode() {


        startup();
        waitForStart();
        sleep(2000);
        DriveForward(800,800);
        strafeRight(1000,1000);

        telemetry.addLine("path complete");
        telemetry.update();


    }
}
