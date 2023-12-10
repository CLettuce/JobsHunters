package com.example.jobshunters.network;

import com.example.jobshunters.Model.Resources;
import com.google.gson.annotations.SerializedName;

public class ResourcesDetailResponse {

    @SerializedName("data")
    private Resources resourcesDetails;

    public Resources getResourcesDetails() {
        return resourcesDetails;
    }

    public void setResourcesDetails(Resources resourcesDetails) {
        this.resourcesDetails = resourcesDetails;
    }
}
