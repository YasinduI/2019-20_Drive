package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

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
    public ModernRoboticsI2cGyro gyro;
    private int valLeft = -1;
    private int valMid = -1;
    private int valRight = -1;
    private int iSkystonePos = 1;


    @Override
    public void runOpMode() throws InterruptedException {
        iSkystonePos = findSkystonePos();
        telemetry.addData("findSkystonePos=" + iSkystonePos );
        telemetry.update();

        startup();
    }

    private int findSkystonePos() {
        SkystoneDetectorService skystoneDetectorService = new SkystoneDetectorService();
        valLeft = skystoneDetectorService.getValLeft();
        valMid = skystoneDetectorService.getValMid();
        valRight = skystoneDetectorService.getValRight();
        int pos = 1;

        // determine which one has skystone

        if (valLeft = 0) {
            pos = 1;
        } else if (valMid = 0) {
            pos = 2;
        } else
            pos = 3;
        return pos;
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

        platformL.setDirection(Servo.Direction.FORWARD);
        platformR.setDirection(Servo.Direction.REVERSE);

        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class,"gyro");


        telemetry.addLine("Ready");
        telemetry.update();
    }

    public void getlift() {

        lift = hardwareMap.get(DcMotor.class, "lift");


    }

    public void getdrive() {
        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");

        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

    }

    public void getgyro() {
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
    }


//input variables

    static final double on = 0.3;
    static final double off = 0;
    double grabMax = .9;
    double grabMin = .5;
    double platformMax = 1;
    double platformMin = 0;


    //Driving Functions and Methods
    public String mecaformula(double XX, double YY, double RR) {
        double FrontLeft = +(XX) - (YY) + (RR);
        double FrontRight = -(XX) - (YY) - (RR);
        double BackLeft = -(XX) + (YY) + (RR);
        double BackRight = +(XX) + (YY) - (RR);

        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);

        return "done";
    }

    public void StopRobot(long time) {
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(time);
    }

    public void DriveForward(long time, int stime) {

        getgyro();

        telemetry.addLine("RUN");
        telemetry.update();



        if (gyro.getIntegratedZValue() > 0) {
            mecaformula(off, off, -on);
            sleep(time);
        } else if (gyro.getIntegratedZValue() < 0) {
            mecaformula(off, off, on);
        }
//        } else {
//            mecaformula(off, -on, off);
//        }

        sleep(stime);

    }

    public void DriveBackward(long time, int stime) {
        mecaformula(off, on, off);
        sleep(time);
        telemetry.addData("value", motorBackLeft.getPower() + "," + motorBackRight.getPower());
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

    //Mechanisms

    public void Grabbers(long time, double position, int stime) {
        GripLeft.setPosition(position);
        GripRight.setPosition(position);
        sleep(stime);
    }


    public void Platform(long time, double position, int stime) {
        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(time);
    }

    // Lift Mechanism
    public void StopLiftArm(long time) {
        getlift();
        lift.setPower(0);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(time);
    }

    public void LiftArm(long time, double power, int stime) {
        getlift();

        lift.setPower(power);
        sleep(stime);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }


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

    //Paths

    public void PathRight() {

    }

    public void PathLeft() {

    }

    public void PathCenter() {

    }


    //Gryo

    public void GyroInit() {
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro.calibrate();

        while (!isStopRequested() && gyro.isCalibrating()) {
            telemetry.addLine("CALIBRATING");
            telemetry.update();
            sleep(50);
            idle();
        }
            telemetry.addData("Heading", gyro.getIntegratedZValue());
            telemetry.update();

    }

    public double GyroValue() {
        getgyro();
        int z = gyro.getIntegratedZValue();
        return z;
    }
}