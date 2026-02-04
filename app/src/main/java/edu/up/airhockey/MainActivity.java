package edu.up.airhockey;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private AirHockeyController airHockeyController;

    private static final int UPDATE_INTERVAL_MS = 25;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable takesOneTimeStep;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AirHockeyView airHockeyView = findViewById(R.id.airhockeyview);
        this.airHockeyController = new AirHockeyController(airHockeyView);

        Log.i("AirHockey", ""+airHockeyView.getRight());
        //System.out.println(this.rinkHeight);

        this.airHockeyController.startGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTakingTimeSteps();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTakingTimeSteps();
    }

    private void startTakingTimeSteps() {
        takesOneTimeStep = new Runnable() {
            @Override
            public void run() {
                // [performance improvement idea] Start a timer here.
                try {

                    airHockeyController.takeATimeStep();

                } finally {
                    // [performance improvement idea] Stop the timer here.
                    // [performance improvement idea] Calculate UPDATE_INTERVAL_MS - elapsedTime from timer. If negative, clip to zero and issue a warning.
                    handler.postDelayed(this, UPDATE_INTERVAL_MS);
                }
            }
        };
        handler.postDelayed(takesOneTimeStep, UPDATE_INTERVAL_MS); // Initial call to start the process.
    }

    private void stopTakingTimeSteps() {
        if (handler != null && takesOneTimeStep != null) {
            handler.removeCallbacks(takesOneTimeStep); // Prevents memory leaks and stops the task.
        }
    }
}