import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class PointOfSaleTerminalTest extends PointOfSaleTerminal {

    public PointOfSaleTerminal pointOfSaleTerminal = new PointOfSaleTerminal();
    final int DEFAULT_PRODUCT_AMOUNT = 1;

    @Before
    public void testSetPricing() throws Exception {

        pointOfSaleTerminal.setPricing("A", 1.25, DEFAULT_PRODUCT_AMOUNT);
        pointOfSaleTerminal.setPricing("A", 3, 3);
        pointOfSaleTerminal.setPricing("B", 4.25, DEFAULT_PRODUCT_AMOUNT);
        pointOfSaleTerminal.setPricing("C", 1, DEFAULT_PRODUCT_AMOUNT);
        pointOfSaleTerminal.setPricing("C", 5, 6);
        pointOfSaleTerminal.setPricing("D", 0.75, 1);
    }

    @Test
    public void testScan() throws Exception {

        String[] listABCDABA = {"A", "B", "C", "D", "A", "B", "A", "L"};
        for (String productName : listABCDABA) {
            pointOfSaleTerminal.scan(productName);
        }
    }

    @Test
    public void testCalculateTotal() throws Exception {

        //load data to calculate total sum
        testScan();

        BigDecimal bigDecimal = pointOfSaleTerminal.calculateTotal();
        System.out.println(bigDecimal);
        Assert.assertTrue("", bigDecimal.equals(new BigDecimal("13.25")));
        pointOfSaleTerminal.clearAll();

    }

    @After
    public void testClearScannedProducts() {
        pointOfSaleTerminal.clearAll();
    }

}