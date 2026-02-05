package edu.up.airhockey;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class AirHockeyController implements SensorEventListener
{
    private AirHockeyView view;
    private AirHockeyModel model;

    public AirHockeyController(AirHockeyView view)
    {
        this.view = view;
        this.model = this.view.getModel();
    }

    public void startGame()
    {
        float diameter = 200;
        this.model.spawnDisc(diameter, 100, 100, -10, 10);
        this.model.spawnDisc(diameter, 500, 1000, 5, 5);
        this.model.spawnDisc(diameter, 400, 1500, -20, -10);
    }

    public void takeATimeStep()
    {
        this.model.takeATimeStep();
        this.view.postInvalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        Log.i("AirHockey", "Sensor accuracy is now " + accuracy);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Log.i("AirHockey", "Gravity (x,y,z):" + event.values[0] + "," + event.values[1] + "," + event.values[2]);
        this.model.setGravity(event.values[0], event.values[1]);
    }
}
