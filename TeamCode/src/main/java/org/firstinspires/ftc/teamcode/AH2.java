package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import static java.lang.Math.sqrt;

public class AH2 extends LinearOpMode {

    public DcMotor motorFrontRight;
    public DcMotor motorFrontLeft;
    public DcMotor motorBackRight;
    public DcMotor motorBackLeft;
    public DcMotor lift;
    public DcMotor lifthelper;
    public Servo GripLeft;
    public Servo GripRight;
    public Servo platformR;
    public Servo platformL;
    public CRServo spinR;
    public CRServo spinL;
    public Servo Arm1;
    public Servo Arm2;
    private int valLeft = 10;
    private int valMid = 10;
    private int valRight = 10;

    public int getValLeft() {
        return valLeft;
    }

    public int getValMid() {
        return valMid;
    }

    public int getValRight() {
        return valRight;
    }

    public int getiSkystonePos() {
        return iSkystonePos;
    }

    private int iSkystonePos = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        startup();
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

        Arm1 = hardwareMap.get(Servo.class, "arm1");
        Arm2 = hardwareMap.get(Servo.class, "arm2");
        Arm1.scaleRange(0.1, 1);
        Arm2.scaleRange(0, .4);

        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        spinR.setDirection(CRServo.Direction.FORWARD);
        spinL.setDirection(CRServo.Direction.REVERSE);
        GripRight.setDirection(Servo.Direction.FORWARD);
        GripLeft.setDirection(Servo.Direction.REVERSE);

        Arm1.setDirection(Servo.Direction.REVERSE);
        Arm2.setDirection(Servo.Direction.REVERSE);
        platformL.setDirection(Servo.Direction.FORWARD);
        platformR.setDirection(Servo.Direction.REVERSE);

        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifthelper = hardwareMap.get(DcMotor.class,"lifthelper");
        lifthelper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifthelper.setDirection(DcMotor.Direction.REVERSE);


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


//input variables

    static final double s_on = .3;
    static final double d_on = .4;
    static final double off = 0;
    double grabMax = .9;
    double grabMin = .5;
    double platformMax = 1;
    double platformMin = 0;
    static final int i_on = 1;
    static final int i_off = 0;

    static final double sarmup = 1;
    static final double sarmdown = 0.1;
    static final double sarmmid = 0.35;

    static final double sarmopen = 1;
    static final double sarmclose = 0;


    //Driving Functions and Methods
    public String mecaformula(double XX, double YY, double RR) {
        double FrontLeft = +(XX) - (YY) + (RR);
        double FrontRight = -(XX) - (YY) - (RR);
        double BackLeft = -(XX) - (YY) + (RR);
        double BackRight = +(XX) - (YY) - (RR);

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
        mecaformula(off, -d_on, off);
        sleep(time);
        telemetry.addData("value", motorBackLeft.getPower() + "," + motorBackRight.getPower());


    }

    public void DriveBackward(long time, int stime) {
        mecaformula(off, d_on, off);
        sleep(time);
        telemetry.addData("value", motorBackLeft.getPower() + "," + motorBackRight.getPower());
    }

    public void turnRight(long time, int stime) {
        mecaformula(off, off, s_on);
        sleep(time);
    }

    public void liftUp(long time, int stime) {
        lift.setPower(0.1);
        lifthelper.setPower(0.1);
        sleep(time);
    }

    public void liftDown(long time, int stime) {
        lift.setPower(0.1);
        lifthelper.setPower(0.1);
        sleep(time);
    }

    public void sideArm(long time, int stime, double aposition) {
        Arm2.setPosition(aposition);
        sleep(stime);
    }

    // public void sideArmUp(long time, int stime) {
    //     Arm2.setPosition(1);
    //     sleep(stime);
    // }

    public void sideArmGrip(long time, int stime, double gposition) {
        Arm1.setPosition(gposition);
        sleep(stime);
    }

    // public void sideArmClose(long time, int stime) {
    //     Arm1.setPosition(0);
    //     sleep(stime);
    // }

    public void turnLeft(long time, int stime) {
        mecaformula(off, off, -s_on);
        sleep(time);
    }

    public void strafeRight(long time, int stime) {
        mecaformula(s_on, off, off);
        sleep(time);
    }

    public void strafeLeft(long time, int stime) {
        mecaformula(-s_on, off, off);
        sleep(time);
    }

    //Mechanisms

    public void Grabbers(long time, double position, int stime) {

        GripLeft.setPosition(position);
        GripRight.setPosition(position);
        sleep(stime);
    }

    public void Intake(long time, double spower, int stime) {
        int on = 1;
        int off = 0;
        spinL.setPower(spower);
        spinR.setPower(spower);
    }


    public void Platform(long time, double position, int stime) {
        platformL.scaleRange(0, 0.65);
        platformR.scaleRange(.35, 1);

        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(time);
    }

    // Lift Mechanism
    public void StopLiftArm(long time) {
        getlift();
        lift.setPower(0.1);
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

    //RED Paths

    public void RedPathRight() {
        telemetry.addLine("Target: Right");
        telemetry.update();
        strafeRight(1100, 1100);
        DriveBackward(800, 800);
        turnRight(600, 600);
        StopRobot(50);
        Grabbers(50, grabMax, 200);
        DriveForward(600, 600);
        StopRobot(100);
        Grabbers(50, grabMin, 200);
        Intake(200, i_on, 200);
        DriveBackward(500, 500);
        turnLeft(700, 700);
        StopRobot(50);
        Intake(50, off, 50);
        DriveBackward(2100, 2100);
        StopRobot(50);
        LiftArm(700, 1, 700);
        StopLiftArm(100);
        turnRight(800, 800);
        DriveForward(750, 750);
        DriveBackward(80, 100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION, 1500);
        DriveForward(80, 80);
        DriveBackward(800, 800);
        strafeLeft(1500, 1500);
        turnRight(2800, 2800);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION, 500);
        DriveForward(1200, 1200);
        StopRobot(100);
        LiftArm(200, -1, 200);
        StopLiftArm(50);
        Grabbers(100, grabMax, 100);
        LiftArm(200, 1, 200);
        StopLiftArm(50);
        strafeRight(1000, 1000);
        DriveForward(500, 500);
        StopRobot(100);
        DriveBackward(800, 800);
        strafeLeft(500, 500);
        LiftArm(100, -1, 100);
        StopLiftArm(50);
        DriveBackward(900, 900);
        StopRobot(100);
        LiftArm(3000, 0.15, 2000);

        StopLiftArm(100);
        stop();
    }

    public void RedPathLeft() {
        telemetry.addLine("Target: Left");
        telemetry.update();

        strafeRight(1000, 1000);
        DriveBackward(200, 200);
        strafeRight(1800, 1800);
        StopRobot(50);
        Grabbers(50, grabMax, 200);
        DriveForward(300, 300);
        StopRobot(100);
        Grabbers(50, grabMin, 200);
        Intake(200, i_on, 200);
        strafeLeft(1250, 1250);
        StopRobot(50);
        Intake(50, off, 50);
        DriveBackward(3050, 3050);
        StopRobot(50);
        LiftArm(700, 1, 700);
        StopLiftArm(100);
        turnRight(800, 800);
        DriveForward(750, 750);
        DriveBackward(80, 100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION, 1500);
        DriveForward(80, 80);
        DriveBackward(900, 900);
        strafeLeft(1500, 1500);
        turnRight(2800, 2800);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION, 500);
        DriveForward(1200, 1200);
        StopRobot(100);
        LiftArm(200, -1, 200);
        StopLiftArm(50);
        Grabbers(100, grabMax, 100);
        LiftArm(200, 1, 200);
        StopLiftArm(50);
        strafeRight(1000, 1000);
        DriveForward(600, 600);
        StopRobot(100);
        DriveBackward(800, 800);
        strafeLeft(1000, 1000);
        LiftArm(50, -1, 50);
        StopLiftArm(50);
        DriveBackward(900, 900);
        StopRobot(100);
        LiftArm(3000, 0.15, 2000);

        StopLiftArm(100);
        stop();

    }

    public void RedPathCenter() {
        telemetry.addLine("Target: Center");
        telemetry.update();

        strafeRight(1000, 1000);
        DriveBackward(480, 180);
        strafeRight(1750, 1750);
        StopRobot(50);
        Grabbers(50, grabMax, 200);
        DriveForward(200, 200);
        StopRobot(100);
        Grabbers(50, grabMin, 200);
        Intake(200, i_on, 200);
        strafeLeft(1500, 1500);
        StopRobot(50);
        Intake(50, off, 50);
        DriveBackward(2600, 2600);
        StopRobot(50);
        LiftArm(700, 1, 700);
        StopLiftArm(100);
        turnRight(800, 800);
        DriveForward(750, 750);
        DriveBackward(80, 100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION, 1500);
        DriveForward(80, 80);
        DriveBackward(900, 900);
        strafeLeft(1500, 1500);
        turnRight(2800, 2800);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION, 500);
        DriveForward(1200, 1200);
        StopRobot(100);
        LiftArm(200, -1, 200);
        StopLiftArm(50);
        Grabbers(100, grabMax, 100);
        strafeRight(1000, 1000);
        DriveForward(500, 500);
        StopRobot(100);
        DriveBackward(800, 800);
        strafeLeft(1000, 1000);
        LiftArm(50, -1, 50);
        StopLiftArm(50);
        DriveBackward(900, 900);
        StopRobot(10);
        LiftArm(3000, 0.15, 2000);

        StopLiftArm(100);
        stop();
    }


    //BLUE  Paths
    public void BluePathRight() {
        telemetry.addLine("Target: Right");
        telemetry.update();
        strafeRight(1000, 1000);
        DriveBackward(600, 600);
        strafeRight(1800, 1800);
        StopRobot(50);
        Grabbers(50, grabMax, 200);
        DriveForward(200, 200);
        StopRobot(100);
        Grabbers(50, grabMin, 200);
        Intake(200, i_on, 200);
        strafeLeft(1500, 1500);
        StopRobot(50);
        Intake(50, off, 50);
        DriveForward(3600, 3600);
        StopRobot(50);
        LiftArm(700, 1, 700);
        StopLiftArm(100);
        turnRight(800, 800);
        DriveForward(750, 750);
        DriveBackward(80, 100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION, 1500);
        DriveForward(80, 80);
        DriveBackward(900, 900);
        strafeRight(1500, 1500);
        turnLeft(2800, 2800);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION, 500);
        DriveForward(1200, 1200);
        StopRobot(100);
        LiftArm(100, -0.6, 100);
        StopLiftArm(50);
        Grabbers(100, grabMax, 100);
        LiftArm(200, 1, 200);
        StopLiftArm(50);
        strafeLeft(1000, 1000);
        DriveForward(600, 600);
        StopRobot(100);
        DriveBackward(800, 800);
        strafeRight(1000, 1000);
        LiftArm(50, -1, 50);
        StopLiftArm(50);
        DriveBackward(900, 900);
        StopRobot(10);
        LiftArm(3000, 0.15, 2000);

        StopLiftArm(100);
        stop();
    }

    public void BluePathLeft() {
        telemetry.addLine("Target: Left");
        telemetry.update();

        strafeRight(1000, 1000);
        DriveBackward(150, 150);
        strafeRight(2050, 250);
        StopRobot(50);
        Grabbers(50, grabMax, 200);
        DriveForward(400, 400);
        StopRobot(100);
        Grabbers(50, grabMin, 200);
        Intake(200, i_on, 200);
        strafeLeft(1250, 1250);
        StopRobot(50);
        Intake(50, off, 50);
        DriveForward(3000, 3000);
        StopRobot(50);
        LiftArm(700, 1, 700);
        StopLiftArm(100);
        turnRight(800, 800);
        DriveForward(750, 750);
        DriveBackward(80, 100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION, 1500);
        DriveForward(80, 80);
        DriveBackward(900, 900);
        strafeRight(1500, 1500);
        turnLeft(2800, 2800);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION, 500);
        DriveForward(1100, 1100);
        StopRobot(100);
        LiftArm(200, -1, 200);
        StopLiftArm(50);
        Grabbers(100, grabMax, 100);
        LiftArm(200, 1, 200);
        StopLiftArm(50);
        strafeLeft(1000, 1000);
        DriveForward(500, 500);
        StopRobot(100);
        DriveBackward(800, 800);
        strafeRight(1000, 1000);
        LiftArm(50, -1, 50);
        StopLiftArm(50);
        DriveBackward(900, 900);
        StopRobot(10);
        LiftArm(3000, 0.15, 2000);

        StopLiftArm(100);
        stop();
    }

    public void BluePathCenter() {
        telemetry.addLine("Target: Center");
        telemetry.update();

        strafeRight(1000, 1000);
        DriveBackward(350, 350);
        strafeRight(1800, 1800);
        StopRobot(50);
        Grabbers(50, grabMax, 200);
        DriveForward(250, 250);
        StopRobot(100);
        Grabbers(50, grabMin, 200);
        Intake(200, i_on, 200);
        strafeLeft(1500, 1500);
        StopRobot(50);
        Intake(50, off, 50);
        DriveForward(3200, 3200);
        StopRobot(50);
        LiftArm(700, 1, 700);
        StopLiftArm(100);
        turnRight(800, 800);
        DriveForward(750, 750);
        DriveBackward(80, 100);
        StopRobot(100);
        Platform(1000, Servo.MAX_POSITION, 1500);
        DriveForward(80, 80);
        DriveBackward(900, 900);
        strafeRight(1500, 1500);
        turnLeft(2500, 2500);
        StopRobot(50);
        Platform(500, Servo.MIN_POSITION, 500);
        DriveForward(1200, 1200);
        StopRobot(100);
        LiftArm(200, -1, 200);
        StopLiftArm(50);
        Grabbers(100, grabMax, 100);
        LiftArm(200, 1, 200);
        StopLiftArm(50);
        strafeLeft(1000, 1000);
        DriveForward(500, 500);
        StopRobot(100);
        DriveBackward(800, 800);
        strafeRight(1000, 1000);
        LiftArm(50, -1, 50);
        StopLiftArm(50);
        DriveBackward(900, 900);
        StopRobot(10);
        LiftArm(3000, 0.15, 2000);

        StopLiftArm(100);
        stop();
    }
}
