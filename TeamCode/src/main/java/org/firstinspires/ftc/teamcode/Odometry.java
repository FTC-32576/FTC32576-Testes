package org.firstinspires.ftc.teamcode;

public class Odometry {
    private Robot robot;
    // Ajustar conforme a roda (ex: raio em cm)
    private double wheelRadius = 3.0; // cm
    // HD HEX tem 28 ticks por rotação do motor.
    // ajustar GEAR_RATIO (por exemplo 20 para 20:1).
    private static final double GEAR_RATIO = 20.0;
    private double ticksPerRev = 28.0 * GEAR_RATIO;
    public double trackWidth = 30.0; // distância entre rodas em cm (ajuste)

    private double x = 0, y = 0, heading = 0;
    private int lastLeft = 0, lastRight = 0;

    public Odometry(Robot robot) {
        this.robot = robot;
    }

    public void update() {
        int leftPos = robot.left.getCurrentPosition();
        int rightPos = robot.right.getCurrentPosition();

        int dL = leftPos - lastLeft;
        int dR = rightPos - lastRight;

        lastLeft = leftPos;
        lastRight = rightPos;

        double dLeft = (dL / ticksPerRev) * 2.0 * Math.PI * wheelRadius;
        double dRight = (dR / ticksPerRev) * 2.0 * Math.PI * wheelRadius;
        double dCenter = (dLeft + dRight) / 2.0;

        heading = robot.getYaw(); // em radianos



        x += dCenter * Math.cos(heading);
        y += dCenter * Math.sin(heading);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getHeading() { return heading; }
    public double getTrackWidth() { return trackWidth; }
}
