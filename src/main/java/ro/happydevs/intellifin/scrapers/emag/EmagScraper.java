package ro.happydevs.intellifin.scrapers.emag;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.business.Product;
import ro.happydevs.intellifin.models.business.ProductPrice;
import ro.happydevs.intellifin.repositories.ProductPriceRepository;
import ro.happydevs.intellifin.repositories.ProductRepository;

import java.util.ArrayList;

@Component
public class EmagScraper {

    @Autowired
    private static ProductRepository productRepository;
    @Autowired
    private static ProductPriceRepository productPriceRepository;

    public static void scrapeMarket(String url, Long categoryId) {
        System.out.println("Started the scrape");
        try {
            Document document = Jsoup.connect(url).get();
            Elements products = document.select(".product-title-zone");
            ArrayList<Product> productList = new ArrayList<Product>();

            for (Element product : products) {
                if (!product.text().isEmpty()) {
                    System.out.println(product.text());
                    if (productRepository.getProductsForName(product.text()) != null) {
                        System.out.println("Creating " + product.text());
                        Product productSingle = new Product();
                        productSingle.setName(product.text());
                        productSingle.setCategory(categoryId);
                        productList.add(productSingle);
                        productRepository.save(productSingle);
                    }

                }

            }

            Elements prices = document.select(".product-new-price");
            int index = 0;
            if (productList.size() > 0) {
                System.out.println(productList.get(index));
                for (Element price : prices) {
                    if (!price.text().isEmpty()) {
                        String firstPart = price.text();

                        StringBuilder b = new StringBuilder(firstPart);
                        b.replace(firstPart.lastIndexOf(price.select("sup").text()), firstPart.lastIndexOf(price.select("sup").text()) + 2, "");
                        firstPart = b.toString();
                        System.out.println(firstPart.trim().replace("Lei", "").trim() + "." + price.select("sup").text().trim());
                        try {
                            ProductPrice productPrice = new ProductPrice();
                            productPrice.setPrice(Double.parseDouble(firstPart.trim().replace("Lei", "").trim() + "." + price.select("sup").text().trim()));
                            productPrice.setProductId((productRepository.getProductsForName(productList.get(index).getName()).get(0)).getId());
                            productPrice.setShopId(1L); //emag
                            productPriceRepository.save(productPrice);
                        } catch (Exception e) {
                            //Ignore since the products already exists
                        }
                        index++;


                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
