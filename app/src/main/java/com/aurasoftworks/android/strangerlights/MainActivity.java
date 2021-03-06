package com.aurasoftworks.android.strangerlights;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.aurasoftworks.android.strangerlights.bluetooth.BluetoothService;
import com.aurasoftworks.android.strangerlights.bluetooth.BluetoothUtil;

public class MainActivity extends AppCompatActivity implements MainFragment.LightCommandListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = getTextFromFragment();
                sendTextViaBluetooth(text);
            }
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
