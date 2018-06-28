package ro.happydevs.intellifin.scrapers.emag;

public class EmagScraper {

//    private static ProductRepository productRepository = new ProductRepository();
//    private static ProductPriceRepository productPriceRepository = new ProductPriceRepository();
//
//    public static void scrapeMarket(String url, int categoryId) {
//
//        try {
//            Document document = Jsoup.connect(url).get();
//            Elements products = document.select(".product-title-zone");
//            ArrayList<Product> productList = new ArrayList<Product>();
//
//            for (Element product : products) {
//                if (!product.text().isEmpty()) {
//                    if (productRepository.getAllByStringColumn("name", product.text()).size() == 0) {
//                        System.out.println("Creating " + product.text());
//                        Product productSingle = new Product();
//                        productSingle.setName(product.text());
//                        productSingle.setCategory(categoryId);
//                        productList.add(productSingle);
//                        productRepository.create(productSingle);
//                    }
//
//                }
//
//            }
//
//            Elements prices = document.select(".product-new-price");
//            int index = 0;
//            if (productList.size() > 0) {
//                for (Element price : prices) {
//                    if (!price.text().isEmpty()) {
//                        String firstPart = price.text();
//
//                        StringBuilder b = new StringBuilder(firstPart);
//                        b.replace(firstPart.lastIndexOf(price.select("sup").text()), firstPart.lastIndexOf(price.select("sup").text()) + 2, "");
//                        firstPart = b.toString();
//                        System.out.println(firstPart.trim().replace("Lei", "").trim() + "." + price.select("sup").text().trim());
//                        try {
//                            ProductPrice productPrice = new ProductPrice();
//                            productPrice.setPrice(Double.parseDouble(firstPart.trim().replace("Lei", "").trim() + "." + price.select("sup").text().trim()));
//                            productPrice.setProductId(((Product) productRepository.getAllByStringColumn("name", productList.get(index).getName()).get(0)).getId());
//                            productPrice.setShopId(1); //emag
//                            productPriceRepository.create(productPrice);
//                        } catch (Exception e) {
//                            //Ignore since the products already exists
//                        }
//                        index++;
//
//
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
