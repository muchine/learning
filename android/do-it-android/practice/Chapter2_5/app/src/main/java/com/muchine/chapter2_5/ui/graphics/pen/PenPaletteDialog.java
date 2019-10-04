package com.muchine.chapter2_5.ui.graphics.pen;

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
 * Created by sejoonlim on 9/3/16.
 */
public class PenPaletteDialog extends DialogFragment {

    private PenDataAdapter adapter;

    private PenDataAdapter.OnPenSelectionListener onPenSelectionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog, container, false);

        initGridView(view);
        initCloseButton(view);

        return view;
    }

    public void setOnPenSelectionListener(PenDataAdapter.OnPenSelectionListener onPenSelectionListener) {
        this.onPenSelectionListener = onPenSelectionListener;
    }

    private void initGridView(View view) {
        GridView grid = (GridView) view.findViewById(R.id.paintColorGrid);
        grid.setColumnWidth(14);
        grid.setBackgroundColor(Color.GRAY);
        grid.setVerticalSpacing(4);
        grid.setHorizontalSpacing(4);

        adapter = new PenDataAdapter(getContext());
        adapter.setOnPenSelectionListener(new PenDataAdapter.OnPenSelectionListener() {
            @Override
            public void onPenSelected(int penWidth) {
                if (onPenSelectionListener != null) onPenSelectionListener.onPenSelected(penWidth);
                dismiss();
            }
        });

        grid.setAdapter(adapter);
        grid.setNumColumns(PenDataAdapter.COLUMN_COUNT);
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
