import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) throws IOException {
    String inputNum = "";
    int sumProducts = 0;
    int sum = 0;
    List<String> products = List.of("Хлеб", "Мясо", "Молоко", "Крупа", "Соль");
    List<Integer> prices = List.of(35, 250, 80, 40, 30);

    Basket basket = new Basket(Basket.Goo);

    Scanner scanner = new Scanner(System.in);

    System.out.println("____________________");
    System.out.println("В наличии продукты:");
    System.out.println("____________________");

    for (int i = 0; i < products.size(); i++) {
      System.out.println(
          (i + 1) + "." + products.get(i) + " " + prices.get(i)
              + "кг/шт руб");
    }

    File f = new File("basket.bin");
    if (f.isFile()) {

      Basket.loadFromBinFile(new File("basket.bin"));

      System.out.println("\nКорзина загружена.");

      //System.out.println("Goo = " + Basket.Goo);
      //System.out.println(Basket.totalPrice(Basket.Goo));

      basket.printCart(products, prices);


    } else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
    }

    while (true) {
      System.out.println("Введите `end`для завершения");

      try {
        System.out.print("\nВведите позицию покупаемого товара [1-5]: > ");

        inputNum = scanner.nextLine();

        if (Integer.parseInt(inputNum) > products.size() || Integer.parseInt(inputNum) < 1) {
          System.out.println("Нет такого номера товара!");
          continue;
        }

        System.out.print("Введите количество покупаемого: > ");

        String inputLot = scanner.nextLine();
        int productNumber = Integer.parseInt(inputNum) - 1;
        int productCount = Integer.parseInt(inputLot);

        basket.addToCart(productNumber, productCount);
        basket.saveBin(new File("basket.bin"));
        basket.printCart(products, prices);


      } catch (NumberFormatException e) {
        if ("end".equals(inputNum)) {
          basket.saveBin(new File("basket.bin"));
          Basket.loadFromBinFile(new File("basket.bin"));
          Basket.setTotalPrice(Basket.Goo);
          System.out.println("КОРЗИНА:");
          for (int i = 0; i < products.size(); i++) {
            if (Basket.Goo.get(i) != null) {
              sumProducts = Basket.Goo.get(i) * prices.get(i);
              sum = sum + sumProducts;
              System.out.println(
                  products.get(i) + "  [" + Basket.Goo.get(i) + "кг/шт]  "
                      + prices.get(i) + " руб. за кг/шт     всего на: " + sumProducts + "руб.");

            }
          }
          System.out.println("Итого за всё: " + sum + " руб.");
          break;
        }
        System.out.println("Ошибка! Нужно вводить только числа!");

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }
}
