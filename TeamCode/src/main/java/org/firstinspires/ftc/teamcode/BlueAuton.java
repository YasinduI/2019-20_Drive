package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "BlueAuton")
public class BlueAuton extends LinearOpMode {
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
    double power = 0.3; // This can change if needed
    @Override
    public void runOpMode() throws InterruptedException {
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
        GripLeft.scaleRange(.5,.85);
        GripRight.scaleRange(.5,.9);

        // Send telemetry message to signify robot waiting;
        //This shows up on the phone after everything has been initialized
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //Drive Forward
        //Stop Robot
        //All your methods go here
        DriveForward(800);
        strafeRight(1000);
        // send the info back to driver station using telemetry function.
        //When done running, this message will appear on the phone




        telemetry.addData("Path", "Comple te");
        telemetry.update();
        sleep(1000);
    }
    //Implement Methods
    public void Gripper (Double Position){
        GripLeft.setPosition(Position);
        GripRight.setPosition(Position);
    }


    public void Lift(Long time , double power){
        lift.setPower(power);
        sleep(time);

    }

    public void StopRobot() { //Method fo r stopping the robot
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
    }
    //long time is a parameter
    public void DriveBackward(long time) {
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(-power);
        sleep(time);
    }
    public void turnRight(long time) {
        motorFrontLeft.setPower(-1);
        motorBackRight.setPower(-0.8);
        motorBackLeft.setPower(-1);
        motorFrontRight.setPower(-0.8);
        sleep(time);
        StopRobot();
    }
    public void turnLeft(long time) {
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(power);
        sleep(time);
        StopRobot();
    }

    public void strafeRight(long time) {
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(-power);
        motorFrontRight.setPower(-power);
        sleep(time);
        StopRobot();
    }
    public void DriveForward(long time) {
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontRight.setPower(power);
        sleep(time);
        StopRobot();
    }
    public void GrabPlatform() {
        platform.setPosition(Servo.MAX_POSITION);
    }
    public void UngrabPlatform() {
        platform.setPosition(Servo.MIN_POSITION);
    }
}

