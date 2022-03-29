package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.motors.EncoderMotor;
import org.firstinspires.ftc.teamcode.motors.Motor;

public class MecanumControl {

    private MecanumControl.Controller controller;
    private LinearOpMode context;
    private Motor[] motors;

    private Motor fl;
    private Motor fr;
    private Motor bl;
    private Motor br;

    public MecanumControl(LinearOpMode context, Motor[] motors){
        controller = new Controller();
        this.context = context;
        this.motors = motors;
        fl = motors[0];
        fr = motors[1];
        bl = motors[2];
        br = motors[3];
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void moveMotorsByJoystick(){
        double r = Math.hypot(context.gamepad1.left_stick_x, context.gamepad1.left_stick_y);
        double robotAngle = Math.atan2(context.gamepad1.left_stick_y, context.gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = context.gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;


        motors[0].setPower(v1);
        motors[1].setPower(v2);
        motors[2].setPower(v3);
        motors[3].setPower(v4);
    }

    public MecanumControl.Controller getController(){
        return controller;
    }



     class Controller{

        public void forward(double power, int cm){
            for(Motor m : motors){
                runEncoder(power,cm,m);
            }
            waitForAllMotors();
        }

         public void right(double power, int cm){
            runEncoder(power,cm,fl);
            runEncoder(power,cm,br);
            runEncoder(-power,cm,fr);
            runEncoder(-power,cm,bl);
            waitForAllMotors();
        }

         public void left(double power, int cm){
             runEncoder(-power,cm,fl);
             runEncoder(-power,cm,br);
             runEncoder(power,cm,fr);
             runEncoder(power,cm,bl);
             waitForAllMotors();
         }

         public void diagonalLeft(double power, int cm){
             runEncoder(power,cm,fr);
             runEncoder(power,cm,bl);
             waitForAllMotors();
         }

         public void diagonalRight(double power, int cm){
             runEncoder(power,cm,fl);
             runEncoder(power,cm,br);
             waitForAllMotors();
         }

         public void rotationRight(double power, int cm){
             runEncoder(power,cm,fl);
             runEncoder(power,cm,bl);
             runEncoder(-power,cm,br);
             runEncoder(-power,cm,fr);
             waitForAllMotors();
         }

         public void rotationLeft(double power, int cm){
             runEncoder(-power,cm,fl);
             runEncoder(-power,cm,bl);
             runEncoder(power,cm,br);
             runEncoder(power,cm,fr);
             waitForAllMotors();
         }

        private void runEncoder(double power, int cm, Motor m){
            EncoderMotor motor = (EncoderMotor) m;
            motor.setTargetPosition(cm);
            motor.setPower(power);
        }

        private void waitForAllMotors(){
            while (context.opModeIsActive())
            {
                for(EncoderMotor m : (EncoderMotor[]) motors){
                    if(!m.isBusy())
                        break;
                }
                context.idle();
            }
        }

    }
}
