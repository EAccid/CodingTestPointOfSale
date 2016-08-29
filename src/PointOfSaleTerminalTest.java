import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PointOfSaleTerminalTest extends PointOfSaleTerminal {

    public PointOfSaleTerminal pointOfSaleTerminal = new PointOfSaleTerminal();
    final int DEFAULT_PRODUCT_AMOUNT = 1;

    @Test
    public void testSetPricing() throws Exception {

        pointOfSaleTerminal.setPricing("A", 1.25, DEFAULT_PRODUCT_AMOUNT);
        pointOfSaleTerminal.setPricing("A", 3, 3);
        pointOfSaleTerminal.setPricing("B", 4.25, DEFAULT_PRODUCT_AMOUNT);
        pointOfSaleTerminal.setPricing("C", 0, DEFAULT_PRODUCT_AMOUNT);
        pointOfSaleTerminal.setPricing("C", 0, 6);
        pointOfSaleTerminal.setPricing("D", 0.75, 1);
    }

    @Test
    public void testScan() throws Exception {

        String[] listABCDABA = {"A", "B", "C", "D", "A", "B", "C"};
        for (String productName : listABCDABA) {
            pointOfSaleTerminal.scan(productName);
        }
    }

    @Test
    public void testCalculateTotal() throws Exception {

        BigDecimal bigDecimal = pointOfSaleTerminal.calculateTotal();
        System.out.println(bigDecimal);
        Assert.assertTrue("", bigDecimal.equals(new BigDecimal("1.25")));
        pointOfSaleTerminal.clearAll();

    }

}