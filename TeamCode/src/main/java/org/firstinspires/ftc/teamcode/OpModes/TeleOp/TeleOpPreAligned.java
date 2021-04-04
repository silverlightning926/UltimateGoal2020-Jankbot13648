package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.DriveBase.drive.PoseStorage;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.Shooter;
import org.firstinspires.ftc.teamcode.WobbleGoal;
import org.firstinspires.ftc.teamcode.DriveBase.drive.DriveBase;

@TeleOp(name = "TeleOp - Pre Aligned")
public class TeleOpPreAligned extends LinearOpMode {

    DriveBase driveBase;
    Shooter shooter;
    Intake intake;
    WobbleGoal wobbleGoal;

    @Override
    public void runOpMode() throws InterruptedException {

        driveBase = new DriveBase(hardwareMap);
        shooter = new Shooter(hardwareMap);
        intake = new Intake(hardwareMap);
        wobbleGoal = new WobbleGoal(hardwareMap);

        telemetry.addLine("System Initialization Complete");
        telemetry.update();

        waitForStart();

        telemetry.clearAll();
        telemetry.update();

        if(isStopRequested()) return;

        shooter.Unkick();

        while (!isStopRequested() && opModeIsActive())
        {
            Pose2d poseEstimate = driveBase.getPoseEstimate();

            if(!gamepad1.right_bumper)
            {
                Vector2d input = new Vector2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x
                ).rotated(-poseEstimate.getHeading());

                driveBase.setWeightedDrivePower(
                        new Pose2d(
                                input.getX(),
                                input.getY(),
                                -gamepad1.right_stick_x
                        )
                );
            }

            else {
                Vector2d input = new Vector2d(
                        -gamepad1.left_stick_y/2,
                        -gamepad1.left_stick_x/2
                ).rotated(-poseEstimate.getHeading());

                driveBase.setWeightedDrivePower(
                        new Pose2d(
                                input.getX(),
                                input.getY(),
                                -gamepad1.right_stick_x/2
                        )
                );
            }

            //double angleToGoal = Math.atan2(36 + poseEstimate.getY(), 144 - poseEstimate.getX());

            driveBase.update();

            telemetry.addData("Current X", -poseEstimate.getY());
            telemetry.addData("Current Y", poseEstimate.getX());
            telemetry.addData("Current Heading", poseEstimate.getHeading());
            telemetry.addData("Distance To X", 36 + poseEstimate.getY());
            telemetry.addData("Distance To Y", 144 - poseEstimate.getX());
            telemetry.addData("Aim Angle", Math.atan2(36 + poseEstimate.getY(), 144 - poseEstimate.getX()));

            telemetry.update();

            if(gamepad1.a)
            {
                for(int i = 0; i <3; i++)
                {
                    shooter.Kick();

                    sleep(200);

                    shooter.Unkick();

                    sleep(200);
                }
            }

            shooter.SetShooter(Constants.SHOOTER_VELOCITY);
            intake.SetIntake(gamepad1.right_trigger, gamepad1.left_trigger);
            wobbleGoal.MoveWobbleGoalPosition(gamepad2.dpad_left, gamepad2.dpad_up, gamepad2.dpad_right);
            wobbleGoal.WobbleGoalManipulatorHandler(gamepad2.x, gamepad2.b);
        }
    }
}
