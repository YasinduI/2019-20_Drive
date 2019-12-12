package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MecanumDrive", group = "Opmode")
//new

public class MecanumDrive extends TeleOpHardware {


    @Override
    public void init() {

    }

    @Override
    public void loop() {

        Driving();
        String liftstatus = LiftArm();
        String gripperstatus = GripperIntake();
        Double platformstatus = PlatformGrabber();
        String DrivingSpeed = returndrivevalue();


        telemetry.addData("DrivingSpeed " , DrivingSpeed);
        telemetry.addData("liftstatus", liftstatus);
        telemetry.addData("liftposition", liftencodervalue());
        telemetry.addData("liftpower", lift.getPower());
        telemetry.addData("gripperstatus", gripperstatus, GripRight.getPosition(), GripLeft.getPosition());
        telemetry.addData("platform", platformstatus);
        telemetry.addData("Brake", lift.getZeroPowerBehavior());

    }

    @Override
    public void stop() {

    }
}
