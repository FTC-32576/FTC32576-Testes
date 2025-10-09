package org.firstinspires.ftc.teamcode.planner;

public class MotionProfile {
    private double accel = 0.01;    // incremento por loop
    public double maxSpeed = 0.6;   // velocidade máxima base
    private double speed = 0.0;

    public MotionProfile() {}
    public MotionProfile(double accel, double maxSpeed) {
        this.accel = accel;
        this.maxSpeed = maxSpeed;
    }

    // moving == true quando queremos acelerar; false quando queremos reduzir
    public double update(boolean moving) {
        if (moving) speed = Math.min(maxSpeed, speed + accel);
        else speed = Math.max(0.0, speed - accel * 2.0);
        return speed;
    }

    public void reset() { speed = 0.0; }
}
