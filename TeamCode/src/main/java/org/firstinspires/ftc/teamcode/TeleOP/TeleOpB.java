package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/** CONTROLS
 *      Controller 1 - Drive
 *          Right Stick Y Axis : Right Wheel Power          Left Stick Y Axis : Left Wheel Power
 *          Y : Reverse Drive Toggle                        A : Half Speed Toggle
 *
 *      Controller 2 - Scoring
 *          Right Stick Y Axis : Manipulator High Power     Left Stick Y Axis : Manipulator Low Power
 *          Right Trigger : Ramp Down                       Left Trigger : Ramp Uo
 *          Right Bumper : Shooter High Power               Left Bumper : Shooter Low Power
 *          Hold X : Beacon Pusher Left                     Hold B : Beacon Pusher Right
 *          Y : Deploy Stopper Toggle                       A : Deploy Lift Gate
 *
 * VHS ROBOTICS 4546
 * 10/17/16
 */
@TeleOp(name = "TeleOpB", group = "TeleOp")
@Disabled
public class TeleOpB extends OpMode {
        DcMotor FR;
        DcMotor FL;
        DcMotor BR;
        DcMotor BL;
        DcMotor ShooterF;
        DcMotor ShooterB;
        DcMotor ManIn;
        DcMotor ManLift;
        Servo Beacon;
        //Servo Release;
        boolean shootFull;
        boolean shootPartial;
        boolean harvest;
        boolean stop;
        boolean gate;
        int direction = 1;
        int liftPosO;
        int liftPosCurrent;
        long shoottime;
        long harvesttime;
        long currentTime;
        long lastTime;
        //Since Toggle A activates halfspeed AND Start A starts robot, halfspeed starts true to it's immediately deactivated
        boolean halfspeed = true;
        final double HALFSPEED = .25;
        final double FULLSPEED = 1;
        final long DURATION = 500;
        final int UPDISTANCE = 30;
        double speed = 0;
        boolean liftUp;
        MotorScaling scale;
        @Override
        public void init() {
            FR = hardwareMap.dcMotor.get("FR");
            BR = hardwareMap.dcMotor.get("BR");
            FL = hardwareMap.dcMotor.get("FL");
            BL = hardwareMap.dcMotor.get("BL");
            ShooterB = hardwareMap.dcMotor.get("B");
            ShooterF = hardwareMap.dcMotor.get("F");
            ManIn = hardwareMap.dcMotor.get("ManIn");
            ManLift = hardwareMap.dcMotor.get("ManLift");
            Beacon = hardwareMap.servo.get("Beacon");
            //Release = hardwareMap.servo.get("Release");
            //Release.setPosition(0);
            Beacon.setPosition(1);
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);
            ShooterB.setPower(0);
            ShooterF.setPower(0);
            ManLift.setPower(0);
            ManIn.setPower(0);
            ManIn.setPower(0);
            //ManLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //ManLift.setMode(DcMotor.RunMode.RESET_ENCODERS);
            liftPosO = 0;
            liftPosCurrent = 0;
            shootFull = false;
            shootPartial = false;
            harvest = false;
            liftUp = false;
            shoottime = 0;
            harvesttime = 0;
            currentTime = 0;
            lastTime = 0;
            liftUp = false;
            stop = false;
            gate = false;
        }

        @Override
        public void loop() {

            //CONTROLLER 1

            //Tank Drive
            if (Math.abs(gamepad1.left_stick_y) > .1) {
                FL.setPower(gamepad1.left_stick_y * direction * -1 * speed);
                BL.setPower(gamepad1.left_stick_y * direction * -1 * speed);
            } else {
                FL.setPower(0);
                BL.setPower(0);
            }
            if (Math.abs(gamepad1.right_stick_y) > .1) {
                FR.setPower(gamepad1.right_stick_y * direction * speed);
                BR.setPower(gamepad1.right_stick_y * direction * speed);
            } else {
                FR.setPower(0);
                BR.setPower(0);
            }
            //HalfSpeed Macro
            if (gamepad1.a) {
                currentTime = System.currentTimeMillis();
                if (currentTime > lastTime + DURATION) {
                    if (halfspeed) {
                        halfspeed = false;
                    } else
                        halfspeed = true;
                }
                if (halfspeed)
                    speed = HALFSPEED;
                else
                    speed = FULLSPEED;
                lastTime = System.currentTimeMillis();
            }
            //Reverse Macro
            if (gamepad1.y) {
                currentTime = System.currentTimeMillis();
                if (currentTime > lastTime + DURATION) {
                    direction *= -1;
                }
                lastTime = System.currentTimeMillis();
            }

            //CONTROLLER 2

            //Shooter Controls
            if (gamepad2.right_bumper) {
                ShooterF.setPower(1);
                ShooterB.setPower(-1);
            } else if (gamepad2.left_bumper) {
                ShooterF.setPower(.9);
                ShooterB.setPower(-.9);
            } else {
                ShooterF.setPower(0);
                ShooterB.setPower(0);
            }
            //Manipulator Control
            if (Math.abs(gamepad2.right_stick_y) > .1) {
                ManIn.setPower(gamepad2.right_stick_y);
            }
            //Half Power Manipulator
            else if (Math.abs(gamepad2.left_stick_y) > .1) {
                ManIn.setPower(gamepad2.left_stick_y * .25);
            } else {
                ManIn.setPower(0);
            }
            if (Math.abs(gamepad2.left_trigger) > .05)
                ManLift.setPower(gamepad2.left_trigger * .25 * -1);

            else if (Math.abs(gamepad2.right_trigger) > .05)
                ManLift.setPower(gamepad2.right_trigger * .25);

            else
                ManLift.setPower(0);
        }
    }
//        //Physical Lift Stop Servo Control
//        if (gamepad2.y) {
//            currentTime = System.currentTimeMillis();
//            if (currentTime > lastTime + DURATION) {
//                if (stop) stop = false;
//                else stop = true;
//            }
//            lastTime = System.currentTimeMillis();
//        }
//        if (stop) {
//            Stopper.setPosition(1); //Arbitrary Values
//        } else {
//            Stopper.setPosition(0);
//        }
//
//        //Beacon Pusher Controls0
//        if (gamepad2.x) {
//            Beacon.setPosition(.6); //Test 3 Value; 1Value = .5; 0value = .4
//        }
//        //else if(gamepad2.b)
//        //{
//        //   Beacon.setPosition(0.05); //Test 2 Value; 1Value = 0; 0value = 0
//        //}
//        else {
//            Beacon.setPosition(.5); //Test 2 Value; 1Value = .25; 0value = .2
//        }
    //Lift Release
        /*
        if(gamepad2.start)
        {
            Release.setPosition(1);
        }
         */


    /**if(gamepad2.y)
     {
     currentTime = System.nanoTime();
     if(currentTime > lastTime + DURATION)
     {
     if(liftUp) liftUp = false;
     else liftUp = true;
     }
     lastTime = System.nanoTime();
     }
     /**if(Math.abs(gamepad2.left_stick_y) > .05)
     {
     ManLift.setPower(-1 * gamepad2.left_stick_y * .1);
     }
     else
     ManLift.setPower(0);
     if(Math.abs(gamepad2.left_trigger) > .05)
     ManLift.setPower(gamepad2.left_trigger * .1);
     else
     ManLift.setPower(0);
     liftPosO = ManLift.getCurrentPosition();
     if(liftUp && Math.abs(ManLift.getCurrentPosition() - liftPosO) < UPDISTANCE)
     {
     ManLift.setPower(-.3);
     telemetry.addData("upPosition", ManLift.getCurrentPosition());
     }
     else if(!liftUp && ManLift.getCurrentPosition() > 0) {
     ManLift.setPower(.3);
     }
     else
     {
     ManLift.setPower(0);
     }
     */
/* Shooter toggle controls - Please check this code to make sure it is fine
button y is now a toggle which controls liftUp boolean. If liftUp boolean is true
and the encoder does not read 0 (we are starting up, so the encoder should read 0 at the top)
if(gamepad2.y)
        {
            currentTime = System.nanoTime();
            if(currentTime > lastTime + DURATION)
            {
                if(liftUp) liftUp = false;
		else liftUp = true;
            }
            lastTime = System.nanoTime();
        }
if(liftUp && ManLift.getCurrentPosition() > 0)
{
	ManLift.setPower(-.3);
}
else if(!liftUp && ManLift.getCurrentPosition() != UPDISTANCE)
{
	ManLift.setPower(.3);
}
else
{
	ManLift.setPower(0);
}


*/

