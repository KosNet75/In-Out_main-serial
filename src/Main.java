import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) throws IOException {
    String inputNum = "";
    int sumProducts;
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

      basket.printCart(products, prices);


    } else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
      //oos.writeObject(totalPrice);

//      Basket.Goo.putAll(basket.getTotalPrice());
//      basket.getTotalPrice().putAll(Basket.Goo);

   //   basket.getTotalPrice().putAll(Basket.Goo);

      basket.saveBin(new File("basket.bin"));
  //    Basket.Goo.putAll(basket.getTotalPrice());

//      Basket.Goo.putAll(basket.getTotalPrice());
//      basket.getTotalPrice().putAll(Basket.Goo);
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
          System.out.println("\nКОРЗИНА:");
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

//import java.io.*;
//import java.util.*;
//
//public class Main {
//  public static void main(String[] args) throws IOException {
//    Scanner sc = new Scanner(System.in);
//    File file = new File("basket.bin");
//
//    String[] products = {"Хлеб", "Сыр", "Гречневая крупа", "Яблоки", "Помидоры", "Огурцы"};
//    int[] prices = {40, 250, 100, 80, 200, 120};
//
//    Basket basket = new Basket(prices, products);
//
//    System.out.println("Список доступных товаров:\n");
//    for (int i = 0; i < products.length; i++) {
//      System.out.println((i + 1) + "." + " " + products[i] + " " + prices[i] + " руб./шт.");
//    }
//
//    int prodNum;
//    int prodCount;
//
//    if (file.exists()) {
//      try {
//        Basket.loadFromBinFile(file);
//      } catch (IOException e) {
//        throw new IOException(e);
//      }
//    } else {
//      System.out.println("Ваша корзина пуста. Наполните корзину товарами.");
//      file = new File("basket.bin");
//    }
//
//    while (true) {
//      System.out.println("\nВыберите товар и кол-во, либо введите 'end' для завершения");
//      try {
//        String insert = sc.nextLine();
//        if ("end".equals(insert)) {
//          break;
//        }
//
//        String[] parts = insert.split(" ");
//
//        prodNum = Integer.parseInt(parts[0]) - 1;
//        prodCount = Integer.parseInt(parts[1]);
//
//      } catch (NumberFormatException e) {
//        System.out.println("Ошибка! Нужно вводить только числа!");
//        continue;
//      }
//      basket.addToCart(prodNum, prodCount);
//
//    }
//    basket.printCart();
//    Basket save = new Basket(prices, products);
//    basket.saveBin(file, save);
//
//    sc.close();
//  }
//}
