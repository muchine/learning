package com.muchine.chapter2_4.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muchine.chapter2_4.R;
import com.muchine.chapter2_4.model.Event;
import com.muchine.chapter2_4.model.EventHolder;

/**
 * Created by sejoonlim on 8/29/16.
 */
public class NewEventFragment extends DialogFragment {

    private View rootView;

    private EventHolder eventHolder;

    private String date;

    private OnSaveListener onSaveListener;

    public void initialize(EventHolder eventHolder, String date) {
        this.eventHolder = eventHolder;
        this.date = date;
    }

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mission08_dialog, container, false);

        initSaveButton();
        initCloseButton();

        return rootView;
    }

    private void initSaveButton() {
        Button button = (Button) rootView.findViewById(R.id.mission08SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText hourEdit = (EditText) rootView.findViewById(R.id.mission08HourEdit);
                String hour = hourEdit.getText().toString();

                EditText minuteEdit = (EditText) rootView.findViewById(R.id.mission08MinuteEdit);
                String minute = minuteEdit.getText().toString();

                EditText eventEdit = (EditText) rootView.findViewById(R.id.mission08EventEdit);
                String eventName = eventEdit.getText().toString();

                Event event = new Event(hour, minute, eventName);
                eventHolder.save(date, event);

                Toast.makeText(getContext(), "일정이 저장되었습니다", Toast.LENGTH_LONG).show();

                if (onSaveListener != null) onSaveListener.onSave();

                dismiss();
            }
        });
    }

    private void initCloseButton() {
        Button button = (Button) rootView.findViewById(R.id.mission08CloseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public interface OnSaveListener {
        void onSave();
    }

}
