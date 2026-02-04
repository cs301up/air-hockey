package edu.up.airhockey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class AirHockeyView extends SurfaceView
{
    private AirHockeyModel model;

    private Paint puckPaint = new Paint();

    public AirHockeyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.model = new AirHockeyModel();

        setWillNotDraw(false); // This is essential or your onDraw method won't get called.

        this.puckPaint.setColor(0xFFC755B5);  // Violet-red.
        this.puckPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);
    }

    public AirHockeyModel getModel()
    {
        return this.model;
    }

    private void drawPuck(Canvas canvas)
    {
        float radius = this.model.puck.getDiameter() / 2;
        float left = this.model.puck.getCenterX() - radius;
        float right = this.model.puck.getCenterX() + radius;
        float top = this.model.puck.getCenterY() - radius;
        float bottom = this.model.puck.getCenterY() + radius;
        canvas.drawOval(left, top, right, bottom, this.puckPaint);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        this.drawPuck(canvas);
    }
}
