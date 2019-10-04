package com.muchine.chapter2_5.ui.graphics;

import android.graphics.Color;
import android.widget.Button;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class Eraser {

    private Button[] buttons;

    private boolean enabled = false;

    private int color;
    private float penWidth;

    public Eraser(Button... buttons) {
        this.buttons = buttons;
    }

    public void toggle(PaintBoard board) {
        enabled = !enabled;
        if (enabled)
            enable(board);
        else
            disable(board);
    }

    private void enable(PaintBoard board) {
        setButtonsEnabled(false);

        this.color = board.getColor();
        this.penWidth = board.getPenWidth();

        board.setColor(Color.WHITE);
        board.setPenWidth(15);
    }

    private void disable(PaintBoard board) {
        setButtonsEnabled(true);

        board.setColor(color);
        board.setPenWidth((int) penWidth);

        this.color = 0;
        this.penWidth = 0F;
    }

    private void setButtonsEnabled(boolean enabled) {
        for (Button button : buttons) {
            button.setEnabled(enabled);
            button.invalidate();
        }
    }
}
