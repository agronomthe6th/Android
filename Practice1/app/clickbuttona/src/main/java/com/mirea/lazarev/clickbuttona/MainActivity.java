package com.mirea.lazarev.clickbuttona;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Text = (TextView) findViewById(R.id.text);
        Button buttonOk = (Button) findViewById(R.id.confirm_button);
        Button buttonCancel = (Button) findViewById(R.id.cancel_button);

        @SuppressLint("SetTextI18n") View.OnClickListener onClickBtnOk = view -> Text.setText("Нажата кнопка 'Ok'");
        @SuppressLint("SetTextI18n") View.OnClickListener onClickBtnCancel = view -> Text.setText("Нажата кнопка 'Cancel'");

        buttonOk.setOnClickListener(onClickBtnOk);
        buttonCancel.setOnClickListener(onClickBtnCancel);

    }
}