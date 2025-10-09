package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class autonoumo extends BasicOpMode_Linear{

    void tank(float powerLeft, float powerRight){
        leftDrive.setPower(powerLeft);
        rightDrive.setPower(powerRight);
    }

    void turnage(float angulo, float velocidade){




    }
}
