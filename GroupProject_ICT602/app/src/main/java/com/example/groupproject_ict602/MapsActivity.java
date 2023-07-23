package com.example.groupproject_ict602;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.groupproject_ict602.databinding.ActivityMapsBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String URL = "https://api.syhrzkwn.dev/maklumat/json.php";

    MarkerOptions marker;
    LatLng centerLocation;
    Vector<MarkerOptions> markerOptions;
    RequestQueue requestQueue;
    Log log;
    Gson gson;
    Maklumat[] maklumat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gson = new GsonBuilder().create();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerLocation = new LatLng(3.0, 101);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Cawangan Alor Setar")
                .position(new LatLng(6.12, 100.37))
                .snippet("Open during MCO : 8am until 6ppm")
        );
        markerOptions.add(new MarkerOptions().title("Cawangan Bayan Lepas")
                .position(new LatLng(5.29, 100.259))
                .snippet("Open during MCO : 8am until 6ppm")
        );
        markerOptions.add(new MarkerOptions().title("Cawangan Ipoh")
                .position(new LatLng(4.61, 101.08))
                .snippet("Open during MCO : 8am until 6ppm")
        );
        markerOptions.add(new MarkerOptions().title("Cawangan Gua Musang")
                .position(new LatLng(4.8721, 100.96))
                .snippet("Open during MCO : 8am until 6ppm")
        );
        markerOptions.add(new MarkerOptions().title("Cawangan Arau")
                .position(new LatLng(6.45, 100.29))
                .snippet("Open during MCO : 8am until 6ppm")
        );
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
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        for(MarkerOptions mark : markerOptions){
            mMap.addMarker(mark);
        }

        enableMyLocation();
        sendRequest();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 8));
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            return;
        }

        // 2. Otherwise, request location permissions from the user.
        String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
        ActivityCompat.requestPermissions(this, perms, 200);
    }

    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,onSucess,onError);
        requestQueue.add(stringRequest);
    }

    public Response.Listener<String> onSucess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            maklumat = gson.fromJson(response, Maklumat[].class);

            //this will be display on logcat as debug
            log.d("Maklumat", "Number of Maklumat Data Point :"+maklumat.length);

            if(maklumat.length < 1){
                Toast.makeText(getApplicationContext(), "Problem retriving the JSON data", Toast.LENGTH_LONG).show();
                return;
            }

            for(Maklumat info:maklumat){
                Double lat = Double.parseDouble(info.getLat());
                Double lng = Double.parseDouble(info.getLng());
                String name = info.getName();
                String location = info.getLocation();
                String type = info.getType();
                String datetime = info.getDateTime();

                MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng))
                        .title(location + " - " + type)
                        .snippet(name + " ( " + datetime + " )")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

                mMap.addMarker(marker);
            }
        }
    };

    public Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}