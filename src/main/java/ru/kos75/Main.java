package ru.kos75;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class Main {



  public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {


    Node tempList = null;


    try {
      // Создается построитель документа
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      // Создается дерево DOM документа из файла
      Document document = documentBuilder.parse("shop.xml");
      // Получаем корневой элемент
      Node root = document.getDocumentElement();
      // Просматриваем все подэлементы корневого - т.е. книги
      NodeList list = root.getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
        Node temp = list.item(i);
        // Если нода не текст, то это книга - заходим внутрь
        if (temp.getNodeType() != Node.TEXT_NODE) {
          NodeList listTemp = temp.getChildNodes();
          for (int j = 0; j < listTemp.getLength(); j++) {
            tempList = listTemp.item(j);
            // Если нода не текст, то это один из параметров книги - печатаем
            if (tempList.getNodeType() != Node.TEXT_NODE) {
              System.out.println(tempList.getNodeName() + ":" + tempList.getChildNodes().item(0).getTextContent());
              System.out.println("111111 " + tempList.getNodeName());
            }
          }
          System.out.println("===========>>>>");
        }
      }

    } catch (ParserConfigurationException ex) {
      ex.printStackTrace(System.out);
    } catch (SAXException ex) {
      ex.printStackTrace(System.out);
    } catch (IOException ex) {
      ex.printStackTrace(System.out);
    }
    // }
//}








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
        clientLog.log(productNumber, quantity);
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




