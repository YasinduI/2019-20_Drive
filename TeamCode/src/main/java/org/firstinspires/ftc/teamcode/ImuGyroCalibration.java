package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;

@TeleOp(name= "Gyro Calibration")

public class ImuGyroCalibration extends LinearOpMode {

    private BNO055IMU imu;
    private ModernRoboticsI2cGyro gyro;

    @Override
    public void runOpMode() throws InterruptedException {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);


        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class,"gyro");
        gyro.calibrate();

        telemetry.addLine("ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {



            telemetry.addData("gyro", gyro.getIntegratedZValue());

            telemetry.update();
        }


    }
}
