//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//@Autonomous(name = "AutonTest")
//@Disabled
//
//
//public class AutonTest extends AutonHardware {
//
//    @Override
//    public void runOpMode() {
//
//
//        startup();
//        waitForStart();
//        telemetry.update();
//        telemetry.addData("Step", "1");
//        telemetry.update();
//
//        LiftArm(2000, 1, 3000);
//        telemetry.addData("Step", "2");
//        telemetry.update();
//
//
//
//        LiftArm(1000, liftdownvalue(),3000);
//        telemetry.addData("Step", "3");
//        telemetry.update();
//
//        DriveForward(3000, 0);
//        telemetry.addData("Step", "4");
//        telemetry.update();
//
//
//        DriveBackward(3000, 0);
//        telemetry.addData("Step", "5");
//        telemetry.update();
//
//        strafeLeft(3000, 0);
//        telemetry.addData("Step", "6");
//        telemetry.update();
//
//        strafeRight(3000, 0);
//        telemetry.addData("Step", "7");
//        telemetry.update();
//
//        turnLeft(3000, 0);
//        telemetry.addData("Step", "8");
//        telemetry.update();
//
//        turnRight(3000, 0);
//        telemetry.addData("Step", "9");
//        telemetry.update();
//
//
//        platformDown(2000, platformMax, 0);
//        telemetry.addData("Step", "10");
//        telemetry.update();
//
//        platformUp(2000, platformMin, 0);
//        telemetry.addData("Step", "11");
//        telemetry.update();
//
//        gripClose(2000, grabMin, 0);
//        telemetry.addData("Step", "12");
//        telemetry.update();
//
//        gripOpen(2000, grabMax, 0);
//        telemetry.addData("Step", "13");
//        telemetry.update();
//
//
//        telemetry.addData("path", "complete");
//        telemetry.update();
//
//
//    }
//}