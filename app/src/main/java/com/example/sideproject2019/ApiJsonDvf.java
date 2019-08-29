package com.example.sideproject2019;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sideproject2019.model.Dvf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiJsonDvf {

    public static void extractAPI(Context context, final Location locationUser, Boolean dropoff, int zoom, final Dvf.DvfListener listener) {
        String json = null;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://api.cquest.org/dvf?code_postal=31000";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultats = response.getJSONArray("resultats");
                            ArrayList<Dvf> dvfs = new ArrayList<>();
                            for (int i = 0; i < resultats.length(); i++) {
                                JSONObject rec = (JSONObject) resultats.get(i);
                                long lat = 0;
                                if (rec.has("lat")) {
                                    lat = (long) rec.get("lat");
                                }
                                long lon = 0;
                                if (rec.has("lon")) {
                                    lon = (long) rec.get("lon");
                                }
                                String valeur_fonciere = (String) rec.get("valeur_fonciere");
                                String numero_plan = (String) rec.get("numero_plan");
                                String type_local = (String) rec.get("type_local");
                                String surface_relle_bati = (String) rec.get("surface_relle_bati");
                                String nombre_pieces_principales = (String) rec.get("nombre_pieces_principales");

                                Dvf dvf = new Dvf(lat, lon, valeur_fonciere, numero_plan, type_local, surface_relle_bati, nombre_pieces_principales);
                                dvfs.add(dvf);
                            }
                            listener.onResult(dvfs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonObjectRequest);
    }
}