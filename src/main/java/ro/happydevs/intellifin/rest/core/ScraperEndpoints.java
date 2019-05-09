package ro.happydevs.intellifin.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.scrapers.emag.EmagScraper;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/scrapers")
public class ScraperEndpoints {
    @Autowired
    EmagScraper emagScraper;

    @RequestMapping(value = "/emag", method = RequestMethod.GET)
    public ResponseEntity<?> getLaptops(@RequestParam(value = "url") String url,
                                        @RequestParam(value = "category") Long categoryId) {
        EmagScraper.scrapeMarket(url, categoryId);
        return ResponseEntity.ok("Job finished!");
    }
}
