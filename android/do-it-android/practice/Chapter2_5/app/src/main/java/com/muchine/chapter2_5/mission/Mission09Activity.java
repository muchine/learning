package com.muchine.chapter2_5.mission;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.muchine.chapter2_5.R;
import com.muchine.chapter2_5.ui.graphics.Eraser;
import com.muchine.chapter2_5.ui.graphics.PaintBoard;
import com.muchine.chapter2_5.ui.graphics.color.ColorDataAdapter;
import com.muchine.chapter2_5.ui.graphics.color.ColorPaletteDialog;
import com.muchine.chapter2_5.ui.graphics.pen.PenDataAdapter;
import com.muchine.chapter2_5.ui.graphics.pen.PenPaletteDialog;

public class Mission09Activity extends AppCompatActivity {

    private PaintBoard board;

    private Button colorButton;
    private Button penButton;
    private Button undoButton;

    private Eraser eraser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission09);

        initPaintBoard();
        initColorButton();
        initPenButton();
        initUndoButton();
        initEraserButton();

        initCapRadioButtons();
    }

    private void initPaintBoard() {
        board = new PaintBoard(this);

        LinearLayout layout = (LinearLayout) findViewById(R.id.goodPaintBoardLayout);
        layout.addView(board);
    }

    private void initColorButton() {
        colorButton = (Button) findViewById(R.id.goodPaintColorButton);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPaletteDialog dialog = new ColorPaletteDialog();
                dialog.setOnColorSelectionListener(new ColorDataAdapter.OnColorSelectionListener() {
                    @Override
                    public void onColorSelected(int color) {
                        board.setColor(color);
                    }
                });

                dialog.show(getSupportFragmentManager(), "color");
            }
        });
    }

    private void initPenButton() {
        penButton = (Button) findViewById(R.id.goodPaintPenButton);
        penButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PenPaletteDialog dialog = new PenPaletteDialog();
                dialog.setOnPenSelectionListener(new PenDataAdapter.OnPenSelectionListener() {
                    @Override
                    public void onPenSelected(int penWidth) {
                        board.setPenWidth(penWidth);
                    }
                });

                dialog.show(getSupportFragmentManager(), "pen");
            }
        });
    }

    private void initUndoButton() {
        undoButton = (Button) findViewById(R.id.goodPaintUndoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.undo();
            }
        });
    }

    private void initEraserButton() {
        this.eraser = new Eraser(colorButton, penButton, undoButton);

        Button button = (Button) findViewById(R.id.goodPaintEraserButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraser.toggle(board);
            }
        });
    }

    private void initCapRadioButtons() {
        initRadioButton(R.id.buttCapButton, Paint.Cap.BUTT);
        initRadioButton(R.id.roundCapButton, Paint.Cap.ROUND);
        initRadioButton(R.id.squareCapButton, Paint.Cap.SQUARE);
    }

    private void initRadioButton(int resId, final Paint.Cap cap) {
        RadioButton button = (RadioButton) findViewById(resId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.setStrokeCap(cap);
            }
        });
    }

}
