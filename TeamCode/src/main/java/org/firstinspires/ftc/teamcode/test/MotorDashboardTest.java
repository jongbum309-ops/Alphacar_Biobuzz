package org.firstinspires.ftc.teamcode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp(name = "Motor Dashboard Test", group = "Test")
public class MotorDashboardTest extends OpMode {

    private DcMotorEx motor;

    // Dashboard에서 실시간 변경
    public static volatile double MOTOR_POWER = 0.8;

    @Override
    public void init() {

        motor = hardwareMap.get(DcMotorEx.class, "testMotor");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry()
        );

        telemetry.addLine("Motor Dashboard Test Ready");
        telemetry.update();
    }

    @Override
    public void loop() {

        // Dashboard의 값을 매 loop마다 직접 읽음
        double power = Math.max(-1.0, Math.min(1.0, MOTOR_POWER));

        motor.setPower(power);

        telemetry.addData("Dashboard MOTOR_POWER", MOTOR_POWER);
        telemetry.addData("Applied Power", power);
        telemetry.addData("Encoder Position", motor.getCurrentPosition());
        telemetry.addData("Encoder Velocity", motor.getVelocity());

        telemetry.update();
    }

    @Override
    public void stop() {
        motor.setPower(0);
    }
}