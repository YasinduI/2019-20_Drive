package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by PIE on 11/1/2017.
 */
@TeleOp(name = "MecanumDrive", group = "Linear Opmode")


public class MecanumDrive extends OpMode {
    // Declare All Motors and Servos

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFrontRight = null;
    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackRight = null;
    private DcMotor motorBackLeft = null;
    private DcMotor lift = null;
    private Servo GripLeft = null;
    private Servo GripRight = null;
    private Servo platform = null;
    private CRServo spinR = null;
    private CRServo spinL = null;



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
        //Setting the drive controls to the gamepad
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        motorFrontLeft.setPower(-v1);
        motorFrontRight.setPower(-v2);
        motorBackLeft.setPower(-v3);
        motorBackRight.setPower(-v4);


        if (lift.getCurrentPosition() > min && lift.getCurrentPosition() < max)
            if (gamepad1.dpad_up) {
                lift.setPower(1);
            } else if (gamepad1.dpad_down) {
                lift.setPower(-1);
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


        if (gamepad1.a) {
            platform.setPosition(.2);
        }

        else if (gamepad1.b) {
            platform.setPosition(.5);
        }

        if (gamepad1.right_bumper){
            spinL.setPower(1);
            spinR.setPower(1);
        }
        if (gamepad1.left_bumper){
            spinL.setPower(-1);
            spinR.setPower(-1);
        }
        else{
            spinR.setPower(0);
            spinL.setPower(0);
        }










        telemetry.addData("lift",lift.getCurrentPosition()+"  busy="+lift.isBusy());



    }

}

