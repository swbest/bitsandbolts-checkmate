package com.bitsandbolts.checkmate;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng singapore = new LatLng(1.290270, 103.851959);
        // mMap.addMarker(new MarkerOptions().position(singapore).title("Marker in Singapore"));

        List<Place> points = parseData();

        for (Place location : points) {
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(point));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
    }

    private List<Place> parseData() {
        final String fileName = "data.csv";
        List<List<String>> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                List<String> values = new ArrayList<>();
                for (String s : temp) {
                    if (!s.equals("")) {
                        values.add(s);
                    }
                }
                data.add(values);
            }
        } catch (FileNotFoundException e) {
            for (int i = 0; i < 10; i++) {
                List<String> stuff = Arrays.asList("0", "0","0","0","0","0","0");
                data.add(stuff);
            }
        } catch (IOException e) {
            for (int i = 0; i < 10; i++) {
                List<String> stuff = Arrays.asList("1", "1","1","1","1","1","1");
                data.add(stuff);
            }
        }
        return createPins(data);
    }

    private List<Place> createPins(List<List<String>> data) {
        List<Place> pins = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            if (!data.get(i).isEmpty()) {
                Place place = Place.createPoint(data.get(i));
                pins.add(place);
            }
        }
        return pins;
    }
}
