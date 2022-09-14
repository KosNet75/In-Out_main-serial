import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) {

    int sumProducts = 0;
    List<String> products = List.of("Хлеб", "Мясо", "Молоко", "Крупа", "Соль");
    List<Integer> prices = List.of(35, 250, 80, 40, 30);

    Basket basket = new Basket(products, prices);

    Scanner scanner = new Scanner(System.in);

    System.out.println("____________________");
    System.out.println("В наличии продукты:");
    System.out.println("____________________");
    for (int i = 0; i < basket.getProducts().size(); i++) {
      System.out.println(
          (i + 1) + "." + basket.getProducts().get(i) + " " + basket.getPrices().get(i) + "кг/шт руб");
    }


    File f = new File("basket.bin");
    if (f.isFile()) {
      Basket.loadFromBinFile(new File("basket.bin"));
      }else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
    }


    while (true) {

      System.out.println("Введите `end`для завершения");

      String inputNum = null;
      try {
        System.out.print("\nВведите позицию покупаемого товара [1-5]: > ");

        inputNum = scanner.nextLine();
        if (Integer.parseInt(inputNum) > products.size() || Integer.parseInt(inputNum) < 1) {
          System.out.println("Нет такого номера товара!");
          continue;
        }

        if ("end".equals(inputNum)) {

          sumProducts = getSum(basket, sumProducts);
        }

        System.out.print("Введите количество покупаемого: > ");

        String inputLot = scanner.nextLine();

        if ("end".equals(inputLot)) {

          sumProducts = getSum(basket, sumProducts);

          basket.printCart();
          System.out.println("Итого: " + sumProducts + " руб");
          break;

        }

        int productNumber = Integer.parseInt(inputNum);
        int productCount = Integer.parseInt(inputLot);

        basket.addToCart(productNumber, productCount);


      } catch (NumberFormatException e) {
        if ("end".equals(inputNum)) {

          sumProducts = getSum(basket, sumProducts);
          break;
        }
        System.out.println("Ошибка! Нужно вводить только числа!");

      }
    }
  }

  private static int getSum(Basket basket, int sumProducts) {
    for (int i = 0; i < basket.getTotalPrice().size(); i++) {
      if (basket.getTotalPrice().get(i) != null) {
        System.out.println(
            basket.getTotalPrice().get(i) + "     " + basket.getPrices().get(i));
        sumProducts += basket.getTotalPrice().get(i) * basket.getPrices().get(i);
        basket.saveBin(new File("basket.bin"));
        break;
      }
    }
    return sumProducts;
  }

}
