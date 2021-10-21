package pos.machine;

import java.util.*;

public class Main {
    public static List<String> barcodes = ItemDataLoader.loadBarcodes();
    public static List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();
    public static List<ItemInfo> barcodesItem = new ArrayList<>();
    public static void main(String[] arg) {

        List<ReceiptItem> receiptItems1 = convertToItems(barcodes);
        for (ReceiptItem receiptItem : receiptItems1) {      //for (ReceiptItem receiptItem (i): receiptItems1   =3 distinct items)
            System.out.println(receiptItem);
            System.out.println(receiptItem.getName());
            System.out.println(receiptItem.getQuantity());
            System.out.println(receiptItem.getSubTotal());
            System.out.println(receiptItem.getUnitPrice());

        }

        for(int i =0; i<receiptItems1.size();i++) {    // for(int i =0; i<receiptItems1.size() = 3   ;i++) {
            System.out.println(receiptItems1.get(i));
            System.out.println(receiptItems1.get(i).getName());
            System.out.println(receiptItems1.get(i).getQuantity());
            System.out.println(receiptItems1.get(i).getSubTotal());
            System.out.println(receiptItems1.get(i).getUnitPrice());

        }

//        PosMachine posMachine = new PosMachine();
//        System.out.println(posMachine.printReceipt(ItemDataLoader.loadBarcodes()));
        //List<String> loadBarcodes = "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004"
       // List<String> barcodes1 = ItemDataLoader.loadBarcodes();
       // List<ReceiptItem> receiptItems1 = convertToItems(barcodes1);

//            printItem();
//        System.out.println(countItemQuantity(barcodesItem.get(0).getBarcode()));//4
//        System.out.println(countSubTotal(barcodes.get(0)));
//        findBarcodeOnce();
//        System.out.println(countTotal());

    }


    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems = convertToItems(barcodes); //get List<ReceiptItem> Return itemsWithDetail
//        int sum =sum(6,7);
        Receipt receipt = calculateReceipt(receiptItems);
        return spliceReceipt(receipt);
    }

    private static List<ReceiptItem> convertToItems(List<String> barcodes) {//List<String> loadBarcodes = "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004"
        LinkedList<ReceiptItem> itemsWithDetail = new LinkedList<ReceiptItem>(); //itemsWithDetail [a,b,c,d,e]
        List<ItemInfo> itemsInfo = loadAllItemsInfo(); //itemsInfo [a,b,c,d,e]

//        ItemInfo item1Info = new ItemInfo("ITEM000000", "Coca-Cola", 3);
//        ItemInfo item2Info = new ItemInfo("ITEM000001", "Sprite", 3);
//        ItemInfo item3Info = new ItemInfo("ITEM000004", "Battery", 2);
        //ItemInfo item4Info = new ItemInfo("ITEM000005", "Tea", 2);
        barcodes = new ArrayList<>(new LinkedHashSet<>(barcodes)); //distinct barcode
        for (String barcode : barcodes) {
            ReceiptItem receiptItemDetail = new ReceiptItem();
            for (ItemInfo itemInfoVal : itemsInfo) {
                if (barcode.equals(itemInfoVal.getBarcode())) {
                    receiptItemDetail.setName(itemInfoVal.getName());
                    receiptItemDetail.setUnitPrice(itemInfoVal.getPrice());
                    receiptItemDetail.setQuantity(Collections.frequency(ItemDataLoader.loadBarcodes(), barcode));
                }
            }
            itemsWithDetail.add(receiptItemDetail);
        }
        return itemsWithDetail;
    }

//    private int sum(int a, int b){
//        return "a+b";
//    }

    private Receipt calculateReceipt(List<ReceiptItem> itemsWithDetail) {
        Receipt receipt = new Receipt();
        receipt.setItemList(calculateItemsSubtotal(itemsWithDetail));  //calculateItemsSubtotal(itemsWithDetail)
        receipt.setTotalPrice(calculateTotalPrice(itemsWithDetail)); //24
        return receipt;
    }



    private static List<ItemInfo> loadAllItemsInfo(){
        return ItemDataLoader.loadAllItemInfos();
    }



    private List<ReceiptItem> calculateItemsSubtotal(List<ReceiptItem> itemsWithDetail) {
        for(ReceiptItem receiptItemValue : itemsWithDetail) {
            receiptItemValue.setSubTotal(receiptItemValue.getQuantity()* receiptItemValue.getUnitPrice());
        }
        return itemsWithDetail;
    }

    private int calculateTotalPrice(List<ReceiptItem> itemsWithDetail) {
        int totalPrice = 0;
        for (ReceiptItem receiptItemDetail : itemsWithDetail) {
            totalPrice += receiptItemDetail.getSubTotal();
        }
        return totalPrice;
    }



    private String spliceItemDetail(Receipt receipt) {
        String itemsDetail = "";
        for (ReceiptItem receiptItemValue : receipt.getItemList())
        {
            itemsDetail += "Name: "+ receiptItemValue.getName() +
                    ", Quantity: " + receiptItemValue.getQuantity() +
                    ", Unit price: " + receiptItemValue.getUnitPrice() + " (yuan)" +
                    ", Subtotal: " + receiptItemValue.getSubTotal() + " (yuan)\n";
        }
        return itemsDetail;
    }

    private String spliceReceipt(Receipt receipt) {
        String itemsDetail = spliceItemDetail(receipt);
        int totalPrice = receipt.getTotalPrice();

        return ("***<store earning no money>Receipt***\n" + itemsDetail + "----------------------\n" +
                "Total: " + totalPrice + " (yuan)\n" +
                "**********************");
    }




//    public static void printItem(){
//
//        for (int i = 0; i < barcodes.size(); i++) {
//            for (int j = 0; j < itemInfos.size(); j++) {
//                if (barcodes.get(i).equals(itemInfos.get(j).getBarcode())) {
//                    barcodesItem.add(itemInfos.get(j));
//                }
//            }
//            System.out.println(barcodesItem.get(i).getBarcode());
////
//            System.out.println(barcodesItem.get(i).getName());
////            System.out.println(barcodesItem.get(i).getPrice());
//        }
//
//    }
//    public static int countItemQuantity(String barcode){
//        int count = 0;
//        for(int i=0;i<barcodes.size();i++){
//            if(barcode.equals(barcodes.get(i))){
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public static int findBarcodeIndex(String barcode){
//        int index = 0;
//        for(int i=0;i<barcodesItem.size();i++){
//            if(barcode.equals(barcodesItem.get(i).getBarcode())){
//                index = i;
//            }
//        }
//        return index;
//    }
//
//    public static int countSubTotal(String barcode){
//        int count = countItemQuantity(barcode);
//        int index = findBarcodeIndex(barcode);
//        int subTotal = barcodesItem.get(index).getPrice()*count;
//        return subTotal;
//    }
//
//    public static int countTotal(){
////        List<String> existBarcodes
//        List<String> onlyBarcodes =new ArrayList<>();
//        onlyBarcodes = findBarcodeOnce();
//        int sumTotal = 0;
//        for (String onlyBarcode : onlyBarcodes) {
//            sumTotal += countSubTotal(onlyBarcode);
//        }
//        return sumTotal;
//    }
//
//    public  static List<String> findBarcodeOnce(){
//        List<String> sortedBarcodes = ItemDataLoader.loadBarcodes();
//        Collections.sort(sortedBarcodes);
//        List<String> onlyBarcodes =new ArrayList<>();
//        onlyBarcodes.add(sortedBarcodes.get(0));
//        for (int i = 0; i < barcodes.size()-1; i++) {
//            if(!sortedBarcodes.get(i).equals(sortedBarcodes.get(i+1))){
//                onlyBarcodes.add(sortedBarcodes.get(i+1));
//
//            }
//
//        }
//        System.out.println(onlyBarcodes);
//        return onlyBarcodes;
//    }
}
