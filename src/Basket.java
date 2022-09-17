
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
