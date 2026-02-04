package edu.up.airhockey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Iterator;

public class AirHockeyView extends SurfaceView
{
    private AirHockeyModel model;

    private Paint discPaint = new Paint();

    public AirHockeyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.model = new AirHockeyModel();

        setWillNotDraw(false); // This is essential or your onDraw method won't get called.

        this.discPaint.setColor(0xFFC755B5);  // Violet-red.
        this.discPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);
    }

    public AirHockeyModel getModel()
    {
        return this.model;
    }

    private void drawDiscs(Canvas canvas)
    {
        Iterator<AirHockeyModel.AirHockeyDisc> discBrowser = this.model.discs.iterator();
        while (discBrowser.hasNext())
        {
            AirHockeyModel.AirHockeyDisc disc = discBrowser.next();
            float radius = disc.getDiameter() / 2;
            float left = disc.getCenterX() - radius;
            float right = disc.getCenterX() + radius;
            float top = disc.getCenterY() - radius;
            float bottom = disc.getCenterY() + radius;
            canvas.drawOval(left, top, right, bottom, this.discPaint);
        }

    }

    @Override
    public void onDraw(Canvas canvas)
    {
        this.drawDiscs(canvas);
    }
}
