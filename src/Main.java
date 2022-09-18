import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    final int minNumOfLot = 1;
    List<String> product = new ArrayList<>(
        List.of(new String[]{"Яйцо", "Соль", "Хлеб", "Крупа", "Мясо"}));
    String[] productSale = {"Нет акции", "Соль", "Нет акции", "Крупа", "Нет акции"};
    List<Integer> productCost = new ArrayList<>(List.of(new Integer[]{80, 30, 35, 70, 150}));

    int[] costAll = new int[productCost.size()];
    int[] writeProd = new int[product.size()];
    int[] tempAll = new int[product.size()];

    System.out.println("____________________");
    System.out.println("В наличии продукты:");

    for (int i = 0; i < product.size(); i++) {
      System.out.println(
          (i + 1) + ": " + product.get(i) + ": " + productCost.get(i) + " руб/кг(шт).");

    }
    System.out.print("по Акции (3 кг/шт по цене 2 кг/шт):  ");
    for (String s : productSale) {
      if ("Нет акции".equals(s)) {
        continue;
      }
      System.out.print("[" + s + "]");
    }
    System.out.println("\nПосмотреть Корзину 'basket'\n" + "Выход и чек 'end'");

    int num;
    int prodCount;

    File f = new File("basket.txt");
    if (f.isFile()) {
      Basket.loadFromTxtFile(tempAll);
      for (int j = 0; j < costAll.length; j++) {

        costAll[j] = tempAll[j] + costAll[j];

      }

    } else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
    }

    while (true) {

      try {
        System.out.print("\nВведите позицию покупаемого товара [1-5]: > ");
        String inputNum = scanner.nextLine();

        if ("end".equals(inputNum)) {
          break;
        }

        if ("basket".equals(inputNum)) {
          Basket.printCart(product, productSale, costAll, productCost);
          continue;
        }

        if (Integer.parseInt(inputNum) < minNumOfLot
            || Integer.parseInt(inputNum) > product.size()) {
          System.out.println(new Basket.FalsePositionException());
          continue;
        }

        System.out.print("Введите количество покупаемого: > ");

        String inputLot = scanner.nextLine();

        if ("basket".equals(inputLot)) {
          Basket.printCart(product, productSale, costAll, productCost);
          continue;
        }

        if ("end".equals(inputLot)) {
          break;
        }

        prodCount = Integer.parseInt(inputLot);
        num = Integer.parseInt(inputNum) - 1;

        Basket.addToCart(tempAll, prodCount, num, costAll, writeProd, productCost,
            List.of(productSale), product);

      } catch (NumberFormatException e) {
        System.out.println("Ошибка! Нужно вводить только числа!");

      }

    }
    Basket.printCart(product, productSale, costAll, productCost);

    scanner.close();
  }
}