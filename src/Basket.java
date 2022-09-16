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

  public void printCart() {
    for (int i = 0; i < getTotalPrice().size(); i++) {
      if (getTotalPrice().get(i) != null) {
        System.out.println(getTotalPrice().get(i) + ": "
            + (getTotalPrice().get(i))
            + " шт " + getTotalPrice().get(i) + " руб/шт "
            + getTotalPrice().get(i) * getTotalPrice().get(i) + " руб в сумме");

      }
    }
  }

  public static Map<Integer, Integer> getTotalPrice() {
    return totalPrice;
  }


  @Override
  public String toString() {

    return " HashMap = [" + totalPrice +
        ']';
  }

}
