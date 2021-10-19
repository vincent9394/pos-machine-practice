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
        System.out.println(countItemQuantity(barcodesItem.get(0).getBarcode()));

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
    public static int countItemQuantity(String s){
        int count = 0;
        for(int i=0;i<barcodes.size();i++){
            if(s.equals(barcodes.get(i))){
                count++;
            }
        }
        return count;
    }

}
