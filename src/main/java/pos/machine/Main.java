package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static List<String> barcodes = ItemDataLoader.loadBarcodes();
    public static List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();
    public static List<ItemInfo> barcodesItem = new ArrayList<>();
    public static void main(String[] arg) {

            printItem();
        System.out.println(countItemQuantity(barcodesItem.get(0).getBarcode()));//4
        System.out.println(countSubTotal(barcodes.get(0)));

    }
    public static void printItem(){

        for (int i = 0; i < barcodes.size(); i++) {
            for (int j = 0; j < itemInfos.size(); j++) {
                if (barcodes.get(i).equals(itemInfos.get(j).getBarcode())) {
                    barcodesItem.add(itemInfos.get(j));
                }
            }
//            System.out.println(barcodesItem.get(i).getBarcode());
//
//            System.out.println(barcodesItem.get(i).getName());
//            System.out.println(barcodesItem.get(i).getPrice());
        }

    }
    public static int countItemQuantity(String barcode){
        int count = 0;
        for(int i=0;i<barcodes.size();i++){
            if(barcode.equals(barcodes.get(i))){
                count++;
            }
        }
        return count;
    }

    public static int findBarcodeIndex(String barcode){
        int index = 0;
        for(int i=0;i<barcodesItem.size();i++){
            if(barcode.equals(barcodesItem.get(i).getBarcode())){
                index = i;
            }
        }
        return index;
    }

    public static int countSubTotal(String barcode){
        int count = countItemQuantity(barcode);
        int index = findBarcodeIndex(barcode);
        int subTotal = barcodesItem.get(index).getPrice()*count;
        return subTotal;
    }
}
