

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/*

   Robot wheel mapping:

          X FRONT X
        X           X
      X  FL       FR  X
              X
             XXX
              X
      X  BL       BR  X
        X           X
          X       X
*/
@TeleOp(name = "Concept: HolonomicDrivetrain", group = "Concept")
//@Disabled
public class HolonomicTeleop extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftRight;
    DcMotor LiftLeft;
    DcMotor Intake1;
    DcMotor Intake2;

    double LL;
    double LR;

    boolean changed; //Outside of loop()





    /**
     * Constructor
     */
    public HolonomicTeleop() {

    }

    @Override
    public void init() {


        /*
         * Use the hardwareMap to get the dc motors and servos by name. Note
         * that the names of the devices must match the names used when you
         * configured your robot and created the configuration file.
         */


        //motorFrontRight = hardwareMap.dcMotor.get("motor front right");
        //motorFrontLeft = hardwareMap.dcMotor.get("motor front left");
        //motorBackLeft = hardwareMap.dcMotor.get("motor back left");
        //motorBackRight = hardwareMap.dcMotor.get("motor back right");
        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);
        LiftRight.setDirection(DcMotor.Direction.REVERSE);
        LiftLeft.setDirection(DcMotor.Direction.FORWARD);
        Intake1.setDirection(DcMotor.Direction.REVERSE);
        Intake2.setDirection(DcMotor.Direction.FORWARD);

    }

    @Override
    public void loop() {


        int min = 0;
        int max = 1800;
        // left stick controls direction
        // right stick X controls rotation

        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;
        // Holonomic formulas

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        boolean changed = false; //Outside of loop()
        if (gamepad1.right_bumper && !changed) {
            if (LiftLeft.getPower() == 0) {
                LiftLeft.setPower(1);
                LiftRight.setPower(1);
            } else LiftLeft.setPower(0);
            changed = true;
        } else if
        (!gamepad1.right_bumper) changed = false;


        if (LiftLeft.getCurrentPosition() > min && LiftLeft.getCurrentPosition() < max)
            if (gamepad1.dpad_up) {
                LiftLeft.setPower(1);
                LiftRight.setPower(1);
            } else if (gamepad1.dpad_down) {
                LiftRight.setPower(-1);
                LiftLeft.setPower(-1);
            } else {
                LiftLeft.setPower(0);
                LiftRight.setPower(0);
            }
        else if (LiftLeft.getCurrentPosition() >= max)
            if (gamepad1.dpad_down) {
                LiftLeft.setPower(-1);
                LiftRight.setPower(-1);
            } else {
                LiftLeft.setPower(0);
                LiftRight.setPower(0);
            }
        if (LiftLeft.getCurrentPosition() <= min)
            if (gamepad1.dpad_up) {
                LiftLeft.setPower(1);
                LiftRight.setPower(1);
            } else {
                LiftLeft.setPower(0);
                LiftRight.setPower(0);
            }



        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);


        // write the values to the motors
        motorFrontRight.setPower(FrontRight);
        motorFrontLeft.setPower(FrontLeft);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);
        LiftLeft.setPower(LL);
        LiftRight.setPower(LR);



        /*
         * Telemetry for debugging
         */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR", String.format("%.2f", gamepad1LeftX) + " " +
                String.format("%.2f", gamepad1LeftY) + " " + String.format("%.2f", gamepad1RightX));
        telemetry.addData("f left pwr", "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));


    }
    @Override
    public void stop() {

    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

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

}