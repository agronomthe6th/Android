package ru.mirea.lazarev.lifecicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Server server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server = new Server();
        getLifecycle().addObserver(server);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void stateUpdated() {
        //будет вызваться при каждом изменении состояния жизненого цикла у activity.
    }



}

