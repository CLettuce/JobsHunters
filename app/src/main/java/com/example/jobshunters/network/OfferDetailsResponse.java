package com.example.jobshunters.network;

import com.example.jobshunters.Model.Offer;
import com.google.gson.annotations.SerializedName;

public class OfferDetailsResponse {

    @SerializedName("data")
    private Offer offerDetails;

    public Offer getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(Offer offerDetails) {
        this.offerDetails = offerDetails;
    }
}
