package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "RedAuto")
public class RedAuto extends LinearOpMode {
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftLeft;
    DcMotor Intake1;
    DcMotor Intake2;
    CRServo BoxMotor;
    double power = 1; // This can change if needed
    @Override
    public void runOpMode() {
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        Intake1 = hardwareMap.dcMotor.get("Intake1");
        Intake2 = hardwareMap.dcMotor.get("Intake2");
        LiftLeft = hardwareMap.dcMotor.get("LiftLeft");
        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);
        LiftLeft.setDirection(DcMotor.Direction.FORWARD);
        Intake1.setDirection(DcMotor.Direction.REVERSE);
        Intake2.setDirection(DcMotor.Direction.FORWARD);
        BoxMotor = hardwareMap.get(CRServo.class, "BoxMotor");
        LiftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Send telemetry message to signify robot waiting;
        //This shows up on the phone after everything has been initialized
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //Drive Forward
        DriveBackward(300);
        DriveForward(300);
        strafeLeft(300);
        strafeRight(300);
        turnLeft(300);
        turnRight(300);
        //Stop Robot
        //All your methods go here
        // send the info back to driver station using telemetry function.
        //When done running, this message will appear on the phone
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
    //Implement Methods
    public void StopRobot() { //Method for stopping the robot
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
    }
    //long time is a parameter
    public void DriveForward(long time) {
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(-power);
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
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(-power);
        sleep(time);
        StopRobot();
    }
    public void IntakeIn(long time) {
        Intake1.setPower(1);
        Intake2.setPower(1);
        sleep(time);
        StopRobot();
    }
    public void IntakeOut(long time) {
        Intake1.setPower(1);
        Intake2.setPower(1);
        sleep(time);
        StopRobot();
    }
    public void LiftUp(long time) {
        LiftLeft.setPower(1);
        sleep(time);
        StopRobot();
    }
    public void LiftDown(long time) {
        LiftLeft.setPower(-1);
        sleep(time);
        StopRobot();
    }
    public void boxOut(long time) {
        BoxMotor.setPower(-1);
        sleep(time);
        StopRobot();
    }
    public void boxIn(long time) {
        BoxMotor.setPower(1);
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