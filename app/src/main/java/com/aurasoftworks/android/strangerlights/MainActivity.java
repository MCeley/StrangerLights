package com.aurasoftworks.android.strangerlights;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.aurasoftworks.android.strangerlights.bluetooth.BluetoothService;
import com.aurasoftworks.android.strangerlights.bluetooth.BluetoothUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainFragment.LightCommandListener {

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> { });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            String text = getTextFromFragment();
            sendTextViaBluetooth(text);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_connect) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) !=
                    PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
                requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getTextFromFragment() {
        MainFragment fragment = (MainFragment)getSupportFragmentManager()
                .findFragmentById(R.id.main_fragment);

        if(fragment != null) {
            return fragment.getTextToSend();
        }

        return null;
    }

    @Override
    public void processCommand(String command) {
        sendTextViaBluetooth(command);
    }

    private void sendTextViaBluetooth(String text) {
        if(BluetoothUtil.isBluetoothEnabled()) {
            BluetoothUtil.findBluetoothDevice();

            if(BluetoothUtil.getConnectedDevice() != null) {
                Intent intent = new Intent(this, BluetoothService.class);
                intent.putExtra(BluetoothService.EXTRA_BLUETOOTH_DATA, text);
                startService(intent);
            }
        }
    }
}
