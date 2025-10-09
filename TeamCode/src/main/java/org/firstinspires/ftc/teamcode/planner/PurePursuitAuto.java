package org.firstinspires.ftc.teamcode.planner;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.Arrays;
import java.util.List;

@Autonomous(name = "PurePursuitAuto")
public class PurePursuitAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
        Odometry odom = new Odometry(robot);

        List<Waypoint> path = Arrays.asList(
            new Waypoint(0.0, 0.0, 0.0),
            new Waypoint(60.0, 0.0, 0.0),
            new Waypoint(60.0, 60.0, 0.0),
            new Waypoint(30.0, 90.0, 0.0)
        );

        PathFollower follower = new PathFollower(path);

        waitForStart();

        while (opModeIsActive()) {
            odom.update();
            double[] powers = follower.follow(odom);

            robot.left.setPower(powers[0]);
            robot.right.setPower(powers[1]);

            telemetry.addLine("Pure Pursuit");
            telemetry.addData("X (cm)", "%.2f", odom.getX());
            telemetry.addData("Y (cm)", "%.2f", odom.getY());
            telemetry.addData("Heading (deg)", "%.2f", Math.toDegrees(odom.getHeading()));
            telemetry.addData("Left Pwr", "%.2f", powers[0]);
            telemetry.addData("Right Pwr", "%.2f", powers[1]);
            telemetry.update();
        }
    }
}
