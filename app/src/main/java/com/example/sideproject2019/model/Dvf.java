package com.example.sideproject2019.model;

import java.util.ArrayList;


public class Dvf {

    private String  lat;
    private String lon;
    private String valeur_fonciere;
    private String numero_plan;
    private String type_local;
    private String surface_relle_bati;
    private String nombre_pieces_principales;

    public Dvf() {
    }

    public Dvf(String lat, String lon, String valeur_fonciere, String numero_plan, String type_local, String surface_relle_bati, String nombre_pieces_principales) {
        this.lat = lat;
        this.lon = lon;
        this.valeur_fonciere = valeur_fonciere;
        this.numero_plan = numero_plan;
        this.type_local = type_local;
        this.surface_relle_bati = surface_relle_bati;
        this.nombre_pieces_principales = nombre_pieces_principales;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getValeur_fonciere() {
        return valeur_fonciere;
    }

    public void setValeur_fonciere(String valeur_fonciere) {
        this.valeur_fonciere = valeur_fonciere;
    }

    public String getNumero_plan() {
        return numero_plan;
    }

    public void setNumero_plan(String numero_plan) {
        this.numero_plan = numero_plan;
    }

    public String getType_local() {
        return type_local;
    }

    public void setType_local(String type_local) {
        this.type_local = type_local;
    }

    public String getSurface_relle_bati() {
        return surface_relle_bati;
    }

    public void setSurface_relle_bati(String surface_relle_bati) {
        this.surface_relle_bati = surface_relle_bati;
    }

    public String getNombre_pieces_principales() {
        return nombre_pieces_principales;
    }

    public void setNombre_pieces_principales(String nombre_pieces_principales) {
        this.nombre_pieces_principales = nombre_pieces_principales;
    }

    public interface DvfListener {
        void onResult(ArrayList<Dvf> dvfs);
    }
}
