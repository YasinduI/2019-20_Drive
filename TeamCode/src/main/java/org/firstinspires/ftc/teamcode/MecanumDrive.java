package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.renderscript.Sampler;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 */
@TeleOp(name = "MecanumDrive", group = "Opmode")


public class MecanumDrive extends OpMode {
    // Declare All Motors and Servos

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor lift;
    Servo GripLeft;
    Servo GripRight;
    Servo platform;
    CRServo spinR;
    CRServo spinL;


    @Override
    public void init() {
        //Set the status to Initialization
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Naming and declaring the classes of the motors and servos

        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");
        lift = hardwareMap.get(DcMotor.class, "lift");
        GripLeft = hardwareMap.get(Servo.class, "GripLeft");
        GripRight = hardwareMap.get(Servo.class, "GripRight");
        platform = hardwareMap.get(Servo.class, "platform");
        spinL = hardwareMap.get(CRServo.class, "spinL");
        spinR = hardwareMap.get(CRServo.class, "spinR");


        //Setting the direction of the Motors and Servos
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        spinR.setDirection(CRServo.Direction.FORWARD);
        spinL.setDirection(CRServo.Direction.REVERSE);
        GripRight.setDirection(Servo.Direction.FORWARD);
        GripLeft.setDirection(Servo.Direction.REVERSE);


        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

    }

    @Override
    public void loop() {
        int min = -2050;
        int max = -100;


//Drive Train
        float Y = gamepad1.left_stick_y;
        float X = gamepad1.left_stick_x;
        float R = gamepad1.right_stick_x;

        float FrontLeft = +X + Y + R;
        float FrontRight = -X + Y - R;
        float BackLeft = -X + Y + R;
        float BackRight = +X + Y - R;


        if (gamepad1.x) {
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

            telemetry.addData("SPEEDY BOY","ON");
        }
        else {
            FrontRight = (float) Range.clip(FrontRight, -0.5, 0.5);
            FrontLeft = (float) Range.clip(FrontLeft, -0.5, 0.5);
            BackLeft = (float) Range.clip(BackLeft, -0.5, 0.5);
            BackRight = (float) Range.clip(BackRight, -0.5, 0.5);

            telemetry.addData("PRECISE BOY","ON");
        }

        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);




        if (lift.getCurrentPosition() > min && lift.getCurrentPosition() < max)
            if (gamepad1.dpad_up) {
                lift.setPower(1);
            } else if (gamepad1.dpad_down) {
                lift.setPower(-.3);
            } else {
                lift.setPower(0);
            }
        else if (lift.getCurrentPosition() <= min)
            if (gamepad1.dpad_down) {
                lift.setPower(-1);
            } else {
                lift.setPower(0);
            }
        if (lift.getCurrentPosition() >= max)
            if (gamepad1.dpad_up) {
                lift.setPower(1);
            } else {
                lift.setPower(0);
            }


        GripLeft.setPosition(gamepad1.right_trigger);
        GripRight.setPosition(gamepad1.right_trigger);

        if (gamepad1.right_bumper) {
            spinL.setPower(1);
            spinR.setPower(1);
        }
        if (gamepad1.left_bumper) {
            spinL.setPower(-1);
            spinR.setPower(-1);
        } else {
            spinR.setPower(0);
            spinL.setPower(0);
        }

        if (gamepad1.a) {
            platform.setPosition(.2);
        }

        if (gamepad1.b) {
            platform.setPosition(.5);
        }


        telemetry.addData("Lift Position", lift.getCurrentPosition() + "");


    }


    @Override
    public void stop() {

    }
}