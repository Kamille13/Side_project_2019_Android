package com.example.sideproject2019.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sideproject2019.GeocodingLocation;
import com.example.sideproject2019.PosSingleton;
import com.example.sideproject2019.R;
import com.example.sideproject2019.model.Position;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends Activity {

        Button addressButton;
        TextView addressTV;
        TextView latLongTV;
        Button Geolocate;
        PosSingleton pos = PosSingleton.getInstance();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);


            addressTV = (TextView) findViewById(R.id.tvAdress);
            latLongTV = (TextView) findViewById(R.id.tvAdressResult);
            Geolocate = findViewById(R.id.btGeo);
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
            Geolocate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText) findViewById(R.id.etAdress);
                    String address = editText.getText().toString();

                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(address,
                            getApplicationContext(), new GeocoderHandler());
                    Geocoder geocoder = new Geocoder(SearchActivity.this, Locale.getDefault());
                    try {
                        List<Address> adressList = geocoder.getFromLocationName(address,1);
                        Address address1 = adressList.get(0);
                        double lat = address1.getLatitude();
                        double lon = address1.getLongitude();
                        Position position = new Position(lat,lon);
                        pos.setPosition(position);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(SearchActivity.this, MapsActivity.class);
                    startActivity(intent);
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
