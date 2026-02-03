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

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawOval(100, 100, 300, 300, this.puckPaint);
    }
}
