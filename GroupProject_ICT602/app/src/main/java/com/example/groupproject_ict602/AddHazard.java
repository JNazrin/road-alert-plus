package com.example.groupproject_ict602;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddHazard extends AppCompatActivity {

    EditText etName, etType, etLocation, etDesc, etLat, etLng;
    RequestQueue queue;
    final String URL = "http://192.168.50.24/maklumat/api2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_hazard);

        queue = Volley.newRequestQueue(getApplicationContext());

        etName = (EditText) findViewById(R.id.etName);
        etType = (EditText) findViewById(R.id.etType);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etDesc = (EditText) findViewById(R.id.etDesc);
        etLat = (EditText) findViewById(R.id.etLat);
        etLng = (EditText) findViewById(R.id.etLng);

        Button button = (Button) findViewById(R.id.btnSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //volley
                makeRequest();
            }
        });
    }

    public void makeRequest(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, errorListener){
            @Override
            protected Map<String, String> getParams(){
                Map <String, String> params = new HashMap<>();

                params.put("name", etName.getText().toString());
                params.put("type", etType.getText().toString());
                params.put("location", etLocation.getText().toString());
                params.put("description", etDesc.getText().toString());
                params.put("lat", etLat.getText().toString());
                params.put("lng", etLng.getText().toString());

                return params;
            }
        };
        queue.add(stringRequest);
    }

    public Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}
