package com.aurasoftworks.android.strangerlights.bluetooth;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aurasoftworks.android.strangerlights.lights.LightsUtil;


public class BluetoothService extends IntentService {

    private static final String LOG_TAG = "ST_BluetoothService";

    public static final String COMMAND_TURN_OFF = "ST_TURN_OFF";
    public static final String COMMAND_SHOW_LIGHTS = "ST_SHOW_LIGHTS";
    public static final String COMMAND_FLASH_LIGHTS = "ST_FLASH_LIGHTS";

    public static final String EXTRA_BLUETOOTH_DATA =
            "com.aurasoftworks.android.EXTRA_BLUETOOTH_DATA";

    public BluetoothService() {
        super(LOG_TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent == null) {
            Log.d(LOG_TAG, "Intent is null.");
            return;
        }

        String textData = intent.getStringExtra(EXTRA_BLUETOOTH_DATA);
        if(textData == null) {
            Log.d(LOG_TAG, "No string passed into service.");
            return;
        }

        byte[] data = null;
        if(textData.equals(COMMAND_TURN_OFF)) {
            data = new byte[] {40};
        } else if(textData.equals(COMMAND_SHOW_LIGHTS)) {
            data = new byte[] {41};
        } else if(textData.equals(COMMAND_FLASH_LIGHTS)) {
            data = new byte[] {42};
        } else {
            data = LightsUtil.getLightBytesFromString(textData);
        }

        if(data == null) {
            Log.d(LOG_TAG, "Failed to convert text data to bytes.");
            return;
        }

        boolean success = BluetoothUtil.sendDataOverBluetooth(data);
        if(success) {
            Log.d(LOG_TAG, "Success");
        } else {
            Log.d(LOG_TAG, "Failure");
        }
    }
}
