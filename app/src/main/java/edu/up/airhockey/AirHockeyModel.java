package edu.up.airhockey;

import java.util.ArrayList;
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

        private void processPossibleCollisionWithLeftWall()
        {
            float distanceFromCenterToLeftWall = this.centerX;
            float radius = this.diameter / 2;
            float distanceFromEdgeToLeftWall = distanceFromCenterToLeftWall - radius;

            if (distanceFromEdgeToLeftWall <= 0)
            {
                this.speedX *= -1;
            }
        }

        private void processPossibleCollisionWithRightWall()
        {
            if (rinkWidth == null)
            {
                return;
            }

            float distanceFromCenterToRightWall = rinkWidth - this.centerX;
            float radius = this.diameter / 2;
            float distanceFromEdgeToRightWall = distanceFromCenterToRightWall - radius;

            if (distanceFromEdgeToRightWall <= 0)
            {
                this.speedX *= -1;
            }
        }

        private void processPossibleCollisionsWithOtherDiscs() {
            for (AirHockeyDisc otherDisc : discs) {
                if (otherDisc == this) {
                    continue;
                }

                float centerToCenterDistance = this.calcDistanceFromCenterTo(otherDisc.centerX, otherDisc.centerY);
                float thisRadius = this.diameter / 2;
                float otherRadius = otherDisc.diameter / 2;
                float edgeToEdgeDistance = centerToCenterDistance - thisRadius - otherRadius;
                if (edgeToEdgeDistance > 0) {
                    continue;
                }

                // Note that imparting momentum is not implemented -- each disc treats the other disc as stationary.
                // When we do implement imparting momentum, note that we will probably need to update disc speeds all at once, after both discs have processed the collision.
                float thisCenterToThatCenterX = otherDisc.centerX - this.centerX;
                float thisCenterToThatCenterY = otherDisc.centerY - this.centerY;
                float thisCenterToThatCenterDistance = (float) Math.sqrt(Math.pow(thisCenterToThatCenterX, 2) + Math.pow(thisCenterToThatCenterY, 2));
                float thisCenterToThatCenterUnitX = thisCenterToThatCenterX / thisCenterToThatCenterDistance;
                float thisCenterToThatCenterUnitY = thisCenterToThatCenterY / thisCenterToThatCenterDistance;
                float thisCenterToThatCenterSpeed = (thisCenterToThatCenterUnitX * this.speedX) + (thisCenterToThatCenterUnitY * this.speedY);
                if (thisCenterToThatCenterSpeed <= 0) { continue; } // Object is moving away from collision surface; CHANGE to implement momentum being imparted.
                float thisCenterToThatCenterSpeedX = thisCenterToThatCenterSpeed * thisCenterToThatCenterUnitX;
                float thisCenterToThatCenterSpeedY = thisCenterToThatCenterSpeed * thisCenterToThatCenterUnitY;
                float remainingSpeedX = this.speedX - thisCenterToThatCenterSpeedX;
                float remainingSpeedY = this.speedY - thisCenterToThatCenterSpeedY;
                thisCenterToThatCenterSpeedX *= -1;
                thisCenterToThatCenterSpeedY *= -1;
                this.speedX = thisCenterToThatCenterSpeedX + remainingSpeedX;
                this.speedY = thisCenterToThatCenterSpeedY + remainingSpeedY;
            }
        }

        public void attemptGrabAt(float grabX, float grabY)
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

    public Integer rinkWidth;
    public Integer rinkHeight;

    private float gravitySensitivity;
    private float gravityX;
    private float gravityY;

    public List<AirHockeyDisc> discs;

    public AirHockeyModel()
    {
        this.rinkWidth = null;
        this.rinkHeight = null;

        this.gravitySensitivity = 0.5f;
        this.gravityX = 0f;
        this.gravityY = 0f;

        this.discs = new ArrayList<>(3);
    }

    public void setRinkDimensions(int rinkWidth, int rinkHeight)
    {
        this.rinkWidth = rinkWidth;
        this.rinkHeight = rinkHeight;
    }

    public void spawnDisc(float diameter, float centerX, float centerY, float speedX, float speedY)
    {
        this.discs.add(new AirHockeyDisc(diameter, centerX, centerY, speedX, speedY));
    }

    public void takeATimeStep()
    {
        for (AirHockeyDisc disc : this.discs)
        {
            disc.processPossibleCollisionWithLeftWall();
            disc.processPossibleCollisionWithRightWall();
            disc.processPossibleCollisionsWithOtherDiscs();

            disc.centerX += disc.speedX;
            disc.centerY += disc.speedY;


            //disc.speedX += this.gravityX * this.gravitySensitivity * -1;
            disc.speedY += this.gravityY * this.gravitySensitivity;

        }

    }

    public void setGravity(float gravityX, float gravityY)
    {
        this.gravityX = gravityX;
        this.gravityY = gravityY;
    }



}
