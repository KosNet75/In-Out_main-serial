//import java.util.Scanner;
//
//public class Main {
//
//  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//
//    String[] product = {"Молоко: ", "Соль: ", "Хлеб: ", "Сахар: ", "Напитки: "};
//
//    String[] productCost = {"80", "30", "35", "95", "70"};
//
//    System.out.println("____________________");
//    System.out.println("В наличии продукты:");
//    System.out.println("____________________");
//
//    for (int i = 0; i < product.length; i++) {
//      System.out.println((i + 1) + ": " + product[i] + productCost[i] + " руб/кг(шт).");
//    }
//    int[] costAll = new int[5];
//    int sumCost = 0;
//
//    while (true) {
//
//      System.out.print("Введите позицию покупаемого товара: > ");
//      String inputNum = scanner.nextLine();
//
//      if ("end".equals(inputNum)) {
//        System.out.println();
//        System.out.println("Ваш чек! Купленные продукты:");
//      }
//
//
//      System.out.print("Введите количество покупаемого: > ");
//      String inputLot = scanner.nextLine();
//
//      if ("end".equals(inputNum) || "end".equals(inputLot)) {
//        System.out.println();
//        System.out.println("Ваш чек! Купленные продукты:");
//
//        for (int i = 0; i <= 4; i++) {
//          int cost = costAll[i] / Integer.parseInt(productCost[i]);
//          if (costAll[i] == 0) {
//            continue;
//          } else {
//            System.out.println(
//                product[i] + " общая стоимость за " + cost + " кг/шт. [" + costAll[i] + "руб.]");
//          }
//        }
//
//        for (int temp : costAll) {
//          sumCost = sumCost + temp;
//        }
//
//        System.out.println("______________________________________________________");
//        System.out.println("Общая сумма покупки составляет: " + sumCost + " руб.");
//        break;
//      }
//      int num = Integer.parseInt(inputNum) - 1;
//      int price = Integer.parseInt(productCost[num]);
//
//      int total = costAll[num] + (Integer.parseInt(inputLot) * price);
//      costAll[num] = total;
//    }
//    scanner.close();
//  }
//
//}
//


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) throws IOException, ClassNotFoundException {
// создание статического массива
    List<String> products = List.of("Хлеб", "Мясо", "Молоко", "Крупа", "Соль");
    List<Integer> prices = List.of(35, 250, 80, 40, 30);

    Basket basket = new Basket(products, prices);
    //   System.out.println(basket);

// переменные
    int sumProducts = 0;
// создаем сканер

    Scanner scanner = new Scanner(System.in);
// Первое отображение списка покупок
    System.out.println("____________________");
    System.out.println("В наличии продукты:");
    System.out.println("____________________");
    for (int i = 0; i < basket.getProducts().size(); i++) {
      System.out.println(
          (i + 1) + "." + basket.getProducts().get(i) + " " + basket.getPrices().get(i) + "р/шт");
    }


    File f = new File("basket.bin");
    if (f.isFile()) {
      Basket.loadFromBinFile(new File("basket.bin"));
      }else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
    }




    // System.out.println(basket);

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

          // System.out.println(basket);

          // Вывод на печать
          basket.printCart();
          System.out.println("Итого: " + sumProducts + " руб");
          break;


        }

        int productNumber = Integer.parseInt(inputNum);
        int productCount = Integer.parseInt(inputLot);

//Стоимость продукта
        basket.addToCart(productNumber, productCount);

        basket.saveBin(new File("basket.bin"));
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
