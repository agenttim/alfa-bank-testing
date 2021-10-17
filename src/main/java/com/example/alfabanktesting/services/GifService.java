package com.example.alfabanktesting.services;

import com.example.alfabanktesting.models.gif.AllGifsJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Gifs", url = "${giphy.api.url}")
public interface GifService {

    @GetMapping("search?api_key=${giphy.app.key}&q=${rate.depreciated}&limit=10&offset=0&rating=g&lang=en")
    AllGifsJson getNegativeGif();

    @GetMapping("search?api_key=${giphy.app.key}&q=${rate.increased}&limit=10&offset=0&rating=g&lang=en")
    AllGifsJson getPositiveGif();

    @GetMapping("search?api_key=${giphy.app.key}&q=${rate.not.changed}&limit=10&offset=0&rating=g&lang=en")
    AllGifsJson getEqualGif();
}
