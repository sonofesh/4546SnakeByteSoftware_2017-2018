package org.firstinspires.ftc.teamcode.Autonomous.OpModes.TestFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.OpModes.AutoOpMode;

/**
 * Created by sopa on 12/7/16.
 * Test log: 28 + 25 + 32 + 3
 */
@Autonomous(name = "TestCorrection", group = "Autonomous")
public class TestCorrection extends AutoOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry.log();
        initialize();
        telemetry.addData("test5", "init");
        telemetry.update();
        waitForStart();
        //Standard constants: double p = .00015; double i = .00000015;
        //moveBackwardPID(.0003, .0000003, 0.0, 2000);
        double angle = getGyroYaw();
        telemetry.addData("before yaw forward", getGyroYaw());
        telemetry.update();
        sleep(3000);
        moveForwardPID(4000, angle);
        //long lastTime = System.currentTimeMillis();
        sleep(2500);
//        moveBackwardPID(.0003, .0000003, 0.0, 2000);
//        telemetry.addData("before yaw backward", getGyroYaw());
//        telemetry.update();
//        moveBackWardWithCorrection(.15, 2000);
//        //hacked
//        sleep(5000);
//        telemetry.addData("after yaw backward", getGyroYaw());
//        telemetry.update();
//        sleep(5000);
//        telemetry.addData("difference in angle", (getGyroYaw() - beforeAngle));
//        telemetry.update();
    }
}
