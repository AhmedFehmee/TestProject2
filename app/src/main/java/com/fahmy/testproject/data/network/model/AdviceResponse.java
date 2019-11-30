package com.fahmy.testproject.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AdviceResponse {

    @Expose
    @SerializedName("fortune")
    private List<String> fortune;

    public List<String> getFortune() {
        return fortune;
    }

    public void setFortune(List<String> fortune) {
        this.fortune = fortune;
    }
}
