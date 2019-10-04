package com.muchine.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by sejoonlim on 8/6/16.
 */
public class MenuFragment extends Fragment {

    private Button goMainButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        initWidgets(rootView);
        return rootView;
    }

    private void initWidgets(ViewGroup rootView) {
        goMainButton = (Button) rootView.findViewById(R.id.goMainButton);
        initGoMainButton();
    }

    private void initGoMainButton() {
        goMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onGoMainButtonClicked();
            }
        });
    }

}
