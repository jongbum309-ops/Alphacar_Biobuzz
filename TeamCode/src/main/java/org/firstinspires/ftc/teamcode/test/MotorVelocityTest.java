package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Motor Velocity Test", group = "Test")
public class MotorVelocityTest extends OpMode {

    private DcMotorEx motor;

    // 목표 속도: encoder ticks / second
    private double targetVelocity = 0;

    @Override
    public void init() {

        motor = hardwareMap.get(DcMotorEx.class, "testMotor");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        // D-pad로 목표 속도 조절
        if (gamepad1.dpad_up) {
            targetVelocity += 10;
        }

        if (gamepad1.dpad_down) {
            targetVelocity -= 10;
        }

        // A 버튼 = 정지
        if (gamepad1.a) {
            targetVelocity = 0;
        }

        motor.setVelocity(targetVelocity);

        telemetry.addData("Target Velocity", targetVelocity);
        telemetry.addData("Actual Velocity", motor.getVelocity());
        telemetry.addData("Encoder Position", motor.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void stop() {
        motor.setPower(0);
    }
}