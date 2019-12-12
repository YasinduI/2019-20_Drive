package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

public class AutonHardware extends LinearOpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor lift;
    Servo GripLeft;
    Servo GripRight;
    Servo platformR;
    Servo platformL;
    CRServo spinR;
    CRServo spinL;

    @Override
    public void runOpMode() throws InterruptedException {

        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");

        lift = hardwareMap.get(DcMotor.class, "lift");

        GripLeft = hardwareMap.get(Servo.class, "GripLeft");
        GripRight = hardwareMap.get(Servo.class, "GripRight");

        platformR = hardwareMap.get(Servo.class, "platformR");
        platformL = hardwareMap.get(Servo.class, "platformL");

        spinL = hardwareMap.get(CRServo.class, "spinL");
        spinR = hardwareMap.get(CRServo.class, "spinR");

    }

    //Methods for Auton




    /*public float rtFrontLeft(float X, float Y, float R){
        float aFrontLeft = +X + -Y + R;
        return aFrontLeft;
    }

     */

    static final float on = (float) 0.3;
    static final float off = (float) 0;



    public float mecaformula (float XX, float YY, float RR ){


        float FrontLeft = +XX + -YY + RR;
        float FrontRight = -XX + -YY - RR;
        float BackLeft = -XX + YY + RR;
        float BackRight = +XX + YY - RR;

        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);

        return 0;

    }
    public void StopRobot(long time) {
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void DriveForward(long time) {
        mecaformula(off,on,off);

    }
    public void DriveBackward(long time) {
        mecaformula(off,-on,off);
    }
    public void turnRight(long time) {
        mecaformula(off,off,on);
    }
    public void turnLeft(long time) {
        mecaformula(off,off,-on);
    }
    public void strafeRight(long time) {
        mecaformula(on,off,off);
    }
    public void strafeLeft(long time) {
        mecaformula(-on,off,off);
    }


}



