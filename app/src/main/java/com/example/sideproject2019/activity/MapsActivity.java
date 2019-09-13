package com.example.sideproject2019.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.sideproject2019.R;
import com.example.sideproject2019.UserSingleton;
import com.example.sideproject2019.model.Dvf;
import com.example.sideproject2019.model.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.DelayedMapListener;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import java.util.ArrayList;

import static com.example.sideproject2019.ApiJsonDvf.extractAPI;
import static com.example.sideproject2019.ApiJsonDvf.extractAPI2;

public class MapsActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 4;
    GoogleApiClient mGoogleApiClient;
    MapView mapView = null;
    UserSingleton userSingleton = UserSingleton.getInstance();
    User user = userSingleton.getUser();
    private static boolean dropOff = true;
    private static int zoom = 15;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        extractAPI(MapsActivity.this, dropOff, zoom, new Dvf.DvfListener(){
            @Override
            public void onResult(ArrayList<Dvf> dvfs) {
              for(int i = 0; i < dvfs.size(); i++){
                  MapView mapView = findViewById(R.id.map);

                  Dvf dvf = new Dvf(dvfs.get(i).getLat(),dvfs.get(i).getLon(),dvfs.get(i).getValeur_fonciere(),dvfs.get(i).getNumero_plan(),dvfs.get(i).getType_local(),dvfs.get(i).getSurface_relle_bati(),dvfs.get(i).getNombre_pieces_principales());
                  Double latitude = 0.0;
                  if(dvf.getLat() != "null"){
                      latitude = Double.valueOf(dvf.getLat());
                  }
                  Double longitude = 0.0;
                  if(dvf.getLon() != "null"){
                      longitude = Double.valueOf(dvf.getLon());
                  }
                  String nombre_pieces_principales = dvf.getNombre_pieces_principales();
                  String numero_plan = dvf.getNumero_plan();
                  String surface_relle_bati = dvf.getSurface_relle_bati();
                  String type_local = dvf.getType_local();
                  String valeur_fonciere = dvf.getValeur_fonciere();
                  GeoPoint startPoint = new GeoPoint(latitude, longitude);
                  Marker startMarker = new Marker(mapView);
                  startMarker.setPosition(startPoint);
                  startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                  startMarker.setTitle(type_local);

                  startMarker.setSnippet("Nombres de piéces principales : " + nombre_pieces_principales + "        " + "Surface réelle habitable : " + surface_relle_bati + "m²"+ "         " + "Valeur fonciére : " + valeur_fonciere + "€");
                  mapView.getOverlays().add(startMarker);

              }
            }
        });
        extractAPI2(MapsActivity.this, dropOff, zoom, new Dvf.DvfListener(){
            @Override
            public void onResult(ArrayList<Dvf> dvfs) {
              for(int i = 0; i < dvfs.size(); i++){
                  MapView mapView = findViewById(R.id.map);

                  Dvf dvf = new Dvf(dvfs.get(i).getLat(),dvfs.get(i).getLon(),dvfs.get(i).getValeur_fonciere(),dvfs.get(i).getNumero_plan(),dvfs.get(i).getType_local(),dvfs.get(i).getSurface_relle_bati(),dvfs.get(i).getNombre_pieces_principales());
                  Double latitude = 0.0;
                  if(dvf.getLat() != "null"){
                      latitude = Double.valueOf(dvf.getLat());
                  }
                  Double longitude = 0.0;
                  if(dvf.getLon() != "null"){
                      longitude = Double.valueOf(dvf.getLon());
                  }
                  String nombre_pieces_principales = dvf.getNombre_pieces_principales();
                  String numero_plan = dvf.getNumero_plan();
                  String surface_relle_bati = dvf.getSurface_relle_bati();
                  String type_local = dvf.getType_local();
                  String valeur_fonciere = dvf.getValeur_fonciere();
                  GeoPoint startPoint = new GeoPoint(latitude, longitude);
                  Marker startMarker = new Marker(mapView);
                  startMarker.setPosition(startPoint);
                  startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                  startMarker.setTitle(type_local);
                  startMarker.setSnippet("Nombres de piéces principales : " + nombre_pieces_principales + "        " + "Surface réelle habitable : " + surface_relle_bati + "         " + "Valeur fonciére : " + valeur_fonciere);
                  mapView.getOverlays().add(startMarker);
              }
            }
        });

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(getPackageName());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        setContentView(R.layout.activity_maps);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        checkPermissionsState();
    }

    void checkPermissionsState() {
        int internetPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);

        int networkStatePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE);

        int writeExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int coarseLocationPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        int fineLocationPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        int wifiStatePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_WIFI_STATE);

        if (internetPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                networkStatePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                writeExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                fineLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                wifiStatePermissionCheck == PackageManager.PERMISSION_GRANTED) {

            setupMap();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_WIFI_STATE},
                    MULTIPLE_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean somePermissionWasDenied = false;
                    for (int result : grantResults) {
                        if (result == PackageManager.PERMISSION_DENIED) {
                            somePermissionWasDenied = true;
                        }
                    }
                    if (somePermissionWasDenied) {
                        Toast.makeText(this, "Cant load maps without all the permissions granted", Toast.LENGTH_SHORT).show();
                    } else {
                        setupMap();
                    }
                } else {
                    Toast.makeText(this, "Cant load maps without all the permissions granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    private void setupMap() {

        MapView mapView = findViewById(R.id.map);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setTileSource(TileSourceFactory.MAPNIK);


        IMapController mapController = mapView.getController();
        mapController.setZoom(20);
        GeoPoint startPoint = new GeoPoint(43.600000, 1.433333);
        mapController.setCenter(startPoint);

        CompassOverlay compassOverlay = new CompassOverlay(this, mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        mapView.setMapListener(new DelayedMapListener(new MapListener() {
            public boolean onZoom(final ZoomEvent e) {
                MapView mapView = findViewById(R.id.map);

                String latitudeStr = "" + mapView.getMapCenter().getLatitude();
                String longitudeStr = "" + mapView.getMapCenter().getLongitude();

                String latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length(), 7));
                String longitudeFormattedStr = longitudeStr.substring(0, Math.min(longitudeStr.length(), 7));

                Log.i("zoom", "" + mapView.getMapCenter().getLatitude() + ", " + mapView.getMapCenter().getLongitude());
                return true;
            }

            public boolean onScroll(final ScrollEvent e) {
                MapView mapView = (MapView) findViewById(R.id.map);

                String latitudeStr = "" + mapView.getMapCenter().getLatitude();
                String longitudeStr = "" + mapView.getMapCenter().getLongitude();

                String latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length(), 7));
                String longitudeFormattedStr = longitudeStr.substring(0, Math.min(longitudeStr.length(), 7));

                Log.i("scroll", "" + mapView.getMapCenter().getLatitude() + ", " + mapView.getMapCenter().getLongitude());
                return true;
            }
        }, 1000));
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
