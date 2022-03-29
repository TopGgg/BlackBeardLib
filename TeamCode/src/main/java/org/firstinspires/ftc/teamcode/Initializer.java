package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.config.Config;
import org.firstinspires.ftc.teamcode.motors.Motor;

public class Initializer {

    //order: {fl,fr,bl,br}
    public static Motor[] initializeMotors(LinearOpMode context){
        Motor fl = new Motor(context, Config.front_left);
        Motor fr = new Motor(context, Config.front_right);
        Motor bl = new Motor(context, Config.back_left);
        Motor br = new Motor(context, Config.back_right);

        return new Motor[]{fl,fr,bl,br};
    }
}
