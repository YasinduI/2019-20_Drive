package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import static java.lang.Math.sqrt;

@TeleOp(name = "MecanumDrive", group = "Opmode")


public class MecanumDrive extends OpMode {

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

        lift.setDirection(DcMotor.Direction.REVERSE);
        //lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Status", "Ready to run");
        telemetry.addData("gripl postion", GripLeft.getPosition());
        telemetry.addData("gripR postion", GripRight.getPosition());
    }
    @Override
    public void loop() {
        int min = -2050;
        int max = -100;


//Drive Train
        float Y2 = gamepad1.left_stick_y;
        float X2 = gamepad1.left_stick_x;
        float R2 = gamepad1.right_stick_x;

        float Y = (float) scaleInput(Y2);
        float X = (float) scaleInput(X2);
        float R = (float) scaleInput(R2);

        float FrontLeft = +X + -Y + R;
        float FrontRight = -X + -Y - R;
        float BackLeft = -X + Y + R;
        float BackRight = +X + Y - R;


        if (gamepad1.x) {
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

            telemetry.addData("SPEEDY BOY", "ON");
        }
        if (gamepad1.right_bumper) {
            FrontRight = (float) Range.clip(FrontRight, -0.15, 0.15);
            FrontLeft = (float) Range.clip(FrontLeft, -0.15, 0.15);
            BackLeft = (float) Range.clip(BackLeft, -0.15, 0.15);
            BackRight = (float) Range.clip(BackRight, -0.15, 0.15);


            telemetry.addData("PRECISE BOY", "ON");
        } else {
            FrontRight = (float) Range.clip(FrontRight, -0.5, 0.5);
            FrontLeft = (float) Range.clip(FrontLeft, -0.5, 0.5);
            BackLeft = (float) Range.clip(BackLeft, -0.5, 0.5);
            BackRight = (float) Range.clip(BackRight, -0.5, 0.5);


        }
        motorFrontLeft.setPower(FrontLeft);
        motorFrontRight.setPower(FrontRight);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);

        String liftstatus = LiftArm();
        String gripperstatus = GripperIntake();
        Double platformstatus = PlatformGrabber();


        telemetry.addData("liftstatus", liftstatus);
        telemetry.addData("liftposition", liftencodervalue());
        telemetry.addData("liftpower", lift.getPower());
        telemetry.addData("gripperstatus", gripperstatus, GripRight.getPosition(), GripLeft.getPosition());
        telemetry.addData("platform", platformstatus);
        telemetry.addData("Brake", lift.getZeroPowerBehavior());

    }

    double liftencodervalue() {
        double value = -lift.getCurrentPosition();

        return value;
    }

    double upvalue() {
        double distance = lift.getCurrentPosition();
        double power = 0;
        if (distance >= 1500) {
            power = 0.35;
        }
        return power;
    }

    double downvalue() {
        double distance = lift.getCurrentPosition();

        double power = 0;
        if (distance <= 1000 && distance > 200) {
            power = sqrt(distance / 1000);
        } else {
            power = -0.1;
        }

        return power;
    }


    String LiftArm() {

        int min = 10;
        int max = 1850;

        String status = "na";

        if (liftencodervalue() > min && liftencodervalue() < max) {
            if (gamepad2.dpad_up) {

                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(1);
            } else if (gamepad2.dpad_down) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(downvalue());
            } else {
                lift.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                // status = "brake";

            }
            status = String.valueOf(liftencodervalue());
        } else if (liftencodervalue() >= max) {
            if (gamepad2.dpad_down) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(downvalue());
            } else {
                lift.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //status = "maxbrake";
            }

            status = "MAX";
        } else if (liftencodervalue() <= min) {
            if (gamepad2.dpad_up) {
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                lift.setPower(1);
            } else {
                lift.setPower(0.1);
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //status = "minbrake";
            }
            status = "MIN";
        }


        return status;

    }

   /* double power (){

        double dpower = 0;

        double powerL = spinL.getPower();

        if  (powerL == 0){
            dpower = 1;
        }

        return dpower;

    }

    */

    String GripperIntake() {

        String power = "na";

        GripLeft.scaleRange(.5, .85);
        GripRight.scaleRange(.5, .9);
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

        return power;
    }

    double PlatformGrabber() {
        platform.scaleRange(0, 1);


        double max = .5;
        double min = .2;


        platform.setPosition(gamepad2.left_trigger);

        /*if (gamepad1.a) {
            platform.setPosition(max);
        }
        if (gamepad1.b) {
            platform.setPosition(min);
        }

         */
        return platform.getPosition();
    }

    double scaleInput(double dVal) {
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

    @Override
    public void stop() {

    }
}
