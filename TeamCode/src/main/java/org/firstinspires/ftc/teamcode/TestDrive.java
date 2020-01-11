package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "test ")


public class TestDrive extends AutonHardware {



    @Override
    public void runOpMode() {
        startup();



        waitForStart();



        if (gyro.getIntegratedZValue() > 0) {
            mecaformula(off, off, -on);
            sleep(10000);
        } else if (gyro.getIntegratedZValue() < 0) {
            mecaformula(off, off, on);
        }

//        telemetry.addLine("Step 2");
        telemetry.update();
        DriveBackward(2000,2000);

        StopRobot(50);
        stop();



    }
}
