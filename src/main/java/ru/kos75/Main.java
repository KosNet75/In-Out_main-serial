package ru.kos75;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {



  public static void main(String[] args) throws IOException {
    String inputNum;
    HashMap<Integer, Integer> amountProduct = new HashMap<>();
    List<String> products = List.of("Хлеб", "Мясо", "Молоко", "Крупа", "Соль");
    List<Integer> prices = List.of(35, 250, 80, 40, 30);
    Basket basket = new Basket(products, prices, amountProduct);
    ClientLog clientLog = new ClientLog();
    Scanner scanner = new Scanner(System.in);

    System.out.println("____________________\n" + "В наличии продукты:" + "\n____________________");

    for (int i = 0; i < products.size(); i++) {
      System.out.println(
          (i + 1) + ". " + products.get(i) + " " + prices.get(i) + " кг/шт руб");
    }


    File log = new File("Log.csv");
    //File f = new File("basket.txt");
      File f = new File("basket.json");
    if (f.isFile()) {
   //   basket = Basket.loadFromTxtFile(f);
      basket.loadFromJSonFile(f);
      System.out.println("\nКорзина загружена.");
    } else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
    //  f = new File("basket.txt");
        f = new File("basket.json");
    }
//
    while (true) {
      System.out.println("Введите 'end' для завершения");

      try {
        System.out.print("\nВведите позицию покупаемого товара [1-5]: > ");

        inputNum = scanner.nextLine();
        if ("end".equals(inputNum)) {
          break;
        }

        if (Integer.parseInt(inputNum) > products.size() || Integer.parseInt(inputNum) < 1) {
          System.out.println("Нет такого номера товара!");
          continue;
        }

        System.out.print("Введите количество покупаемого: > ");

        String inputLot = scanner.nextLine();
        if ("end".equals(inputLot)) {
          break;
        }

        int productNumber = Integer.parseInt(inputNum) - 1;
        int quantity = Integer.parseInt(inputLot);

        basket.addToCart(productNumber, quantity);
        clientLog.log(productNumber,quantity);
        basket.printCart();
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }

   // basket.saveTxt(f);
      basket.saveJSon(f);
    clientLog.exportAsCSV(new File(String.valueOf(log)));
    System.out.println("\nКОРЗИНА:");
    basket.printCart();
    scanner.close();
  }

}




