import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


public class Basket {


  public static void addToCart(int[] tempAll, int prodCount, int num, int[] costAll,
      int[] writeProd,
      List<Integer> productCost, List<String> productSale, List<String> product)
      throws IOException {

    writeProd[num] += prodCount;
    costAll[num] = tempAll[num] + productCost.get(num) * writeProd[num];

    if (productSale.get(num).equals(product.get(num)) & writeProd[num] >= 3) {
      costAll[num] =
          tempAll[num] + (writeProd[num] - (writeProd[num] / 3)) * productCost.get(num);  // Акция
    }

    saveTxt(productCost, costAll);

  }


  private static void saveTxt(List<Integer> productCost, int[] costAll)
      throws IOException {
    int i;
    PrintWriter writer = new PrintWriter(new FileWriter("basket.txt"));
    for (i = 0; i < productCost.size(); i++) {
      String b = costAll[i] + "\n";
      writer.append(b);
    }
    writer.close();
  }


  protected static void loadFromTxtFile(int[] tempAll) {
    try {
      File file = new File("basket.txt");
      Scanner scanner = new Scanner(file);
      int t = 0;
      while (scanner.hasNextLine()) {
        String d = scanner.nextLine();
        tempAll[t] = Integer.parseInt(d);
        t++;
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void printCart(List<String> product, String[] productSale,
      int[] costAll, List<Integer> productCost) {

    System.out.println("\n______________________________________________________");
    System.out.println("КОРЗИНА :");

    for (int i = 0; i < product.size(); i++) {
      System.out.println(
          (i + 1) + ": " + product.get(i) + ": " + productCost.get(i) + " руб/кг(шт)." +
              "в корзине " + costAll[i] / productCost.get(i) + "кг/шт [по акции (" + productSale[i]
              + ")] на " + costAll[i] + " руб");

    }
    int sumCost = 0;

    for (int i = 0; i < product.size(); i++) {

      sumCost += costAll[i];
    }
    System.out.println("______________________________________________________");
    System.out.println("Общая сумма покупок за все время составляет: " + sumCost + " руб.");
  }

  public static class FalsePositionException extends RuntimeException {

    public FalsePositionException(String inputNum) {
      super("Ошибка ввода величины позиции [1-5]");
    }
  }
}
