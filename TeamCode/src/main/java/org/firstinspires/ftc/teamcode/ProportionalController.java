package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Proportional Controller Test", group = "Test")
//@Disabled
public class ProportionalController extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //reset encoder
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // don't use the built in
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        if (opModeIsActive()) {
            driveToPosition(10);
            sleep(50);
        }
    }

    final double TOLERANCE = 5.0;
    final double PROPORTIONAL_CONSTANT = 0.1; //slope of y = mx
    double leftError;
    double rightError;

    public void driveToPosition(double targetDistance) {
        while(opModeIsActive() && !((Math.abs(leftError) < TOLERANCE) && (Math.abs(rightError) < TOLERANCE))) {
            double currentLeftInches = getLeftEncoderCounts() / robot.COUNTS_PER_INCH,
                    currentRightInches = getRightEncoderCounts() / robot.COUNTS_PER_INCH;

            leftError = targetDistance - currentLeftInches;
            rightError = targetDistance - currentRightInches;

            double leftOutput = PROPORTIONAL_CONSTANT * leftError;
            double rightOutput = PROPORTIONAL_CONSTANT * rightError;

            robot.leftDrive.setPower(leftOutput);
            robot.rightDrive.setPower(rightOutput);

            sleep(50);
        } // end while loop
    } // end driveToPosition method

    public double getLeftEncoderCounts() {
        return robot.leftDrive.getCurrentPosition();
    }

    public double getRightEncoderCounts() {
        return robot.rightDrive.getCurrentPosition();
    }

}
