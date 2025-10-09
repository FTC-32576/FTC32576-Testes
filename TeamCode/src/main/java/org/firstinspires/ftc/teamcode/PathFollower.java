package org.firstinspires.ftc.teamcode;

import java.util.List;

public class PathFollower {
    private List<Waypoint> path;
    private double lookahead = 10.0; // cm
    private double lastError = 0.0;

    // PD gains
    private double kP_turn = 2.0;
    private double kD_turn = 0.25;

    private MotionProfile profile = new MotionProfile();

    public PathFollower(List<Waypoint> path) {
        this.path = path;
    }

    // encontra o ponto lookahead na polilinha do path
    public Waypoint getLookahead(double x, double y) {
        for (int i = 0; i < path.size() - 1; i++) {
            Waypoint start = path.get(i);
            Waypoint end   = path.get(i + 1);

            double dx = end.x - start.x;
            double dy = end.y - start.y;
            double fx = start.x - x;
            double fy = start.y - y;

            double a = dx*dx + dy*dy;
            double b = 2*(fx*dx + fy*dy);
            double c = fx*fx + fy*fy - lookahead*lookahead;
            double disc = b*b - 4*a*c;

            if (disc >= 0) {
                double t = (-b + Math.sqrt(disc)) / (2*a);
                if (t >= 0 && t <= 1) {
                    return new Waypoint(start.x + t*dx, start.y + t*dy, 0.0);
                }
            }
        }
        // se nada encontrado, retorna último ponto
        return path.get(path.size()-1);
    }

    // Retorna array {leftPower, rightPower}
    public double[] follow(Odometry odom) {
        double x = odom.getX();
        double y = odom.getY();
        double heading = odom.getHeading();

        Waypoint target = getLookahead(x, y);

        double dx = target.x - x;
        double dy = target.y - y;
        double targetHeading = Math.atan2(dy, dx);

        double error = normalizeAngle(targetHeading - heading);
        double derivative = error - lastError;
        lastError = error;

        double turn = kP_turn * error + kD_turn * derivative;

        // base speed from motion profile
        double base = profile.update(true);

        // differential tank mixing
        double left = base - turn;
        double right = base + turn;

        // clamp to [-1, 1]
        left = Math.max(-1.0, Math.min(1.0, left));
        right = Math.max(-1.0, Math.min(1.0, right));

        return new double[] { left, right };
    }

    // normalize angle to [-PI, PI]
    private double normalizeAngle(double ang) {
        while (ang > Math.PI) ang -= 2*Math.PI;
        while (ang < -Math.PI) ang += 2*Math.PI;
        return ang;
    }

    public void resetProfile() { profile.reset(); }
}
