package com.muchine.chapter2_8.permission;

/**
 * Created by sejoonlim on 9/14/16.
 */
public interface PermissionRequestable {

    int getRequestCode();

    void requestPermission();

    void handlePermissionRequestResult(String[] permissions, int[] grantResults);

}
