import java.io.*;
import java.util.*;

public class Basket implements Serializable {

  private static Map<Integer, Integer> totalPrice = new HashMap<>();

  public Basket(Map<Integer, Integer> totalPrice) {

    Basket.totalPrice = totalPrice;
  }


  static void loadFromBinFile(File file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);
      totalPrice = (HashMap) ois.readObject();
      ois.close();
      fis.close();
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

  public void printCart(List<String> products,List<Integer> prices) {
    for (int i = 0; i < products.size(); i++) {
      if (Basket.getTotalPrice().get(i) != null) {
        int sumProducts = Basket.getTotalPrice().get(i) * prices.get(i);
        System.out.println(products.get(i) + "  " + Basket.getTotalPrice().get(i) + "кг/шт  "
            + prices.get(i) + " руб. за кг/шт     всего на: " + sumProducts + "руб.");
      }
    }
    }
 // }

  public static Map<Integer, Integer> getTotalPrice() {
    return totalPrice;
  }

}
