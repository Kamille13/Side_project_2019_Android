package com.example.sideproject2019.model;

import java.util.ArrayList;

public class Dvf {

    private Double  lat;
    private Double lon;
    private Integer valeur_fonciere;
    private String numero_plan;
    private String type_local;
    private Integer surface_relle_bati;
    private Integer nombre_pieces_principales;

    public Dvf() {
    }

    public Dvf(Double lat, Double lon, Integer valeur_fonciere, String numero_plan,String type_local, Integer surface_relle_bati, Integer nombre_pieces_principales) {
        this.lat = lat;
        this.lon = lon;
        this.valeur_fonciere = valeur_fonciere;
        this.numero_plan = numero_plan;
        this.type_local = type_local;
        this.surface_relle_bati = surface_relle_bati;
        this.nombre_pieces_principales = nombre_pieces_principales;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getValeur_fonciere() {
        return valeur_fonciere;
    }

    public void setValeur_fonciere(Integer valeur_fonciere) {
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

    public Integer getSurface_relle_bati() {
        return surface_relle_bati;
    }

    public void setSurface_relle_bati(Integer surface_relle_bati) {
        this.surface_relle_bati = surface_relle_bati;
    }

    public Integer getNombre_pieces_principales() {
        return nombre_pieces_principales;
    }

    public void setNombre_pieces_principales(Integer nombre_pieces_principales) {
        this.nombre_pieces_principales = nombre_pieces_principales;
    }

    public interface DvfListener {
        void onResult(ArrayList<Dvf> dvfs);
    }
}
