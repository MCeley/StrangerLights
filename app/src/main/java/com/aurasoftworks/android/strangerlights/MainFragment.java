package com.aurasoftworks.android.strangerlights;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aurasoftworks.android.strangerlights.bluetooth.BluetoothService;

public class MainFragment extends Fragment implements View.OnClickListener {

    public interface LightCommandListener {
        void processCommand(String command);
    }

    private LightCommandListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof LightCommandListener) {
            mListener = (LightCommandListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        view.findViewById(R.id.button_flash_lights).setOnClickListener(this);
        view.findViewById(R.id.button_show_lights).setOnClickListener(this);
        view.findViewById(R.id.button_turn_off).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button_flash_lights) {
            mListener.processCommand(BluetoothService.COMMAND_FLASH_LIGHTS);
        } else if(id == R.id.button_show_lights) {
            mListener.processCommand(BluetoothService.COMMAND_SHOW_LIGHTS);
        } else if(id == R.id.button_turn_off) {
            mListener.processCommand(BluetoothService.COMMAND_TURN_OFF);
        }
    }

    public String getTextToSend() {
        View view = getView();

        if(view != null) {
            EditText et = view.findViewById(R.id.edit_string);
            if(et != null) {
                return et.getText().toString();
            }
        }

        return null;
    }
}
