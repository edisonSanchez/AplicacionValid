package com.poc.myapplication;

public class ItemTop{

    private String name;
    private String oyentes;
    private String url;

    public ItemTop(String nameP, String oyentesP, String urlP){
        name = nameP;
        oyentes = oyentesP;
        url = urlP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOyentes() {
        return oyentes;
    }

    public void setOyentes(String oyentes) {
        this.oyentes = oyentes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
