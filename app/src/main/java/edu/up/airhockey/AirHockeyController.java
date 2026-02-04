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
        this.model.spawnDisc(diameter, 100, 100, 20, 20);
        this.model.spawnDisc(diameter, 300, 500, 20, 20);
        this.model.spawnDisc(diameter, 400, 1000, 20, 20);
    }

    public void takeATimeStep()
    {
        this.model.takeATimeStep();
        this.view.postInvalidate();
    }

}
