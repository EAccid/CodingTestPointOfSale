import java.math.BigDecimal;

public interface PointOfSale {
    void setPricing(String productCode, double price, int amount);
    void scan(String productCode);
    BigDecimal calculateTotal();
    }
