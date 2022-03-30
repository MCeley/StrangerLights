package com.aurasoftworks.android.strangerlights.bluetooth;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import androidx.annotation.WorkerThread;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothUtil {

    public static final UUID BLUETOOTH_DEVICE_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private static final String BLUETOOTH_DEVICE_NAME = "HC-06";

    private static BluetoothAdapter bluetoothAdapter;
    private static BluetoothDevice bluetoothDevice;

    private static BluetoothAdapter getBluetoothAdapter() {
        if(bluetoothAdapter == null) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        return bluetoothAdapter;
    }

    public static boolean isBluetoothEnabled() {

        BluetoothAdapter adapter = getBluetoothAdapter();

        return adapter.isEnabled();
    }

    public static void findBluetoothDevice() {
        Set<BluetoothDevice> devices = getBluetoothAdapter().getBondedDevices();

        if(devices != null) {
            for (BluetoothDevice device : devices) {
                String name = device.getName();
                if (name != null && name.equals(BLUETOOTH_DEVICE_NAME)) {
                    bluetoothDevice = device;
                    break;
                }
            }
        }
    }

    public static BluetoothDevice getConnectedDevice() {
        return bluetoothDevice;
    }

    @WorkerThread
    public static boolean sendDataOverBluetooth(byte[] data) {

        BluetoothSocket outputSocket = null;

        try {
            outputSocket = bluetoothDevice
                    .createRfcommSocketToServiceRecord(BLUETOOTH_DEVICE_UUID);
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(outputSocket == null) {
            return false;
        }

        try {
            bluetoothAdapter.cancelDiscovery();

            outputSocket.connect();

            OutputStream os = outputSocket.getOutputStream();
            os.write(data);

            try {
                Thread.sleep(1500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            os.close();

            outputSocket.close();

        } catch(IOException e) {
            e.printStackTrace();

            try {
                outputSocket.close();
            } catch(IOException e1) {
                e1.printStackTrace();
            }

            return false;
        }

        return true;
    }
}
