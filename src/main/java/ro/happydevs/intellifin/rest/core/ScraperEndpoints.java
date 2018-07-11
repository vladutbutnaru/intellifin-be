package ro.happydevs.intellifin.rest.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/scrapers")
public class ScraperEndpoints {
    @RequestMapping(value = "/emag", method = RequestMethod.POST)
    public ResponseEntity<?> getLaptops(@RequestParam(value = "url") String url,
                                        @RequestParam(value = "category") int categoryId) {
//        EmagScraper.scrapeMarket(url, categoryId);
        return ResponseEntity.ok("Job finished!");
    }
}
