import java.io.*;
import java.util.*;

public class Basket implements Serializable {

  public Map<Integer, Integer> totalPrice = new HashMap<>();

  public Basket(Map<Integer, Integer> totalPrice) {

    Basket.Goo = totalPrice;
  }

  static void loadFromBinFile(File file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);
      Basket.Goo = (HashMap) ois.readObject();
      ois.close();
      fis.close();
      setTotalPrice(Basket.Goo);
      Basket.setTotalPrice(Basket.Goo);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("Ошибка!!!");
      c.printStackTrace();
      return;
    }
  }

  public void addToCart(int productNum, int productCount) {

    if (Goo == null){
    Basket.Goo = new HashMap<>();
    getTotalPrice().putAll(Goo);
    }

    if (Goo.containsKey(productNum)) {

      Goo.put(productNum, Goo.get(productNum) + productCount);
    } else {

      Goo.put(productNum, productCount);
    }

  }

  public void saveBin(File file) throws IOException {
    FileOutputStream fos =
        new FileOutputStream(file);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(Basket.Goo);
    oos.close();
    fos.close();
    System.out.print("Данные сохранены.");
  }

  public void printCart(List<String> products, List<Integer> prices) {
    for (int i = 0; i < products.size(); i++) {
      if (Basket.Goo.get(i) != null) {
        int sumProducts = Basket.Goo.get(i) * prices.get(i);
        System.out.println(products.get(i) + " " + Basket.Goo.get(i) + "кг/шт  "
            + prices.get(i) + " руб. за кг/шт     всего на: " + sumProducts + "руб.");
      }

    }
  }

  public static void setTotalPrice(Map<Integer, Integer> totalPrice) {
    totalPrice.putAll(Goo);
  }

  public Map<Integer, Integer> getTotalPrice() {
    return totalPrice;
  }

  public static Map<Integer, Integer> Goo;{
    getTotalPrice();
  }
}



//if (Goo == null){
//    Basket.Goo = new HashMap<>();
//    getTotalPrice().putAll(Goo);
//    }

//import java.io.*;
//import java.util.Arrays;
//
//public class Basket implements Serializable {
//  protected int[] prices;
//  protected String[] products;
//  protected int total = 0;
//  protected int[] amountOfProducts;
//
//  protected Basket(int[] prices, String[] products) {
//    this.prices = prices;
//    this.products = products;
//    amountOfProducts = new int[products.length];
//  }
//
//  public static void loadFromBinFile(File file) throws IOException, ClassCastException {
//    try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
//      Basket save = (Basket) reader.readObject();
//      System.out.println(save);
//      save.printCart();
//    } catch (IOException io) {
//      throw new IOException(io);
//    } catch (ClassCastException | ClassNotFoundException cc) {
//      cc.printStackTrace();
//    }
//  }
//
//
////  public static void loadFromBinFile(File file) throws IOException, ClassCastException {
////    try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))){
////      FileInputStream fis = new FileInputStream(file);
////      ObjectInputStream ois = new ObjectInputStream(fis);
////      Basket.Goo = (HashMap) ois.readObject();
////      ois.close();
////      fis.close();
////      setTotalPrice(Basket.Goo);
////      Basket.setTotalPrice(Basket.Goo);
////    } catch (IOException ioe) {
////      ioe.printStackTrace();
////      return;
////    } catch (ClassNotFoundException c) {
////      System.out.println("Ошибка!!!");
////      c.printStackTrace();
////      return;
////    }
////  }
//
//
//
//
//
//
//
//
//  protected void addToCart(int productNum, int amount) {
//    if (amount != 0) {
//      amountOfProducts[productNum] += amount;
//    } else {
//      System.out.println("Товар: '" + products[productNum] + "' удален из корзины");
//      amountOfProducts[productNum] = 0;
//      prices[productNum] = 0;
//      total = 0;
//    }
//  }
//
//  protected void printCart() {
//    System.out.println("Ваша корзина:\n");
//    for (int i = 0; i < products.length; i++) {
//      if (amountOfProducts[i] != 0) {
//        System.out.printf("%s %d шт. %d руб./шт. %d руб. в сумме%n",
//            products[i], amountOfProducts[i], prices[i],
//            total += prices[i] * amountOfProducts[i]);
//      }
//    }
//    System.out.println("\nИтого:" + total + " руб.");
//  }
//
//  protected void saveBin(File file, Basket save) throws IOException {
//    try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
//      writer.writeObject(save);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  public int[] getAmountOfProducts() {
//    return amountOfProducts;
//  }
//
//  public int[] getPrices() {
//    return prices;
//  }
//
//  public String[] getProducts() {
//    return products;
//  }
//
////    public int getTotal() {
////        return total;
////    }
//}