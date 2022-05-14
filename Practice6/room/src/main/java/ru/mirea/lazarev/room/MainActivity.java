package ru.mirea.lazarev.room;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ru.mirea.lazarev.room.AppDatabase db = ru.mirea.lazarev.room.App.getInstance().getDatabase();
        ru.mirea.lazarev.room.EmployeeDao employeeDao = db.employeeDao();

        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "John Smith";
        employee.salary = 10000;

        employeeDao.Insert(employee);

        List<Employee> employees = employeeDao.getAll();

        employee = employeeDao.getById(1);

        employee.salary = 20000;
        employeeDao.Update(employee);
        Log.d(TAG, employee.name + " " + employee.salary);
    }
}