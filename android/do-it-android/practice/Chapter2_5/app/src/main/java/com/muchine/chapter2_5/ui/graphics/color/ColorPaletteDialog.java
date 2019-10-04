package com.muchine.chapter2_5.ui.graphics.color;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.muchine.chapter2_5.R;

/**
 * Created by sejoonlim on 8/31/16.
 */
public class ColorPaletteDialog extends DialogFragment {

    private ColorDataAdapter adapter;

    private ColorDataAdapter.OnColorSelectionListener onColorSelectionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog, container, false);

        initGridView(rootView);
        initCloseButton(rootView);

        return rootView;
    }

    public void setOnColorSelectionListener(ColorDataAdapter.OnColorSelectionListener onColorSelectionListener) {
        this.onColorSelectionListener = onColorSelectionListener;
    }

    private void initGridView(View view) {
        GridView grid = (GridView) view.findViewById(R.id.paintColorGrid);
        grid.setColumnWidth(14);
        grid.setBackgroundColor(Color.GRAY);
        grid.setVerticalSpacing(4);
        grid.setHorizontalSpacing(4);

        adapter = new ColorDataAdapter(getContext());
        adapter.setOnColorSelectionListener(new ColorDataAdapter.OnColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                onColorSelectionListener.onColorSelected(color);
                dismiss();
            }
        });

        grid.setAdapter(adapter);
        grid.setNumColumns(ColorDataAdapter.COLUMN_COUNT);
    }

    private void initCloseButton(View view) {
        Button button = (Button) view.findViewById(R.id.paintCloseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
