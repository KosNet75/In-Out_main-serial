import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) throws IOException {
    String inputNum = "";
    int sumProducts = 0;
    int sum = 0;
    List<String> products = List.of("Хлеб", "Мясо", "Молоко", "Крупа", "Соль");
    List<Integer> prices = List.of(35, 250, 80, 40, 30);


    Basket basket = new Basket(Basket.getTotalPrice());
   // Basket.loadFromBinFile(new File("basket.bin"));
    basket.printCart();

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

    } else {
      System.out.println("Файла Корзины не существует! Покупка начнется с '0'!");
    }



    while (true) {

      System.out.println("Введите `end`для завершения");



      try {
        System.out.print("\nВведите позицию покупаемого товара [1-5]: > ");

        inputNum = scanner.nextLine();



      if ("111".equals(inputNum)) {
        Basket.loadFromBinFile(new File("basket.bin"));
         System.out.println("Корзина загружена");
      }

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

      } catch (NumberFormatException e) {
        if ("end".equals(inputNum)) {

          for (int i = 0; i < products.size(); i++) {
          if (Basket.getTotalPrice().get(i) != null) {
              sumProducts = Basket.getTotalPrice().get(i) * prices.get(i);
            sum = sum + sumProducts;
            System.out.println(products.get(i) + ". " + Basket.getTotalPrice().get(i) + "  "
                      + prices.get(i) + "   " + sumProducts);


//            for (Map.Entry<Integer, Integer> pair : totalPrice.entrySet()) {
//              System.out.printf("Key (name) is: %s, Value (age) is : %s%n", pair.getKey(), pair.getValue());
//            }

            }


          }

          System.out.println("Итого: " + sum);

          basket.saveBin(new File("basket.bin"));
          break;
        }
        System.out.println("Ошибка! Нужно вводить только числа!");

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }
}





//
//
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//
//
//  public static void main(String[] args) throws IOException, ClassNotFoundException {
//// создание статического массива
//    List<String> products = List.of("Хлеб", "Яблоки", "Молоко");
//    List<Integer> prices = List.of(100, 200, 300);
//    int[] currentPrice = new int[3];
//    int[] countProduct = new int[3];
//
//
//    Basket baske = new Basket(products, prices);
//    System.out.println(baske);
//
//
//// переменные
//    int sumProducts = 0;
//// создаем сканер
//
//    Scanner scan = new Scanner(System.in);
//// Первое отображение списка покупок
//    System.out.println("Список возможных товаров для покупки");
//    for (int i = 0; i < baske.getProducts().size(); i++) {
//      System.out.println((i + 1) + "." + baske.getProducts().get(i) + " " + baske.getPrices().get(i) + "р/шт");
//    }
//// бесконечный цикл
//    while (true) {
//
//      System.out.println("Выберите товар и количество или введите `end`для завершения или " +
//          "'load' для загрузки файлов");
//      String input = scan.nextLine();
//
//      if ("end".equals(input)) {
//        // Общая сумма
//
//
//        for (int i = 0; i < baske.getTotalPrice().size(); i++) {
//          if (baske.getTotalPrice().get(i) != null){
//            sumProducts += baske.getTotalPrice().get(i)*baske.getPrices().get(i);
//          }
//        }
//        System.out.println(baske);
//
//        // Вывод на печать
//        baske.printCart();
//        System.out.println("Итого: " + sumProducts + " руб");
//        break;
//
//
//      }
//      if ("load".equals(input)) {
//        Basket.loadFromBinFile(new File("basket.bin"));
//        System.out.println("Успешно загружены ");
//        continue;
//      }
//      String[] pars = input.split(" ");
//      int productNumber = Integer.parseInt(pars[0]) - 1;
//      int productCount = Integer.parseInt(pars[1]);
//
////Стоимость продукта
//      baske.addToCart(productNumber, productCount);
//
//      baske.saveBin(new File("basket.bin"));
//    }
//
//
//  }
//
//
//
//
//}

