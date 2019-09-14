package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Encoder Test", group = "Test")
//@Disabled
public class TestEncoder extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Left Encoder Counts", getLeftEncoderCounts());
            telemetry.addData("Right Encoder Counts", getRightEncoderCounts());
            telemetry.addData("Left Encoder Inches", getLeftEncoderCounts() / robot.COUNTS_PER_INCH);
            telemetry.addData("Right Encoder Inches", getRightEncoderCounts() / robot.COUNTS_PER_INCH);
            telemetry.update();

            sleep(50);
        }
    }

    public double getLeftEncoderCounts() {
        return robot.leftDrive.getCurrentPosition();
    }

    public double getRightEncoderCounts() {
        return robot.rightDrive.getCurrentPosition();
    }

}
