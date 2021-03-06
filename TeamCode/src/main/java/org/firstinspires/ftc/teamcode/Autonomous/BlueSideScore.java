package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.OpModes.AutoOpMode;


/**
 * Created by 4546 on 12/21/16.
 * This auto will move to the beacons, hit both, turn, shoot, and then knock the cap ball
 * Test Count: 12 + 3 + 22 + 12 + 4 + 4 + 1 + 5 + 14 + 19 + 2 = 100 tests
 */
@Autonomous(name = "BlueSide100", group = "Autonomous")
public class BlueSideScore extends AutoOpMode {
    public BlueSideScore() {
        super();
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        double power = .88;
        telemetry.addData("init", "test6");
        telemetry.update();
        waitForStart();
        moveForwardWithEncoders(.16, 500);
        bringDownShooter(.3, 1150);
        sleep(1000);
        double voltage = hardwareMap.voltageSensor.get("Motor Controller 1").getVoltage();
        if (voltage <= 13.5)
            power = .95;
        else if (voltage <= 13.75 && voltage > 13.5)
            power = .92;
        else if (voltage > 13.75 && voltage <= 13.9)
            power = .88;
        else if (voltage > 13.9 && voltage <= 14)
            power = .86;
        else if (voltage > 14)
            power = .80;
        shoot(power, 360);
        double angle36 = getGyroYaw();
        bringDownShooter(-.4, 600);
        turnRightWithPID(36, .005, .000025, 0.0);
        angle36 += 36;
        moveForwardPID(3200, angle36);
        sleep(500);
        turnRightWithPID(160, .0055, .000045, 0.0);
        moveBackWardWithEncoders(.2, 600);
        moveToWallBlue(2250, .25);
        sleep(500);
        if (onWhiteLine() == false)
            moveBackwardsToWhiteLine(500);
        sleep(500);
        pushBlueBeacon();
        sleep(1000);
        moveToSecondLine(3400, -.3, 6);
        sleep(500);
        if (onWhiteLine() == false)
            moveBackwardsToWhiteLine(200);
        sleep(500);
        pushBlueBeacon();
        sleep(500);
        moveBackWardWithEncoders(.4, 800);
        turnLeftWithGyro(.4, 90);
        sleep(500);
        moveForwardWithEncoders(.5, 3000);
    }
}


//        moveBackWardWithEncoders(.175, 400);
//        turnLeftWithPID(75);
//        moveForwardsToWhiteLine(2800, getGyroYaw());
//        if(getGyroYaw() < perpendicular)
//            turnIntoWhiteLine(perpendicular - getGyroYaw() + 90);
//        else if(getGyroYaw() > perpendicular)
//            turnIntoWhiteLine(90 + (getGyroYaw() - perpendicular));
//        else
//            turnIntoWhiteLine(90);
//        pushFrontBlue(perpendicular + 90);
//        moveBackWardWithEncoders(.175, 400);
//        turnLeftWithGyro(.3, 15);
//        moveBackWardWithEncoders(.5, 3000);
//        double p = .004; double i = .000015; //double d = 2.0;
//        correct(perpendicular, .004, .000015, 0, 0);
//        moveForwardPID(2500, perpendicular);
//        moveForwardsToWhiteLine(300, perpendicular);
//        turnIntoWhiteLine(perpendicular - 90);
//        pushFrontBlue();


//    telemetry.addData("init", "test1");
//        telemetry.update();
//        long beforeTime = System.currentTimeMillis();
//        double beforeAngle = getGyroYaw();
//        while(System.currentTimeMillis() - beforeTime < 15000) {
//            telemetry.addData("angle", Math.abs(getGyroYaw() - beforeAngle));
//            telemetry.update();
//        }
//        waitForStart();
//        moveBackwardPID(2500);
//
//        //correct(beforeAngle);
//        moveBackwardsToWhiteLine(300, .175, perpen);
//        beaconValue();
//        moveForwardPID(2300);
//        moveForwardsToWhiteLine(600);
//        turnLeftWithPID(15);
//        moveForwardWithEncoders(.25, 1000);
//        //bring down shooter
//        bringDownShooter(.1, 1100);
//        sleep(750);
//        //shoot
//        shoot(.85, 350);
//        moveForwardWithEncoders(.4, 3000);
//        moveForwardPID(500);
//        //bring down shooter
//        bringDownShooter(.1, 1100);
//        sleep(750);
//        //shoot
//        shoot(.85, 400);
//        sleep(750);
//        turnRightWithPID(50);
//        sleep(500);
//        moveForwardPID(2500);
//        sleep(500);
//        turnLeftWithPID(50);
//        sleep(500);
//        correct(beforeAngle);
//        //moveToWhiteLine();


//    double angle43 = perpendicular;
//    //double p = .004; double i = .000015;
//    turnRightWithPID(43, .006, .00004, 0.0);
//    sleep(500);
//    angle43 += 43;
//    moveForwardsToWhiteLine(2950, angle43);
//    //        moveExtra(.2, 20);
//    //double p = .004; double i = .000015;
//    sleep(500);
//    turnIntoWhiteLine(Math.abs(getGyroYaw() - perpendicular) + 2);
//    sleep(500);
//    pushFrontBlue(perpendicular + 90);
//    sleep(1000);
//    bringDownShooter(-.4, 400);
//    moveBackWardWithEncoders(.6, 3000);
//    turnLeftWithGyro(.3, 30);


    //shoot
//        double voltage = hardwareMap.voltageSensor.get("Motor Controller 1").getVoltage();
//        if (voltage <= 13.5)
//            power = .9;
//        else if (voltage <= 13.75 && voltage > 13.5)
//            power = .85;
//        else if (voltage > 13.75 && voltage <= 13.9)
//            power = .78;
//        else if (voltage > 13.9 && voltage <= 14)
//            power = .72;
//        else if (voltage > 14)
//            power = .7;
//        shoot(power, 350);
//        sleep(750);
    //shoot