package com.example.alfabanktesting.models.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSectionGifsJson {

    @JsonProperty("images")
    public ImagesSectionGifsJson images;

    public ImagesSectionGifsJson getImages() {
        return images;
    }
}
