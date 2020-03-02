package org.firstinspires.ftc.teamcode;

import android.graphics.Paint;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Math.sqrt;


@TeleOp(name = "Drive Code ILT")


public class MecanumDrive extends TeleOpHardware {


    @Override
    public void loop() {



        Driving();
        LiftArm();
        String gripperstatus = GripperIntake();
        PlatformGrabber();
        String DrivingSpeed = returndrivevalue();
        Capstone();
        String SideArmValue = SideArm();


        telemetry.addData("DrivingSpeed " , DrivingSpeed);
        telemetry.addData("liftstatus", LiftArm());
        telemetry.addData("liftpower", lift.getPower());
        telemetry.addData("gripperstatus", gripperstatus, GripRight.getPosition(), GripLeft.getPosition());
//        telemetry.addData("platform", platformstatus);
        telemetry.addData("Brake", lift.getZeroPowerBehavior());
        telemetry.addData("Captone", Capstone());
        telemetry.addData("SideArm Value",SideArmValue);

    }



    @Override
    public void stop() {

    }
}
