package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Paths.Blue.LeftLeft_Paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;

import org.firstinspires.ftc.teamcode.Systems.DriveBase.drive.DriveConstants;

import java.util.Arrays;

import static org.firstinspires.ftc.teamcode.Systems.DriveBase.drive.DriveConstants.MAX_ACCEL;
import static org.firstinspires.ftc.teamcode.Systems.DriveBase.drive.DriveConstants.MAX_ANG_VEL;
import static org.firstinspires.ftc.teamcode.Systems.DriveBase.drive.DriveConstants.MAX_VEL;
import static org.firstinspires.ftc.teamcode.Systems.DriveBase.drive.DriveConstants.TRACK_WIDTH;

@Config
public class Blue_LeftLeft_1RingPath {

    public static Trajectory BLL1_traj1 = BuildTrajectory(new Pose2d())
            .lineToSplineHeading(new Pose2d(60, 0, Math.toRadians(325.875)),
                    new MinVelocityConstraint(
                            Arrays.asList(
                                    new AngularVelocityConstraint(Math.toRadians(60)),
                                    new MecanumVelocityConstraint(MAX_VEL, DriveConstants.TRACK_WIDTH)
                            )),
                    new ProfileAccelerationConstraint(DriveConstants.MAX_ACCEL))
            .build();

    public static Trajectory BLL1_traj2 = BuildTrajectory(BLL1_traj1.end())
            .lineToSplineHeading(new Pose2d(90,-42,0))
            .build();

    public static Trajectory BLL1_traj3 = BuildTrajectory(BLL1_traj2.end())
            .lineToSplineHeading(new Pose2d(117,-40,Math.toRadians(315)))
            .build();

    public static Trajectory BLL1_traj4 = BuildTrajectory(BLL1_traj3.end())
            .lineToConstantHeading(new Vector2d(112.5,-62.5))
            .build();

    public static Trajectory BLL1_traj5 = BuildTrajectory(BLL1_traj4.end())
            .lineToSplineHeading(new Pose2d(18,-22, Math.toRadians(250)))
            .build();

    public static Trajectory BLL1_traj6 = BuildTrajectory(BLL1_traj5.end())
            .lineToSplineHeading(new Pose2d(14.75,-22, Math.toRadians(250)))
            .build();

    public static Trajectory BLL1_traj7 = BuildTrajectory(BLL1_traj6.end())
            .lineToSplineHeading(new Pose2d(1.5,-17.5, Math.toRadians(250)))
            .build();

    public static Trajectory BLL1_traj8 = BuildTrajectory(BLL1_traj7.end())
            .lineToSplineHeading(new Pose2d(15,-6, Math.toRadians(355)))
            .build();

    public static Trajectory BLL1_traj9 = BuildTrajectory(BLL1_traj8.end())
            .lineToSplineHeading(new Pose2d(60,-6, Math.toRadians(343)))
            .build();

    public static Trajectory BLL1_traj10 = BuildTrajectory(BLL1_traj9.end())
            .lineToSplineHeading(new Pose2d(60,-35, Math.toRadians(90)))
            .build();

    public static Trajectory BLL1_traj10_1 = BuildTrajectory(BLL1_traj10.end())
            .lineToSplineHeading(new Pose2d(94,-35, Math.toRadians(90)))
            .build();

    public static Trajectory BLL1_traj11 = BuildTrajectory(BLL1_traj10_1.end())
            .lineToSplineHeading(new Pose2d(70,-35, Math.toRadians(90)))
            .build();

    public static TrajectoryBuilder BuildTrajectory(Pose2d startPose) {
        return new TrajectoryBuilder(startPose, new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(MAX_ANG_VEL),
                new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
        )), new ProfileAccelerationConstraint(MAX_ACCEL));
    }
}