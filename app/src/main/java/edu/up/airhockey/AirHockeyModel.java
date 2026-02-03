package edu.up.airhockey;

public class AirHockeyModel
{
    private class AirHockeyPuck
    {
        private float puckDiameter;
        private float puckCenterX;
        private float puckCenterY;
        private float puckSpeedX;
        private float puckSpeedY;

        public AirHockeyPuck(float puckDiameter, float puckCenterX, float puckCenterY, float puckSpeedX, float puckSpeedY)
        {
            this.puckDiameter = puckDiameter;
            this.puckCenterX = puckCenterX;
            this.puckCenterY = puckCenterY;
            this.puckSpeedX = puckSpeedX; // per second.
            this.puckSpeedY = puckSpeedY; // per second.
        }
    }

    private AirHockeyPuck puck;

    public AirHockeyModel()
    {
        this.puck = null;
    }

    public void spawnPuck(float puckDiameter, float puckCenterX, float puckCenterY, float puckSpeedX, float puckSpeedY)
    {
        this.puck = new AirHockeyPuck(puckDiameter, puckCenterX, puckCenterY, puckSpeedX, puckSpeedY);
    }
}
