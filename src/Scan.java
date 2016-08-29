import java.util.HashMap;

public class Scan {

//    private final int DEFAULT_PRODUCT_AMOUNT = 1;
    private HashMap<String, Integer> productsMap = new HashMap<>();

    public void addScan(String productCode) {

        if (productCode.isEmpty()){return;}

        int amountOfScanedProducts =  productsMap.get(productCode) == null ?
                0 : productsMap.get(productCode);
        productsMap.put(productCode, amountOfScanedProducts + 1);
   }

    public HashMap<String, Integer> getScanedProducts(){
        return  productsMap;
    }

    public void clearScanedProducts(){
        productsMap.clear();
    }

}
