package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.sqrt;

public class AutonHardware extends LinearOpMode {

    public DcMotor motorFrontRight;
    public DcMotor motorFrontLeft;
    public DcMotor motorBackRight;
    public DcMotor motorBackLeft;
    public DcMotor lift;
    public Servo GripLeft;
    public Servo GripRight;
    public Servo platformR;
    public Servo platformL;
    public CRServo spinR;
    public CRServo spinL;

    @Override
    public void runOpMode() throws InterruptedException {
        startup();
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }



    public void startup() {

        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");


        GripLeft = hardwareMap.get(Servo.class, "GripLeft");
        GripRight = hardwareMap.get(Servo.class, "GripRight");

        platformR = hardwareMap.get(Servo.class, "platformR");
        platformL = hardwareMap.get(Servo.class, "platformL");

        spinL = hardwareMap.get(CRServo.class, "spinL");
        spinR = hardwareMap.get(CRServo.class, "spinR");


        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        spinR.setDirection(CRServo.Direction.FORWARD);
        spinL.setDirection(CRServo.Direction.REVERSE);
        GripRight.setDirection(Servo.Direction.FORWARD);
        GripLeft.setDirection(Servo.Direction.REVERSE);

        platformL.setDirection(Servo.Direction.REVERSE);
        platformR.setDirection(Servo.Direction.FORWARD);

    }

    public void getlift() {

        lift = hardwareMap.get(DcMotor.class, "lift");

        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    //Methods for Auton




    /*public float rtFrontLeft(float X, float Y, float R){
        float aFrontLeft = +X + -Y + R;
        return aFrontLeft;
    }

     */
//input variables

    static final double on = 0.3;
    static final double off = 0;
    double grabMax = .9;
    double grabMin = .5;
    double platformMax = 1;
    double platformMin = 0;


    //Driving Functions and Methods
    public String mecaformula(double XX, double YY, double RR) {


        double FrontLeft = +XX + -YY + RR;
        double FrontRight = -XX + -YY - RR;
        double BackLeft = -XX + YY + RR;
        double BackRight = +XX + YY - RR;

        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);



        return "done";
    }


    public void StopRobot() {
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void DriveForward(long time, int stime) {
        mecaformula(off, -on, off);
        sleep(time);

    }

    public void DriveBackward(long time, int stime) {
        mecaformula(off, on, off);
        sleep(time);
    }

    public void turnRight(long time, int stime) {
        mecaformula(off, off, on);
        sleep(time);
    }

    public void turnLeft(long time, int stime) {
        mecaformula(off, off, -on);
        sleep(time);
    }

    public void strafeRight(long time, int stime) {
        mecaformula(on, off, off);
        sleep(time);
    }

    public void strafeLeft(long time, int stime) {
        mecaformula(-on, off, off);
        sleep(time);
    }

    public void gripOpen(long time, double position, int stime) {
        GripLeft.setPosition(position);
        GripRight.setPosition(position);
        sleep(time);
    }

    public void gripClose(long time, double position, int stime) {
        GripLeft.setPosition(position);
        GripRight.setPosition(position);
        sleep(time);
    }

    public void platformDown(long time, double position, int stime) {
        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(time);
    }

    public void platformUp(long time, double position, int stime) {
        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(time);
    }

// Lift Mechanism
    public void StopLiftArm (int stime){
        getlift();
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(stime);
    }

    public void LiftArm(long time , double power , int stime){
        getlift();

        lift.setPower(power);
        sleep(stime);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

/*    private double liftencodervalue() {
        getlift();
        double value = -lift.getCurrentPosition();


        return value;
    }

   /* private double liftupvalue() {
        double distance = lift.getCurrentPosition();
        double power = 0;
        if (distance >= 1500) {
            power = 0.35;
        }
        return power;
    }
    */

   public double liftdownvalue() {
        getlift();
        lift = hardwareMap.get(DcMotor.class, "lift");
        double distance = lift.getCurrentPosition();

        double power = 0;
        if (distance <= 1000 && distance > 200) {
            power = sqrt(distance / 1000);
        } else {
            power = -0.1;
        }

        return power;
    }

    /*public String LiftArm(long time, boolean UP, boolean DOWN) {

        int min = 10;
        int max = 1850;

        String status = "na";

        if (liftencodervalue() > min && liftencodervalue() < max) {
            if (UP) {

                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(1);
            } else if (DOWN) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(liftdownvalue());
            } else {
                lift.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                // status = "brake";

            }
            status = String.valueOf(liftencodervalue());
        } else if (liftencodervalue() >= max) {
            if (DOWN) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(liftdownvalue());
            } else {
                lift.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //status = "maxbrake";
            }

            status = "MAX";
        } else if (liftencodervalue() <= min) {
            if (UP) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(1);
            } else {
                lift.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //status = "minbrake";
            }
            status = "MIN";
        }

        sleep(time);
        return status;

    }

 */


}



