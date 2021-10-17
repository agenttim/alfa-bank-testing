package com.example.alfabanktesting;

import com.example.alfabanktesting.models.gif.AllGifsJson;
import com.example.alfabanktesting.models.gif.DataSectionGifsJson;
import com.example.alfabanktesting.models.gif.Gif;
import com.example.alfabanktesting.models.gif.ImagesSectionGifsJson;
import com.example.alfabanktesting.services.GifService;
import com.example.alfabanktesting.services.RateService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1L);
    private final RateService rateService;
    private final GifService gifService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    //    Example request: "http://localhost:8080/compare/USD"
    @RequestMapping(value = "/compare/{custom}", method = RequestMethod.GET)
    public String compareCurrency(@Value("${rate.base}") String baseCurrency,
                                  @PathVariable String custom) {
        LOGGER.debug("Executing method compareCurrency(). Custom currency is " + custom);
        Map<String, BigDecimal> allRatesToday = rateService.getLatestRates().getRates();
        Map<String, BigDecimal> allRatesYesterday = rateService.getHistoricalRates(YESTERDAY.toString()).getRates();

        BigDecimal baseCurrencyToday = allRatesToday.get(baseCurrency);
        BigDecimal baseCurrencyYesterday = allRatesYesterday.get(baseCurrency);
        BigDecimal customCurrencyToday = allRatesToday.get(custom.toUpperCase());
        BigDecimal customCurrencyYesterday = allRatesYesterday.get(custom.toUpperCase());

        int rateHigher = baseCurrencyToday.subtract(customCurrencyToday)
                .compareTo(baseCurrencyYesterday.subtract(customCurrencyYesterday));
        List<Gif> gifList = getGifList(rateHigher);
        return "redirect:" + gifList.get(getRandomGif(gifList.size()));
    }

    //    get a list with links to all the GIFs
    public List<Gif> getGifList(int rateHigher) {
        LOGGER.debug("Executing method getGifList(). RateHigher is " + rateHigher);
        return getAllGifsJson(rateHigher).getMainData()
                .stream().map(DataSectionGifsJson::getImages).collect(Collectors.toList())
                .stream().map(ImagesSectionGifsJson::getGif).collect(Collectors.toList());
    }

    //    get a page in JSON format depending on the exchange rate
    public AllGifsJson getAllGifsJson(int rateHigher) {
        LOGGER.debug("Executing method getAllGifsJson(). RateHigher is " + rateHigher);
        return rateHigher > 0 ? gifService.getPositiveGif()
                : rateHigher < 0 ? gifService.getNegativeGif()
                : gifService.getEqualGif();
    }

    //    random selection of gifs from the general list
    public int getRandomGif(int size) {
        LOGGER.debug("Executing method getRandomGif(). Size is " + size);
        return new Random().nextInt(size);
    }

}





