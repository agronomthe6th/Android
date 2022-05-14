package ru.mirea.lazarev.mireaproject;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AudioRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioRecordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final int REQUEST_CODE_PERMISSION = 101;
    private String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static boolean isWork;
    private File audioFile;
    private Uri audioUri;
    private MediaRecorder mediaRecorder;
    private Activity act;
    private Button start;
    private Button stop;
    private Button play;


    public AudioRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AudioRecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AudioRecordFragment newInstance(String param1, String param2) {
        AudioRecordFragment fragment = new AudioRecordFragment();
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
        act = getActivity();
        isWork = hasPermissions(act, PERMISSIONS);
        if (!isWork){
            ActivityCompat.requestPermissions(act, PERMISSIONS,
                    REQUEST_CODE_PERMISSION);
        }
        mediaRecorder = new MediaRecorder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_record, container, false);

        start = view.findViewById(R.id.record);
        stop = view.findViewById(R.id.stop);
        play = view.findViewById(R.id.play);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onStartRecording();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopRecording();
            }
        });

        // Inflate the layout for this fragment
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopRecording();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayRecording();
            }
        });
        // Inflate the layout for this fragment
        return view;    }
    public static boolean hasPermissions(Context context, String[] permissions){
        if(context != null && permissions != null){
            for (String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context,permission) !=
                        PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    private void startRecording() throws IOException {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            if (audioFile == null){
                audioFile = new File(act.getExternalFilesDir(
                        Environment.DIRECTORY_MUSIC), "mirea.3gp");
            }
            mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(act, "Recording started!", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.release();
            Toast.makeText(act, "You are not recording right now!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void processAudioFile(){
        ContentValues values = new ContentValues();
        long current = System.currentTimeMillis();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + audioFile.getName());
        values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, audioFile.getAbsolutePath());
        ContentResolver contentResolver = act.getContentResolver();

        Uri baseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        audioUri = contentResolver.insert(baseUri, values);

        act.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, audioUri));
    }

    public void onStopRecording(){
        start.setEnabled(true);
        stop.setEnabled(false);
        play.setEnabled(true);
        stopRecording();
        processAudioFile();
    }

    public void onStartRecording() throws IOException {
        start.setEnabled(false);
        play.setEnabled(false);
        startRecording();
    }

    public void onPlayRecording(){
        MediaPlayer mediaPlayer = MediaPlayer.create(act,audioUri);
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        });
        thr.start();
    }
}