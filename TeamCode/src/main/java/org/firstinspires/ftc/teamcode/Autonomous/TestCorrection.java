package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by sopa on 12/7/16.
 */
@Autonomous(name = "TestCorrection", group = "Autonomous")
public class TestCorrection extends AutoOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        initalize();
        telemetry.addData("test1", "init");
        waitForStart();
        telemetry.addData("before yaw forward", getGryoYaw());
        double beforeAngle = getGryoYaw();
        telemetry.update();
        sleep(3000);
        moveForwardWithCorrection(.15, 3000);
        telemetry.addData("after yaw forward", getGryoYaw());
        telemetry.update();
        sleep(5000);
        telemetry.addData("before yaw backward", getGryoYaw());
        telemetry.update();
        moveBackWarddWithCorrection(.15, 3000);
        //hacked
        sleep(5000);
        telemetry.addData("after yaw backward", getGryoYaw());
        telemetry.update();
        sleep(5000);
        telemetry.addData("difference in angle", (getGryoYaw() - beforeAngle));
    }
}
