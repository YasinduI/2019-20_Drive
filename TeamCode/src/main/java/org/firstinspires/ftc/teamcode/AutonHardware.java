package org.firstinspires.ftc.teamcode;

import android.widget.Spinner;

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
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.lang.annotation.Target;

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
    OpenCvCamera webcam;


    @Override
    public void runOpMode() throws InterruptedException {
        detectorstartup();

        startup();
    }

    public void detectorstartup() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech"), cameraMonitorViewId);

        SkystoneDetectorService skystoneDetectorService = new SkystoneDetectorService(webcam);

    }

    public int findSkystonePosActive() {

//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//       webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech"), cameraMonitorViewId);

        SkystoneDetectorService skystoneDetectorService = new SkystoneDetectorService(webcam);
        valLeft = skystoneDetectorService.getValLeft();
        valMid = skystoneDetectorService.getValMid();
        valRight = skystoneDetectorService.getValRight();
        int pos = 1;

        // determine which one has skystone

        if (opModeIsActive() &&valLeft <=0) {
            pos = 1;
        } else if(opModeIsActive() && valMid <= 0) {
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

    static final double s_on = .3;
    static final double d_on = .4;
    static final double off = 0;
    double grabMax = .9;
    double grabMin = .5;
    double platformMax = 1;
    double platformMin = 0;
    static final int i_on = 1;
    static final int i_off = 0;


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

    public void Intake (long time ,double spower,int stime){
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

    //RED Paths

    public void RedPathRight() {
        telemetry.addLine("Target: Right");
        telemetry.update();
        strafeRight(1100,1100);
        DriveBackward(800,800);
        turnRight(600,600);
        StopRobot(50);
        Grabbers(50,grabMax,200);
        DriveForward(600,600);
        StopRobot(100);
        Grabbers(50,grabMin,200);
        Intake(200,i_on,200);
        DriveBackward(500,500);
        turnLeft(700,700);
        StopRobot(50);
        Intake(50,off,50);
        DriveBackward(2100,2100);
        StopRobot(50);
        LiftArm(700,1,700);
        StopLiftArm(100);
        turnRight(800,800);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(800,800);
        strafeLeft(1500,1500);
        turnRight(2800,2800);
        StopRobot(50);
        Platform(500,Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        LiftArm(200,-1,200);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        LiftArm(200,1,200);
        StopLiftArm(50);
        strafeRight(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeLeft(1000,1000);
        LiftArm(100,-1,100);
        StopLiftArm(50);
        DriveBackward(900,900);
        StopRobot(100);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();
    }

    public void RedPathLeft() {
        telemetry.addLine("Target: Left");
        telemetry.update();

        strafeRight(1000,1000);
        DriveBackward(200,200);
        strafeRight(1750,1750);
        StopRobot(50);
        Grabbers(50,grabMax,200);
        DriveForward(200,200);
        StopRobot(100);
        Grabbers(50,grabMin,200);
        Intake(200,i_on,200);
        strafeLeft(1250,1250);
        StopRobot(50);
        Intake(50,off,50);
        DriveBackward(3050,3050);
        StopRobot(50);
        LiftArm(700,1,700);
        StopLiftArm(100);
        turnRight(800,800);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(900,900);
        strafeLeft(1500,1500);
        turnRight(2800,2800);
        StopRobot(50);
        Platform(500,Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        LiftArm(200,-1,200);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        LiftArm(200,1,200);
        StopLiftArm(50);
        strafeRight(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeLeft(1000,1000);
        LiftArm(50,-1,50);
        StopLiftArm(50);
        DriveBackward(900,900);
        StopRobot(100);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();

    }

    public void RedPathCenter() {
        telemetry.addLine("Target: Center");
        telemetry.update();

        strafeRight(1000,1000);
        DriveBackward(480,180);
        strafeRight(1750,1750);
        StopRobot(50);
        Grabbers(50,grabMax,200);
        DriveForward(200,200);
        StopRobot(100);
        Grabbers(50,grabMin,200);
        Intake(200,i_on,200);
        strafeLeft(1500,1500);
        StopRobot(50);
        Intake(50,off,50);
        DriveBackward(2600,2600);
        StopRobot(50);
        LiftArm(700,1,700);
        StopLiftArm(100);
        turnRight(800,800);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(900,900);
        strafeLeft(1500,1500);
        turnRight(2800,2800);
        StopRobot(50);
        Platform(500,Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        LiftArm(200,-1,200);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        strafeRight(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeLeft(1000,1000);
        LiftArm(50,-1,50);
        StopLiftArm(50);
        DriveBackward(900,900);
        StopRobot(10);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();
    }

    public void RedRunPath(){

        if(opModeIsActive() && findSkystonePosActive() == 1){
            RedPathLeft();
        }
        else if (opModeIsActive() && findSkystonePosActive() == 2){
            RedPathCenter();
        }
        else if(opModeIsActive() && findSkystonePosActive() == 3){
            RedPathRight();
        }



    }


    //BLUE  Paths
    public void BluePathRight() {
        telemetry.addLine("Target: Right");
        telemetry.update();
        strafeRight(1000,1000);
        DriveBackward(600,600);
        strafeRight(1800,1800);
        StopRobot(50);
        Grabbers(50,grabMax,200);
        DriveForward(200,200);
        StopRobot(100);
        Grabbers(50,grabMin,200);
        Intake(200,i_on,200);
        strafeLeft(1500,1500);
        StopRobot(50);
        Intake(50,off,50);
        DriveForward(3600,3600);
        StopRobot(50);
        LiftArm(700,1,700);
        StopLiftArm(100);
        turnRight(800,800);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(900,900);
        strafeRight(1500,1500);
        turnLeft(2800,2800);
        StopRobot(50);
        Platform(500,Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        LiftArm(200,-1,200);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        strafeLeft(1000,1000);
        DriveForward(600,600);
        StopRobot(100);
        DriveBackward(800,800);
        strafeRight(1000,1000);
        LiftArm(50,-1,50);
        StopLiftArm(50);
        DriveBackward(900,900);
        StopRobot(10);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();
    }

    public void BluePathLeft() {
        telemetry.addLine("Target: Left");
        telemetry.update();

        strafeRight(1000,1000);
        DriveBackward(150,150);
        strafeRight(1800,1800);
        StopRobot(50);
        Grabbers(50,grabMax,200);
        DriveForward(200,200);
        StopRobot(100);
        Grabbers(50,grabMin,200);
        Intake(200,i_on,200);
        strafeLeft(1250,1250);
        StopRobot(50);
        Intake(50,off,50);
        DriveForward(3000,3000);
        StopRobot(50);
        LiftArm(700,1,700);
        StopLiftArm(100);
        turnRight(800,800);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(900,900);
        strafeRight(1500,1500);
        turnLeft(2800,2800);
        StopRobot(50);
        Platform(500,Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        LiftArm(200,-1,200);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        strafeLeft(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeRight(1000,1000);
        LiftArm(50,-1,50);
        StopLiftArm(50);
        DriveBackward(900,900);
        StopRobot(10);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();
    }

    public void BluePathCenter() {
        telemetry.addLine("Target: Center");
        telemetry.update();

        strafeRight(1000,1000);
        DriveBackward(350,350);
        strafeRight(1800,1800);
        StopRobot(50);
        Grabbers(50,grabMax,200);
        DriveForward(250,250);
        StopRobot(100);
        Grabbers(50,grabMin,200);
        Intake(200,i_on,200);
        strafeLeft(1500,1500);
        StopRobot(50);
        Intake(50,off,50);
        DriveForward(3200,3200);
        StopRobot(50);
        LiftArm(700,1,700);
        StopLiftArm(100);
        turnRight(800,800);
        DriveForward(750,750);
        DriveBackward(80,100);
        StopRobot(100);
        Platform(1000,Servo.MAX_POSITION,1500);
        DriveForward(80,80);
        DriveBackward(900,900);
        strafeRight(1500,1500);
        turnLeft(2500,2500);
        StopRobot(50);
        Platform(500,Servo.MIN_POSITION,500);
        DriveForward(1200,1200);
        StopRobot(100);
        LiftArm(200,-1,200);
        StopLiftArm(50);
        Grabbers(100,grabMax,100);
        strafeLeft(1000,1000);
        DriveForward(500,500);
        StopRobot(100);
        DriveBackward(800,800);
        strafeRight(1000,1000);
        LiftArm(50,-1,50);
        StopLiftArm(50);
        DriveBackward(900,900);
        StopRobot(10);
        LiftArm(3000,0.15,2000);

        StopLiftArm(100);
        stop();
    }

    public void BlueRunPath(){

        if(opModeIsActive() && findSkystonePosActive() == 1){
            BluePathLeft();
        }
        else if (opModeIsActive() && findSkystonePosActive() == 2){
            BluePathCenter();
        }
        else if(opModeIsActive() && findSkystonePosActive() == 3){
            BluePathRight();
        }


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