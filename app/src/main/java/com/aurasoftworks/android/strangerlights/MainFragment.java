package com.aurasoftworks.android.strangerlights;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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
