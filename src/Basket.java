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
