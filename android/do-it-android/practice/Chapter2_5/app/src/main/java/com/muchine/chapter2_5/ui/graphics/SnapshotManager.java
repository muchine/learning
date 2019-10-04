package com.muchine.chapter2_5.ui.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Stack;

/**
 * Created by sejoonlim on 9/3/16.
 */
public class SnapshotManager {

    private static final int MAX_SNAPSHOT_COUNT = 10;

    private Stack snapshots = new Stack();

    public Bitmap pop() {
        try {
            return (Bitmap) snapshots.pop();
        } catch(Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

        return null;
    }

    public void createSnapshot(Bitmap bitmap, Paint paint) {
        if (bitmap == null) return;

        removeOldSnapshots();

        Bitmap snapshot = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(snapshot);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        snapshots.push(snapshot);

        Log.d(getClass().getSimpleName(), "createSnapshot() called.");
    }

    private void removeOldSnapshots() {
        while (snapshots.size() >= MAX_SNAPSHOT_COUNT) {
            Bitmap snapshot = (Bitmap) snapshots.get(snapshots.size() -1);
            snapshot.recycle();
            snapshots.remove(snapshot);
        }
    }
}
