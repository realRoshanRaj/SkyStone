package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "Pivot", group = "Example")
public class PivotOpMode extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    // State used for updating telemetry
    Orientation angles;

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            final double targetAngle = 90;
            angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            double currentAngle = angles.firstAngle;
            telemetry.addData("Current Angle", currentAngle);
            telemetry.update();

            pivotToAngle(targetAngle, currentAngle);

            sleep(50);
        }

    }

    private void pivotToAngle(double targetHeading, double currentHeading) {
        final double TOLERANCE = 5; // Degrees of tolerance
        final double BASE_POWER = 0.5; // Base power for pivot
        if (Math.abs(targetHeading - currentHeading) > TOLERANCE) {
            double leftPower = BASE_POWER;
            double rightPower = -BASE_POWER;

            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);
        } else {
            robot.leftDrive.setPower(0.0);
            robot.rightDrive.setPower(0.0);
        }

    }
}


