package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BlueAutonSkystone", group = "Skystone")


public class BlueAutonSkystone extends AutonHardware {


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

        BlueRunPath();
        stop();
    }
}
