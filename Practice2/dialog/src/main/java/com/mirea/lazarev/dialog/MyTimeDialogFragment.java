package com.mirea.lazarev.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                ((com.mirea.lazarev.dialog.MainActivity) getActivity()).onTimeDialog(hourOfDay, minute);
            }
        }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);

        return dialog;
    }
}