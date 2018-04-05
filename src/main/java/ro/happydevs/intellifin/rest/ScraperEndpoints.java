package ro.happydevs.intellifin.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.happydevs.intellifin.scrapers.emag.EmagScraper;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/scrapers")
public class ScraperEndpoints {
    @RequestMapping(value = "/emag")
    public ResponseEntity<?> getLaptops(@RequestParam(value = "url") String url,
                                        @RequestParam(value = "category") int categoryId) {
        EmagScraper.scrapeMarket(url, categoryId);
        return ResponseEntity.ok("Job finished!");
    }
}
