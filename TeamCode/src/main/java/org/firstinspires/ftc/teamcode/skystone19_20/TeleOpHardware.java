package org.firstinspires.ftc.teamcode.skystone19_20;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Math.sqrt;

public class TeleOpHardware extends OpMode {

    public DcMotor motorFrontRight;
    public DcMotor motorFrontLeft;
    public DcMotor motorBackRight;
    public DcMotor motorBackLeft;
    public DcMotor lift;
    public DcMotor lifthelper;
    public DcMotor tapemeasure;
    public Servo GripLeft;
    public Servo GripRight;
    public Servo platformR;
    public Servo platformL;
    public CRServo spinR;
    public CRServo spinL;
    public Servo cap;
    public Servo Arm1;
    public Servo Arm2;

    @Override
    public void init() {

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

        cap = hardwareMap.get(Servo.class, "cap");
        Arm1 = hardwareMap.get(Servo.class,"arm1");
        Arm2 = hardwareMap.get(Servo.class,"arm2");

        cap.setDirection(Servo.Direction.REVERSE);


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

        platformL.setDirection(Servo.Direction.REVERSE);
        platformR.setDirection(Servo.Direction.FORWARD);

        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lifthelper = hardwareMap.get(DcMotor.class,"lifthelper");
        lifthelper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifthelper.setDirection(DcMotor.Direction.REVERSE);

        tapemeasure = hardwareMap.get(DcMotor.class,"tape");


    }

    @Override
    public void loop() {

    }

    public void getlift() {

        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setDirection(DcMotor.Direction.REVERSE);


        //lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    //Functions for Driving


    public void Driving() {


        float Y2 = gamepad1.left_stick_y;
        float X2 = gamepad1.left_stick_x;
        float R2 = gamepad1.right_stick_x;

        float Y = (float) scaleInput(Y2);
        float X = (float) scaleInput(X2);
        float R = (float) scaleInput(R2);

        float FrontLeft = +X - Y + R;
        float FrontRight = - X - Y - R;
        float BackLeft = - X - Y + R;
        float BackRight = +X - Y - R;


        if (gamepad1.x) {
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

            telemetry.addData("SPEEDY BOY", "ON");

        } else if (gamepad1.right_bumper) {
            FrontRight = (float) Range.clip(FrontRight, -0.3, 0.3);
            FrontLeft = (float) Range.clip(FrontLeft, -0.3, 0.3);
            BackLeft = (float) Range.clip(BackLeft, -0.3, 0.3);
            BackRight = (float) Range.clip(BackRight, -0.3, 0.3);


            telemetry.addData("PRECISE BOY", "ON");
        } else {
            FrontRight = (float) Range.clip(FrontRight, -0.55, 0.55);
            FrontLeft = (float) Range.clip(FrontLeft, -0.55, 0.55);
            BackLeft = (float) Range.clip(BackLeft, -0.55, 0.55);
            BackRight = (float) Range.clip(BackRight, -0.55, 0.55);


        }
        if (X + Y + R == 0) {
            motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }

        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);

    }

    public String returndrivevalue() {
        getlift();
        String Speed;

        double FL = motorFrontLeft.getPower();
        double FR = motorFrontRight.getPower();
        double BL = motorBackLeft.getPower();
        double BR = motorBackRight.getPower();

        Speed = FL + "," + FR + "," + BL + "," + BR;

        return Speed;

    }

    public String GripperIntake() {

        String power = "na";

        GripLeft.scaleRange(.2, .5);
        GripRight.scaleRange(.51, .8);
        GripLeft.setPosition(gamepad1.right_trigger);
        GripRight.setPosition(gamepad1.right_trigger);

        if (gamepad2.right_bumper) {
            spinL.setPower(1);
            spinR.setPower(1);

            power = String.valueOf(spinL.getPower());
        }
        if (gamepad2.left_bumper) {
            spinL.setPower(0);
            spinR.setPower(0);

            power = String.valueOf(spinL.getPower());
        }

        return (power + "," + "L" + GripLeft.getPosition() + "," + "R" + GripRight.getPosition());
    }

    public void PlatformGrabber() {

        platformL.scaleRange(.3, 1);
        platformR.scaleRange(0, 0.7);

        platformL.setPosition(gamepad2.left_trigger);
        platformR.setPosition(gamepad2.right_trigger);

        return;

    }

    private double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

    private double liftencodervalue() {
        getlift();
        double value = -lift.getCurrentPosition();

        return value;
    }

    private double upvalue() {
        getlift();
        double distance = lift.getCurrentPosition();
        double power = 0;
        if (distance >= 1500) {
            power = 0.35;
        }
        return power;
    }

    private double downvalue() {
        getlift();
        double distance = lift.getCurrentPosition();

        double power = 0;
        if (distance <= 1000 && distance > 200) {
            power = sqrt(distance / 1000);
        } else {
            power = -0.1;
        }

        return power;
    }

    public String LiftArm() {
        getlift();

        int min = 10;
        int max = 1800;

        String status = "na";

        if (liftencodervalue() > min && liftencodervalue() < max) {
            if (gamepad2.dpad_up) {

                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(1);
                lifthelper.setPower(1);
            } else if (gamepad2.dpad_down) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(downvalue());
                lifthelper.setPower(downvalue());
            } else {
                lift.setPower(0.1);
                lifthelper.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                // status = "brake";

            }
            status = String.valueOf(liftencodervalue());
        } else if (liftencodervalue() >= max) {
            if (gamepad2.dpad_down) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(downvalue());
                lifthelper.setPower(downvalue());
            } else {
                lift.setPower(0.1);
                lifthelper.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //status = "maxbrake";
            }

            status = "MAX";
        } else if (liftencodervalue() <= min) {
            if (gamepad2.dpad_up) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(1);
                lifthelper.setPower(1);
            } else {
                lift.setPower(0.1);
                lifthelper.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //status = "minbrake";
            }
            status = "MIN";
        }


        return status;

    }

    public String Capstone() {

        String status = "Locked";
        if (gamepad2.x) {
            cap.setPosition(0);
            status = "Released";
        } else if (!gamepad2.x) {
            cap.setPosition(0.8);
        }
        return status;
    }

    public String SideArm (){

        String ArmValue = String.valueOf(Arm1.getPosition()+ ","+Arm2.getPosition());
        Arm1.scaleRange(0.1, 0.7);
        Arm2.scaleRange(0, 1);
        if(gamepad1.y){
            Arm1.setPosition(0);
        }
        if(gamepad1.x){
            Arm1.setPosition(1);
        }
        if (gamepad1.b){
            Arm2.setPosition(0);
        }
        else if(gamepad1.a){
            Arm2.setPosition(1);
        }

        return ArmValue;
    }


    public void Tape (){
        tapemeasure.setPower(gamepad2.left_stick_y);
    }


}

