package com.muchine.chapter2_8.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class StoragePermissionManager implements PermissionRequestable {

    private static final int STORAGE_PERMISSION_CODE = 1;

    private final Activity activity;

    public StoragePermissionManager(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getRequestCode() {
        return STORAGE_PERMISSION_CODE;
    }

    @Override
    public void requestPermission() {
        int storagePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (storagePermission == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "권한 있음", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(activity, "Permission required.", Toast.LENGTH_LONG).show();
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "Permission rationale required.", Toast.LENGTH_LONG).show();
            return;
        }

        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ActivityCompat.requestPermissions(activity, permissions, STORAGE_PERMISSION_CODE);
    }

    public void handlePermissionRequestResult(String[] permissions, int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, permissions[i] + " authorized.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, permissions[i] + " not authorized.", Toast.LENGTH_LONG).show();
            }
        }
    }

}
