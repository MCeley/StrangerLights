package com.aurasoftworks.android.strangerlights.bluetooth;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


public class BluetoothService extends IntentService {

    public BluetoothService() {
        super("BluetoothService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
