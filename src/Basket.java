import java.io.*;
import java.util.*;

public class Basket {


  public HashMap<Integer, Integer> amountProduct = new HashMap<>();
  public static List<Integer> prices;
  public static List<String> products;

  public Basket(List<String> products, List<Integer> prices,
      HashMap<Integer, Integer> amountProduct) {
    this.prices = prices;
    this.products = products;
    this.amountProduct = amountProduct;
  }

  public Basket() {

  }

  public void addToCart(int productNum, int quantity) throws IOException {
    System.out.println("\nПРОВЕРКА = " + amountProduct);
    amountProduct.merge(productNum, quantity, Integer::sum);

    System.out.println("\nПРОВЕРКА = " + amountProduct);

  }

  public void printCart() {
    int all = 0;
    for (int i = 0; i < products.size(); i++) {
      if (amountProduct.get(i) != null) {
        System.out.println(products.get(i) + " " + amountProduct.get(i) + "кг/шт  "
            + prices.get(i) + " руб. за кг/шт     всего на: " + (amountProduct.get(i) * prices.get(
            i)) + "руб.");
        all += (amountProduct.get(i) * prices.get(i));
      }
    }
    System.out.println("Всего: " + all + " руб.");
  }

  protected void saveTxt(File textFile) throws IOException {
    try (PrintWriter writer = new PrintWriter(textFile)) {
      for (int i = 0; i < products.size(); i++) {
        int set;
        if (amountProduct.get(i) == null) {
          set = 0;
        } else {
          set = amountProduct.get(i);
        }
        writer.print(set + " ");
      }
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  static HashMap<Integer, Integer> loadFromTxtFile(File textFile) throws IOException {
    Basket basket = new Basket();
    String line = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
      line = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] temp = Objects.requireNonNull(line).split(" ");

    for (int i = 0; i < temp.length; i++) {

      basket.amountProduct.put(i, (Integer.parseInt(temp[i])));

    }
    System.out.println("\n");
    basket.printCart();
    return basket.amountProduct;
  }

}

