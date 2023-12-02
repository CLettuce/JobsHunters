package com.example.jobshunters.network;

import com.example.jobshunters.Model.Offer;

import java.util.List;

public class OfferResponse {
    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
