package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "BlueAuto")
public class BlueAuto extends LinearOpMode {

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


    double power = 1; // This can change if needed
    @Override
    public void runOpMode() {
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



        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);
        spinR.setDirection(CRServo.Direction.FORWARD);
        spinL.setDirection(CRServo.Direction.REVERSE);

        // Send telemetry message to signify robot waiting;
        //This shows up on the phone after everything has been initialized
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //Drive Forward
        //Stop Robot
        //All your methods go here
        // send the info back to driver station using telemetry function.
        //When done running, this message will appear on the phone
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
    //Implement Methods
    public void StopRobot() { //Method fo r stopping the robot
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
    }
    //long time is a parameter
    public void DriveForward(long time) {
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(power);
        sleep(time);
        StopRobot();
    }
    public void turnRight(long time) {
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontRight.setPower(-power);
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
    public void DriveBackward(long time) {
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontRight.setPower(-power);
        sleep(time);
        StopRobot();
    }

    public void strafeLeft(long time) {
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontRight.setPower(power);
        sleep(time);
        StopRobot();
    }
    public void strafeRight(long time) {
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(-power);
        sleep(time);
        StopRobot();
    }
}