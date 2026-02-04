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

        public AirHockeyDisc(float diameter, float centerX, float centerY, float speedX, float speedY)
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
