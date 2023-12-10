package com.example.jobshunters.network;


import com.example.jobshunters.Model.Resources;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResourcesResponse {
    @SerializedName("data")
    private List<Resources> resources;

    public List<Resources> getResources() {
        return resources;
    }

    public void setResources(List<Resources> resources) {
        this.resources = resources;
    }
}
