package edu.up.airhockey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Iterator;

public class AirHockeyView extends SurfaceView implements SurfaceHolder.Callback
{
    private AirHockeyModel model;

    private Paint discPaint = new Paint();

    public AirHockeyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        getHolder().addCallback(this); // See implementation of SurfaceHolder.Callback.

        this.model = new AirHockeyModel();

        setWillNotDraw(false); // This is essential or your onDraw method won't get called.

        this.discPaint.setColor(0xFFC755B5);  // Violet-red.
        this.discPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);
    }

    /* Begin implementation of SurfaceHolder.Callback */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int surfaceWidthInPixels, int surfaceHeightInPixels)
    {
        Log.d("AirHockey", "Width: " + surfaceWidthInPixels + ", Height: " + surfaceHeightInPixels);
        this.model.setRinkDimensions(surfaceWidthInPixels, surfaceHeightInPixels);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) { /* Surface is created, but dimensions might not be final yet. */ }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) { /* No-op. */ }
    /* End implementation of SurfaceHolder.Callback */

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
