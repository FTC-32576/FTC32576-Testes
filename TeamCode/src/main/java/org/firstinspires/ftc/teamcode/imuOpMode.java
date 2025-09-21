package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "imuTestes")
public class imuOpMode extends OpMode {
    BNO055IMU imu;
    Orientation angles;
    @Override
    public void init() {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        Orientation angles = imu.getAngularOrientation(
                AxesReference.INTRINSIC, // ângulos medidos em relação ao robô
                AxesOrder.ZYX, // Ordem em que os valores sao retornados; FIRST ANGLE = Heading; Second Angle = Pitch; Third Angle = Roll
                AngleUnit.DEGREES // Unidade usada na medida no angulo - no caso, angulos. Pode usar radianos
        );
    }


    @Override
    public void loop() {

        telemetry.addData("IMU Heading", angles.firstAngle);
        telemetry.addData("IMU Pitch", angles.secondAngle);
        telemetry.addData("IMU Roll", angles.thirdAngle);
        telemetry.update();

    }
}
