package com.example.groupproject_ict602;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HazardInfo extends AppCompatActivity {

    private ListView listView;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hazard_info);

        this.setTitle("Latest News");

        listView = findViewById(R.id.listHazard);
        List<Maklumat> maklumats = new ArrayList<>();
        adapter = new DataAdapter(this, maklumats);
        listView.setAdapter(adapter);

        String url = "http://192.168.50.24/maklumat/json.php";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parse the JSON response using Gson
                        Gson gson = new Gson();
                        Type dataType = new TypeToken<List<Maklumat>>() {}.getType();
                        List<Maklumat> newDataItems = gson.fromJson(response.toString(), dataType);

                        // Update the data items and notify the adapter
                        maklumats.clear();
                        maklumats.addAll(newDataItems);
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        Toast.makeText(HazardInfo.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }
}
