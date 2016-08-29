import java.math.BigDecimal;
import java.util.HashMap;

public class CostCalculation {

    private Prices prices = new Prices();

    public void setPrice(String productCode, int amount, double price) {
        prices.setPrice(productCode, amount, price);
    }

    public BigDecimal calculateTotalProductSum(String productCode, int amount) {

        if (prices.isProductPricesEmpty(productCode)){
            System.out.println("Product '"+ productCode +"': there aren't any prices");
            return new BigDecimal(0);
        }
        int DefaultProductAmount = 1;
        int amountForDiscount = prices.findAmountForDiscount(productCode, amount);

        double priceForNumber = prices.getPrice(productCode, amountForDiscount);
        double priceForDefaultAmount = prices.getPrice(productCode, DefaultProductAmount);

        int modAmount = amount % amountForDiscount;

        return new BigDecimal(
                priceForDefaultAmount * modAmount
                        + priceForNumber * ((amount - modAmount) / amountForDiscount)
        );
    }

}

class Prices {

    HashMap<String, HashMap<Integer, Double>> productPrices = new HashMap<>();

    void setPrice(String productCode, int amount, double price) {
        if (amount < 1) {
            System.out.println("Product '"+ productCode +"': amount must be greater than zero");
            return;
        }
        setPriceForNumberProducts(productCode, amount, price);
    }

    double getPrice(String productCode, int amount) {
        if (isProductPricesEmpty(productCode)) {
            return 0;
        }
        return getPriceForNumberProducts(productPrices.get(productCode), amount);

             }

    boolean isProductPricesEmpty(String productCode) {
        return productPrices.get(productCode) == null;
    }

    /**
     * return max available amount
     * */
    int findAmountForDiscount(String productCode, int amount) {
        if (isProductPricesEmpty(productCode)) {
            return 0;
        }

        int maxAmount = 1;
        for (int productAmount : productPrices.get(productCode).keySet()
                ) {
            if (productAmount >= maxAmount && productAmount <= amount) {
                maxAmount = productAmount;
            }
        } return maxAmount;
    }

    private void setPriceForNumberProducts(String productCode, int amount, double price) {

        if (isProductPricesEmpty(productCode)) {
            HashMap<Integer, Double> priceForNumberProduct = new HashMap<>();
            priceForNumberProduct.put(amount, price);
            productPrices.put(productCode, priceForNumberProduct);
        } else {
            productPrices.get(productCode).put(amount, price);
        }

    }

    private double getPriceForNumberProducts(HashMap<Integer, Double> pricesForAmount, int amount) {
        return (pricesForAmount.get(amount) == null) ?
                0 : pricesForAmount.get(amount);
    }

}