//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.Scanner;
//
//
//public class Basket {
//
//
//  public static void addToCart(int[] tempAll, int prodCount, int num, int[] costAll,
//      int[] writeProd,
//      List<Integer> productCost, List<String> productSale, List<String> product)
//      throws IOException {
//
//    writeProd[num] += prodCount;
//    costAll[num] = tempAll[num] + productCost.get(num) * writeProd[num];
//
//    if (productSale.get(num).equals(product.get(num)) & writeProd[num] >= 3) {
//      costAll[num] =
//          tempAll[num] + (writeProd[num] - (writeProd[num] / 3)) * productCost.get(num);  // Акция
//    }
//
//    saveTxt(productCost, costAll);
//
//  }
//
//
//  private static void saveTxt(List<Integer> productCost, int[] costAll)
//      throws IOException {
//    int i;
//    PrintWriter writer = new PrintWriter(new FileWriter("basket.txt"));
//    for (i = 0; i < productCost.size(); i++) {
//      String b = costAll[i] + "\n";
//      writer.append(b);
//    }
//    writer.close();
//  }
//
//
//  protected static void loadFromTxtFile(int[] tempAll) throws FileNotFoundException {
//    try (Scanner scanner = new Scanner(new File("basket.txt"))) {
//      int temp = 0;
//      while (scanner.hasNextLine()) {
//        String line = scanner.nextLine();
//        tempAll[temp] = Integer.parseInt(line);
//        temp++;
//
//      }
//
//    }
//
//  }
//
//  public static void printCart(List<String> product, String[] productSale,
//      int[] costAll, List<Integer> productCost) {
//
//    System.out.println("\n______________________________________________________");
//    System.out.println("КОРЗИНА :");
//
//    for (int i = 0; i < product.size(); i++) {
//      System.out.println(
//          (i + 1) + ": " + product.get(i) + ": " + productCost.get(i) + " руб/кг(шт)." +
//              "в корзине " + costAll[i] / productCost.get(i) + "кг/шт [по акции (" + productSale[i]
//              + ")] на " + costAll[i] + " руб");
//
//    }
//    int sumCost = 0;
//
//    for (int i = 0; i < product.size(); i++) {
//
//      sumCost += costAll[i];
//    }
//    System.out.println("______________________________________________________");
//    System.out.println("Общая сумма покупок за все время составляет: " + sumCost + " руб.");
//  }
//
//  public static class FalsePositionException extends RuntimeException {
//
//    public FalsePositionException() {
//      super("Ошибка ввода величины позиции [1-5]");
//    }
//  }
//}



import java.io.*;
import java.util.*;

public class Basket implements Serializable {

  private static final long SerialVersionUID = 1L;
  private final Map<Integer, Integer> amountProduct;

  private final List<Integer> prices;
  private final List<String> products;

  public Basket(List<String> products, List<Integer> prices, Map<Integer, Integer> amountProduct) {
    this.prices = prices;
    this.products = products;
    this.amountProduct = amountProduct;
  }

  public void addToCart(int productNum, int quantity) {
    amountProduct.merge(productNum, quantity, Integer::sum);

  }

  public void printCart() {
    int All = 0;
    for (int i = 0; i < products.size(); i++) {
      if (amountProduct.get(i) != null) {
        System.out.println(products.get(i) + " " + amountProduct.get(i) + "кг/шт  "
            + prices.get(i) + " руб. за кг/шт     всего на: " + (amountProduct.get(i) * prices.get(i)) + "руб.");
        All +=  (amountProduct.get(i) * prices.get(i));
      }
    }
    System.out.println("Всего: " + All + " руб.");
  }

  protected void saveBin(File file) throws IOException {
    try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
      writer.writeObject(this);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static Basket loadFromBinFile(File file) throws IOException, ClassCastException {
    Basket load = null;
    try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
      load = (Basket) reader.readObject();
      load.printCart();
    } catch (IOException io) {
      throw new IOException(io);
    } catch (ClassCastException | ClassNotFoundException cc) {
      cc.printStackTrace();
    }
    return load;
  }

}
