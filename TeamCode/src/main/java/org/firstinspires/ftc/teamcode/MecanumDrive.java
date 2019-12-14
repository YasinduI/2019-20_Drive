package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Math.sqrt;


@TeleOp(name = "MecanumDrive2", group = "Opmode")


public class MecanumDrive extends TeleOpHardware {


    @Override
    public void loop() {


        Driving();
        LiftArm();
        String gripperstatus = GripperIntake();
        String platformstatus = PlatformGrabber();
        String DrivingSpeed = returndrivevalue();


        telemetry.addData("DrivingSpeed " , DrivingSpeed);
        telemetry.addData("liftstatus", LiftArm());
        telemetry.addData("liftpower", lift.getPower());
        telemetry.addData("gripperstatus", gripperstatus, GripRight.getPosition(), GripLeft.getPosition());
        telemetry.addData("platform", platformstatus);
        telemetry.addData("Brake", lift.getZeroPowerBehavior());

    }



    @Override
    public void stop() {

    }
}
