import java.io.*;
import java.util.*;

public class Basket {

  HashMap<Integer,Integer> amountProduct = new HashMap<>();
  private final List<Integer> prices;
  private final List<String> products;

  public Basket(List<String> products, List<Integer> prices) {
    this.prices = prices;
    this.products = products;///////, Map<Integer, Integer> amountProduct
    amountProduct = amountProduct;
  //  this.amountProduct = new HashMap<>();
  }

  public void addToCart(int productNum, int quantity) {
    amountProduct.merge(productNum, quantity, Integer::sum);
  }

  public void printCart() {
    int All = 0;
    for (int i = 0; i < products.size(); i++) {
      if (amountProduct.get(i) != null) {
        System.out.println(products.get(i) + " " + amountProduct.get(i) + "кг/шт  "
            + prices.get(i) + " руб. за кг/шт     всего на: " + (amountProduct.get(i) * prices.get(
            i)) + "руб.");
        All += (amountProduct.get(i) * prices.get(i));
      }
    }
    System.out.println("Всего: " + All + " руб.");
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

  protected static String[]  loadFromTxtFile(File textFile) throws IOException {
    String line = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
      line = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String[] arrayLine = Objects.requireNonNull(line).split(" ");
//    for (int i = 0; i < arrayLine.length; i++) {
//      amountProduct.put(i, Integer.parseInt(arrayLine[i]));
//
//    }
    return arrayLine;
  }


}

