package edu.up.airhockey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AirHockeyModel
{
    public class AirHockeyDisc
    {
        private float diameter;
        private float centerX;
        private float centerY;
        private float speedX;
        private float speedY;

        private boolean isGrabbed;

        public AirHockeyDisc(float diameter, float centerX, float centerY, float speedX, float speedY)
        {
            this.diameter = diameter;
            this.centerX = centerX;
            this.centerY = centerY;
            this.speedX = speedX; // per second.
            this.speedY = speedY; // per second.

            this.isGrabbed = false;
        }

        public float getDiameter() { return this.diameter; }
        public float getCenterX() { return this.centerX; }
        public float getCenterY() { return this.centerY; }

        public float calcDistanceFromCenterTo(float x, float y)
        {
            float dX = x - this.centerX;
            float dY = y - this.centerY;
            return (float) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
        }

        public void processGrabAttempt(float grabX, float grabY)
        {
            float distanceFromCenterToGrab = this.calcDistanceFromCenterTo(grabX, grabY);
            float radius = this.diameter / 2;
            if (distanceFromCenterToGrab <= radius)
            {
                this.isGrabbed = true;
                this.dragCenterTo(grabX, grabY);
            }
        }

        public void dragCenterTo(float x, float y)
        {
            this.centerX = x;
            this.centerY = y;
            this.speedX = 0;
            this.speedY = 0;
        }

        public void release()
        {
            this.isGrabbed = false;
        }

        public boolean isGrabbed() { return this.isGrabbed; }
    }

    public List<AirHockeyDisc> discs;

    public AirHockeyModel()
    {
        this.discs = new ArrayList<>(3);
    }

    public void spawnDisc(float diameter, float centerX, float centerY, float speedX, float speedY)
    {
        this.discs.add(new AirHockeyDisc(diameter, centerX, centerY, speedX, speedY));
    }

    public void takeATimeStep()
    {
        Iterator<AirHockeyDisc> discBrowser = this.discs.iterator();
        while (discBrowser.hasNext())
        {
            AirHockeyDisc disc = discBrowser.next();
            disc.centerX += disc.speedX;
            disc.centerY += disc.speedY;
        }

    }

}
