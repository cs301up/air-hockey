package edu.up.airhockey;

public class AirHockeyController
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
        this.model.spawnPuck(200, 100, 100, 20, 20);
    }

    public void takeATimeStep()
    {
        this.model.takeATimeStep();
        this.view.postInvalidate();
    }

}
