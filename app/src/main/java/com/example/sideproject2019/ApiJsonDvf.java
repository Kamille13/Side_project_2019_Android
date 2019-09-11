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
                                String lat = "";
                                if (rec.has("lat")) {
                                    lat =  rec.get("lat").toString();
                                }
                                String lon = "";
                                if (rec.has("lon")) {
                                    lon =  rec.get("lon").toString();
                                }
                                String valeur_fonciere = "";
                                if(rec.has("valeur_fonciere")){
                                    valeur_fonciere = rec.get("valeur_fonciere").toString();
                                }
                                String numero_plan = "";
                                if(rec.has("numero_plan")){
                                    numero_plan = rec.get("numero_plan").toString();
                                }
                                String type_local = "";
                                if(rec.has("type_local")){
                                    type_local =  rec.get("type_local").toString();
                                }
                                String surface_relle_bati = "";
                                if(rec.has("surface_relle_bati")){
                                    surface_relle_bati = rec.get("surface_relle_bati").toString();
                                }
                                String nombre_pieces_principales = "";
                                if(rec.has("nombre_pieces_principales")){
                                    nombre_pieces_principales = rec.get("nombre_pieces_principales").toString();
                                }

                                Dvf dvf = new Dvf(lat, lon, valeur_fonciere,numero_plan,type_local,surface_relle_bati,nombre_pieces_principales);
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
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}