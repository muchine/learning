package com.muchine.chapter2_5.ui.graphics.painter;

import android.view.View;

/**
 * Created by sejoonlim on 9/3/16.
 */
public interface Paintable {

    void paint(View view);

    void begin(int x, int y);

    void moveTo(int x, int y);

    void end(int x, int y);

}
