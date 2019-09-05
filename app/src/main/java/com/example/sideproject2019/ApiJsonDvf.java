package com.example.sideproject2019;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sideproject2019.model.Dvf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ApiJsonDvf {

    public static void extractAPI(Context context, Boolean dropoff, int zoom, final Dvf.DvfListener listener) {
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
                                Double lat = 0.0;
                                if (rec.has("lat")) {
                                    lat = (Double) rec.get("lat");
                                }
                                Double lon = 0.0;
                                if (rec.has("lon")) {
                                    lon = (Double) rec.get("lon");
                                }
                                Integer valeur_fonciere = 0;
                                if(rec.has("valeur_fonciere")){
                                    valeur_fonciere = (Integer) rec.get("valeur_fonciere");
                                }
                                String numero_plan = "";
                                if(rec.has("numero_plan")){
                                    numero_plan = (String) rec.get("numero_plan");
                                }
                                String type_local = "";
                                if(rec.has("type_local")){
                                    type_local = (String) rec.get("type_local");
                                }
                                Integer surface_relle_bati = 0;
                                if(rec.has("surface_relle_bati")){
                                    surface_relle_bati = (Integer) rec.get("surface_relle_bati");
                                }
                                Integer nombre_pieces_principales = 0;
                                if(rec.has("nombre_pieces_principales")){
                                    nombre_pieces_principales = (Integer) rec.get("nombre_pieces_principales");
                                }

                                Dvf dvf = new Dvf(lat, lon, valeur_fonciere, numero_plan,type_local, surface_relle_bati, nombre_pieces_principales);
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