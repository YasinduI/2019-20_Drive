package org.firstinspires.ftc.teamcode.skystone19_20;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RedAutonSkystone",group = "Skystone")


public class RedAutonSkystone extends AutonHardware {


    @Override
    public void runOpMode() {
        startup();

        detectorstartup();

        waitForStart();

            telemetry.addData("skystone position", findSkystonePosActive());
            telemetry.addData("skystone position L", getValLeft());
            telemetry.addData("skystone position M", getValMid());
            telemetry.addData("skystone position R", getValRight());
            telemetry.update();

        RedRunPath();
        stop();
    }
}
