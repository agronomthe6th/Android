package ru.mirea.lazarev.mireaproject;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorsFragment extends Fragment implements SensorEventListener {

    private TextView accelerator;
    private TextView motion;
    private TextView stationary;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor stationarySensor;
    private Sensor motionSensor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Activity act = getActivity();
        sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stationarySensor = sensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT);
        motionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensors, container, false);

        accelerator = view.findViewById(R.id.accelerometerSensor);
        motion = view.findViewById(R.id.motionSensor);
        stationary = view.findViewById(R.id.stationarySensor);
        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float valueAzimuth = sensorEvent.values[0];
            float valuePitch = sensorEvent.values[1];
            float valueRoll = sensorEvent.values[2];
            accelerator.setText(Float.toString(valueAzimuth));
            motion.setText(Float.toString(valuePitch));
            stationary.setText(Float.toString(valueRoll));
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_STATIONARY_DETECT){
            float stationaryt = sensorEvent.values[0];
            stationary.setText(Float.toString(stationaryt));
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_MOTION_DETECT){
            float motiont = sensorEvent.values[0];
            motion.setText(Float.toString(motiont));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}