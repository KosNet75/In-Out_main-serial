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
      Goo = (HashMap) ois.readObject();
      ois.close();
      fis.close();
      setTotalPrice(Goo);
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
    if (totalPrice.containsKey(productNum)) {
      totalPrice.put(productNum, totalPrice.get(productNum) + productCount);
    } else {
      totalPrice.put(productNum, productCount);
    }

  }

  public void saveBin(File file) throws IOException {
    FileOutputStream fos =
        new FileOutputStream(file);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(totalPrice);
    oos.close();
    fos.close();
    System.out.print("Данные сохранены.");
  }

  public void printCart(List<String> products, List<Integer> prices) {
    for (int i = 0; i < products.size(); i++) {
      if (getTotalPrice().get(i) != null) {
        int sumProducts = getTotalPrice().get(i) * prices.get(i);
        System.out.println(products.get(i) + " ---- " + getTotalPrice().get(i) + "кг/шт  "
            + prices.get(i) + " руб. за кг/шт     всего на: " + sumProducts + "руб.");
      }

    }
  }
  //setTotalPrice(totalPrice).putAll(Goo);

    public static void setTotalPrice(Map<Integer, Integer> totalPrice) {
      totalPrice.putAll(Goo);
  }

  public Map<Integer, Integer> getTotalPrice() {
    return totalPrice;
  }


  public static Map<Integer, Integer> Goo; {
    getTotalPrice();
  }


}

















//  static void loadFromBinFile(File file) {
//    try {
//      FileInputStream fis = new FileInputStream(file);
//      ObjectInputStream ois = new ObjectInputStream(fis);
////      totalPrice = (HashMap) ois.readObject();
//
//      Basket basket =new Basket();
//
//      basket.totalPrice = (HashMap) ois.readObject();
//
//      ois.close();
//      fis.close();
//    } catch (IOException ioe) {
//      ioe.printStackTrace();
//      return;
//    } catch (ClassNotFoundException c) {
//      System.out.println("Ошибка!!!");
//      c.printStackTrace();
//      return;
//    }
//  }









//import java.io.*;
//    import java.util.*;
//
//public class Basket implements Serializable {
//
//  private static List<String> products;
//  private static List<Integer> prices;
//  private static Map<Integer, Integer> totalPrice = new HashMap<>();
//
//  public Basket(List<String> products, List<Integer> prices) {
//    this.products = products;
//    this.prices = prices;
//  }
//
//  public Basket() {
//
//  }
//
//  public void addToCart(int productNum, int amount) {
//    if (totalPrice.containsKey(productNum)) {
//      totalPrice.put(productNum, totalPrice.get(productNum) + amount);
//    } else {
//      totalPrice.put(productNum, amount);
//    }
//
//  }
//
//  public void printCart() {
//    for (int i = 0; i < getProducts().size(); i++) {
//      // Проверка на введенные продукты и печатаем только которые купили
//      if (getTotalPrice().get(i) != null) {
//        System.out.println(getProducts().get(i) + ": "
//            + (getTotalPrice().get(i))
//            + " шт " + getPrices().get(i) + " руб/шт "
//            + getTotalPrice().get(i) * getPrices().get(i) + " руб в сумме");
//      }
//    }
//  }
//
//
//  public void saveBin(File file) throws IOException {
//
//    Basket saveBin = new Basket();
//    try (FileOutputStream os = new FileOutputStream(file);
//        ObjectOutputStream oos = new ObjectOutputStream(os)) {
//
//      oos.writeObject(saveBin);
//
//    } catch (Exception ex) {
//      System.out.println("ошибка");
//    }
//    System.out.println("Данные сохранены");
//    System.out.println(saveBin);
//  }
//
//  static void loadFromBinFile(File file) {
//    Basket basket = new Basket();
//    try (FileInputStream fis = new FileInputStream(file);
//        ObjectInputStream ois = new ObjectInputStream(fis)) {
//      basket = (Basket) ois.readObject();
//    } catch (Exception ex) {
//      System.out.println(ex.getMessage());
//    }
//    System.out.println(basket);
//  }
//
//  public List<String> getProducts() {
//    return products;
//  }
//
//  public void setProducts(List<String> products) {
//    this.products = products;
//  }
//
//  public List<Integer> getPrices() {
//    return prices;
//  }
//
//
//
//  public Map<Integer, Integer> getTotalPrice() {
//    return totalPrice;
//  }
//
//
//  @Override
//  public String toString() {
//    return "Baske{" +
//        "products=" + products +
//        ", prices=" + prices +
//        ", totalPrice=" + totalPrice +
//        '}';
//  }

//}