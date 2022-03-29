package org.firstinspires.ftc.teamcode.motors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.config.Config;

public class EncoderMotor extends Motor {

    public LinearOpMode context;

    public EncoderMotor(OpMode context, String name) {
        super(context, name);
        this.context = (LinearOpMode) context;
        motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void setTargetPosition(int cm){
        motor.setTargetPosition((int)(Config.ticksToCmMultiplier * cm));
    }

    public void setTargetPositionInTicks(int ticks){
        motor.setTargetPosition(ticks);
    }

    public void waitForMotorAndStop(){
        while (context.opModeIsActive() && motor.isBusy())
        {
            context.idle();
        }
        setPowerWithoutEncoder(0);
        resetEncoder();
    }

    public void waitForMotor(){
        while (context.opModeIsActive() && motor.isBusy())
        {
            context.idle();
        }
    }

    @Override
    public void setPower(double power) {
        motor.setMode(RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }

    public void resetEncoder(){
        motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPowerWithoutEncoder(double power){
        motor.setPower(power);
    }

    @Override
    public int getCurrentPosition(){
        return (int)(Config.ticksToCmMultiplier * motor.getCurrentPosition());
    }

    public int getCurrentPositionInTicks(){
        return motor.getCurrentPosition();
    }

    public void runMotorForCm(double power, int cm){
        setTargetPosition(cm);
        setPower(power);
        waitForMotorAndStop();
    }
}
