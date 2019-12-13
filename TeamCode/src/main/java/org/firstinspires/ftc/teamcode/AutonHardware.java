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
    double grabMax = .9;
    double grabMin = .5;
    double platformMax = 1;
    double platformMin = 0;



    public float mecaformula (float XX, float YY, float RR ){


        float FrontLeft = +XX + -YY + RR;
        float FrontRight = -XX + -YY - RR;
        float BackLeft = -XX + YY + RR;
        float BackRight = +XX + YY - RR;

        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);
        GripLeft.scaleRange(.5, .85);
        GripRight.scaleRange(.5, .9);
        platformL.scaleRange(0, 1);
        platformR.scaleRange(0, 1);


        return 0;
    }
    public void StopRobot(long time) {
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void DriveForward(long time , int stime) {
        mecaformula(off,on,off);
        sleep(stime);

    }
    public void DriveBackward(long time, int stime) {
        mecaformula(off,-on,off);
        sleep(stime);
    }
    public void turnRight(long time, int stime) {
        mecaformula(off,off,on);
        sleep(stime);
    }
    public void turnLeft(long time, int stime) {
        mecaformula(off,off,-on);
        sleep(stime);
    }
    public void strafeRight(long time, int stime) {
        mecaformula(on,off,off);
        sleep(stime);
    }
    public void strafeLeft(long time, int stime) {
        mecaformula(-on,off,off);
        sleep(stime);
    }
    public void gripOpen(long time , double postion, int stime){
        GripLeft.setPosition(postion);
        GripRight.setPosition(postion);
        sleep(stime);
    }
    public void gripClose(long time, double position, int stime){
        GripLeft.setPosition(position);
        GripRight.setPosition(position);
        sleep(stime);
    }
    public void platformDown(long time, double position, int stime){
        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(stime);
    }
    public void platformUp(long time, double position, int stime) {
        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(stime);
    }


}



