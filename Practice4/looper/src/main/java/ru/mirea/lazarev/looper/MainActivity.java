package ru.mirea.lazarev.looper;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyLooper myLooper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLooper = new MyLooper();
        myLooper.start();
    }

    public void onClick(View view){
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "20 лет, студент");
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessageDelayed(msg, 20);
        }
    }
}