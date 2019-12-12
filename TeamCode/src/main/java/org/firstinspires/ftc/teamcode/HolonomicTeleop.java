

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;
import static com.qualcomm.robotcore.hardware.Servo.MAX_POSITION;
import static com.qualcomm.robotcore.hardware.Servo.MIN_POSITION;


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
@TeleOp(name = "HolonomicDrivetrain", group = "Concept")
@Disabled
public class HolonomicTeleop extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftLeft;
    DcMotor LiftRight;
    DcMotor Intake1;
    DcMotor Intake2;
    CRServo BoxMotor;
    Servo GripLeft;
    Servo GripRight;
    Servo Hitter;
    TouchSensor Unload;
    TouchSensor Load;


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

        //Motors//
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        //Intake//
        Intake1 = hardwareMap.dcMotor.get("Intake1");
        Intake2 = hardwareMap.dcMotor.get("Intake2");
        //Lift//
        LiftLeft = hardwareMap.dcMotor.get("LiftLeft");
        LiftRight = hardwareMap.dcMotor.get("LiftRight");
        //Extras//
        BoxMotor = hardwareMap.get(CRServo.class, "BoxMotor");
        GripLeft = hardwareMap.get(Servo.class, "GripLeft");
        GripRight = hardwareMap.get(Servo.class, "GripRight");
        Hitter = hardwareMap.get(Servo.class, "Hitter");
        //Sensors//
        Unload = hardwareMap.get(TouchSensor.class,"Unload" );
        Load = hardwareMap.get(TouchSensor.class,"LOad");


        //Direction//
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);

        LiftLeft.setDirection(DcMotor.Direction.FORWARD);
        LiftRight.setDirection(DcMotor.Direction.REVERSE);

        Intake1.setDirection(DcMotor.Direction.REVERSE);
        Intake2.setDirection(DcMotor.Direction.FORWARD);

        //Servos//
        GripRight.setDirection(FORWARD);
        GripLeft.setDirection(REVERSE);
        Hitter.setDirection(REVERSE);
        Hitter.scaleRange(-0.5,1);
        //Encoder Motors//
        LiftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("ChingChong", "Ready");
    }

    @Override
    public void loop() {
        //Max and Min for Lift//
        int min = -1750;
        int max = -100;

        int mina = 0;
        int maxb =200;

        LiftLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LiftRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        float gamepad1LeftY = gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        float FrontLeft = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackLeft = gamepad1LeftY - -gamepad1LeftX - gamepad1RightX;
        float BackRight = -gamepad1LeftY - -gamepad1LeftX - gamepad1RightX;

//Intake//

        if (gamepad2.right_bumper) {
            Intake1.setPower(.3);
            Intake2.setPower(.3);

        }
        else if (gamepad2.left_bumper) {
            Intake1.setPower(-.3);
            Intake2.setPower(-.3);

        }
        else {
            Intake1.setPower(0);
            Intake2.setPower(0);
        }

//Lift//

        if (LiftLeft.getCurrentPosition() > min && LiftLeft.getCurrentPosition() < max)
            if (gamepad2.dpad_up) {
                LiftLeft.setPower(1);
                LiftRight.setPower(1);
            } else if (gamepad2.dpad_down) {
                LiftLeft.setPower(-1);
                LiftRight.setPower(-1);
            } else {
                LiftLeft.setPower(0);
                LiftRight.setPower(0);
            }
        else if (LiftLeft.getCurrentPosition() <= min)
            if (gamepad2.dpad_down) {
                LiftLeft.setPower(-1);
                LiftRight.setPower(-1);
            } else {
                LiftLeft.setPower(0);
                LiftRight.setPower(0);
            }
        if (LiftLeft.getCurrentPosition() >= max)
            if (gamepad2.dpad_up) {
                LiftLeft.setPower(1);
                LiftRight.setPower(1);
            } else {
                LiftLeft.setPower(0);
                LiftRight.setPower(0);
            }


//Grippers//

        GripLeft.setPosition(gamepad2.right_trigger);
        GripRight.setPosition(gamepad2.right_trigger);

//Hitter//
        Hitter.setPosition(-gamepad2.right_stick_y);

//Box Motor//

        if(!Unload.isPressed()){

            if(gamepad2.y){
                BoxMotor.setPower(1);
            }
        }
        if(!Load.isPressed()){

            if(gamepad2.a){
                BoxMotor.setPower(-1);
            }
        }

        if (Load.isPressed()&& !gamepad2.a && !gamepad2.y){
            BoxMotor.setPower(0);
        }
        if (Unload.isPressed()&& !gamepad2.y && !gamepad2.a) {

            BoxMotor.setPower(0);
        }






        // clip the right/left values so that the values never exceed +/- 1
        if (gamepad1.left_bumper) {
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

            telemetry.addData("SPEEDY BOY","ON");
        }
        if (gamepad1.right_bumper) {
            FrontRight = (float) Range.clip(FrontRight, -0.5, 0.5);
            FrontLeft = (float) Range.clip(FrontLeft, -0.5, 0.5);
            BackLeft = (float) Range.clip(BackLeft, -0.5, 0.5);
            BackRight = (float) Range.clip(BackRight, -0.5, 0.5);

            telemetry.addData("PRECISE BOY","ON");

        }




        // write the values to the motors
        motorFrontRight.setPower(FrontRight);
        motorFrontLeft.setPower(FrontLeft);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);



        /*
         * Telemetry for debugging
         */
        telemetry.addData("LiftLeft", LiftLeft.getCurrentPosition() + "  busy=" + LiftLeft.isBusy());
        telemetry.addData("unload", Unload.getValue());
        telemetry.addData("load", Load.getValue());
        telemetry.addData("BoxPosition",BoxMotor.getPower());

    }



    @Override
    public void stop (){

        telemetry.addData("Good Job Drivers","");

    }
}
