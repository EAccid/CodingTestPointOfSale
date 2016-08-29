import java.math.BigDecimal;
import java.util.HashMap;

public class PointOfSaleTerminal implements PointOfSale {

    private Scan scan = new Scan();
    private CostCalculation costCalculation = new CostCalculation();

    @Override
    public void setPricing(String productCode, double price, int amount) {
        costCalculation.setPrice(productCode, amount, price);
    }

    @Override
    public void scan(String productCode) {
        scan.addScan(productCode);
    }

    @Override
    public BigDecimal calculateTotal() {

        BigDecimal result = new BigDecimal(0);
        HashMap<String, Integer> scannedProducts = scan.getScannedProducts();
        for (String productCode : scannedProducts.keySet()
                ) {
            result = result.add(
                    costCalculation.calculateTotalProductSum(productCode, scannedProducts.get(productCode))
            );
        }
        return result;
    }

    public void clearAll() {
        scan.clearScannedProducts();
    }
}

