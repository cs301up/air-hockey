package edu.up.airhockey;

import android.graphics.Canvas;

public class AirHockeyModel
{
    public class AirHockeyPuck
    {
        private float diameter;
        private float centerX;
        private float centerY;
        private float speedX;
        private float speedY;

        public AirHockeyPuck(float diameter, float centerX, float centerY, float speedX, float speedY)
        {
            this.diameter = diameter;
            this.centerX = centerX;
            this.centerY = centerY;
            this.speedX = speedX; // per second.
            this.speedY = speedY; // per second.
        }

        public float getDiameter() { return this.diameter; }
        public float getCenterX() { return this.centerX; }
        public float getCenterY() { return this.centerY; }
    }

    public AirHockeyPuck puck;

    public AirHockeyModel()
    {
        this.puck = null;
    }

    public void spawnPuck(float puckDiameter, float puckCenterX, float puckCenterY, float puckSpeedX, float puckSpeedY)
    {
        this.puck = new AirHockeyPuck(puckDiameter, puckCenterX, puckCenterY, puckSpeedX, puckSpeedY);
    }

}
