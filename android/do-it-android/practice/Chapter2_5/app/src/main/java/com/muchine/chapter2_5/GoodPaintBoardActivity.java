package com.muchine.chapter2_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.muchine.chapter2_5.ui.graphics.Eraser;
import com.muchine.chapter2_5.ui.graphics.PaintBoard;
import com.muchine.chapter2_5.ui.graphics.color.ColorDataAdapter;
import com.muchine.chapter2_5.ui.graphics.color.ColorPaletteDialog;
import com.muchine.chapter2_5.ui.graphics.pen.PenDataAdapter;
import com.muchine.chapter2_5.ui.graphics.pen.PenPaletteDialog;

public class GoodPaintBoardActivity extends AppCompatActivity {

    private PaintBoard board;

    private Button colorButton;
    private Button penButton;
    private Button undoButton;

    private Eraser eraser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goodpaint);

        initPaintBoard();
        initColorButton();
        initPenButton();
        initUndoButton();
        initEraserButton();
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

}
