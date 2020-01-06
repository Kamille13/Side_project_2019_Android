package com.example.sideproject2019.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sideproject2019.GeocodingLocation;
import com.example.sideproject2019.R;

public class SearchActivity extends Activity {

        Button addressButton;
        TextView addressTV;
        TextView latLongTV;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);


            addressTV = (TextView) findViewById(R.id.tvAdress);
            latLongTV = (TextView) findViewById(R.id.tvAdressResult);

            addressButton = (Button) findViewById(R.id.btSubmit);
            addressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    EditText editText = (EditText) findViewById(R.id.etAdress);
                    String address = editText.getText().toString();

                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(address,
                            getApplicationContext(), new GeocoderHandler());
                }
            });

        }

        private class GeocoderHandler extends Handler {
            @Override
            public void handleMessage(Message message) {
                String locationAddress;
                switch (message.what) {
                    case 1:
                        Bundle bundle = message.getData();
                        locationAddress = bundle.getString("address");
                        break;
                    default:
                        locationAddress = null;
                }
                latLongTV.setText(locationAddress);
            }
        }
    }
