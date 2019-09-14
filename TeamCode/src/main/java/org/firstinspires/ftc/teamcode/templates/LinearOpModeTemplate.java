package org.firstinspires.ftc.teamcode.templates;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwarePushbot;
/*
 * http://ideone.com/Wgu700
 *
 * */

@TeleOp(name = "Linear OpMode Example", group = "Examples")
@Disabled
public class LinearOpModeTemplate extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
    }
}
