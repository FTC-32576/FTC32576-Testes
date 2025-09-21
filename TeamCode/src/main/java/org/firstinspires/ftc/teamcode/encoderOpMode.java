package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "encoderTestes")
public class encoderOpMode extends OpMode {

    DcMotor motor;
    int ticks = 28;
    double newTarget;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motorDireita");
        telemetry.addData("Hardware", "Inicializado");
        telemetry.update();
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    @Override
    public void loop() {
    if(gamepad1.a) {
        encoder(1);
    }

    telemetry.addData("Motor Ticks", motor.getCurrentPosition());
    }

    public void encoder(int rotacoes){

        newTarget = ticks * rotacoes;
        motor.setTargetPosition((int)newTarget);
        motor.setPower(0.1);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void resetEncoder(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void trackerToZero(){
        motor.setTargetPosition(0);
        motor.setPower(0.1);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
