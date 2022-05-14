package com.mirea.lazarev.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickShowTimeDialog(View view) {
        MyTimeDialogFragment dialogFragment = new MyTimeDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickShowDateDialog(View view){
        com.mirea.lazarev.dialog.MyDateDialogFragment dialogFragment = new com.mirea.lazarev.dialog.MyDateDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked(){
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",Toast.LENGTH_LONG).show();
    }

    public void onCancelClicked(){
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",Toast.LENGTH_LONG).show();
    }

    public void onNeutralClicked(){
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",Toast.LENGTH_LONG).show();
    }

    public void onTimeDialog(int hour, int minute){
        Toast.makeText(getApplicationContext(), "Вы выбрали ".concat(Integer.toString(hour))
                .concat(" часов ").concat(Integer.toString(minute)).concat(" минут"), Toast.LENGTH_LONG).show();
    }

    public void onDateDialog(int year, int month, int day){
        Toast.makeText(getApplicationContext(), "Вы выбрали ".concat(Integer.toString(day)).concat(".")
                .concat(Integer.toString(month)).concat(".").concat(Integer.toString(year)), Toast.LENGTH_LONG).show();
    }
}