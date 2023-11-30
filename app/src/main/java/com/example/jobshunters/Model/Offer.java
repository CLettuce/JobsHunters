package com.example.jobshunters.Model;

public class Offer {
    private int id;
    private String title;
    private String description;

    // Constructor vacío (necesario para procesar objetos con Gson en Retrofit)
    public Offer() {
    }

    // Constructor con parámetros
    public Offer(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters y setters (necesarios para Gson)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

