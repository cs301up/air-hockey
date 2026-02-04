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

}
