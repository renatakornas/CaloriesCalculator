/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.renatakornas;

import com.thoughtworks.xstream.XStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rkornas
 */
public class ProductRepository {

    static File file = new File("data.xml");
    static private HashMap<String, Product> products = new HashMap<>(); //HashMap<klasa klucza,klasa obiektu>

    static private ProductRepository INSTANCE; // (1 cecha singletonu) prywatne statyczne pole reprezentujące jedyną instancję mojej bazy 

    private ProductRepository() {
    }              // (2 cecha singletonu) pusty konstruktor 

    public static ProductRepository getInstance() { // (3 cecha singletonu) publiczna statyczna metoda zwracająca jedyną instancje klasy
        if (INSTANCE == null) {
            synchronized (ProductRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductRepository();
                }
            }
        }
        return INSTANCE;
    }

    /////////////////////////// POBIERANIE PRODUKTU ///////////////////////////
    static Product getProduct(String productName) {
        try {
            loadData();
            // (FileNotFoundException | EOFException ex) można wyłapać kilka wyjątków w jednej instrukcji catch, pod warunkiem, że te klasy nie są swoimi podklasami, albo nadklasami
        } catch (IOException ex) {
            System.out.println("Błąd pliku");
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Klasy nie znaleziono");
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (products.containsKey(productName)) {
            Product product = products.get(productName); //hashmap.get(klucz) - pobiera obiekt odp kluczowi w hashmapie 
            return product;
        } else {
            Product product = new Product(productName, 0);
            return product;
        }
    }

/////////////////////////// LADOWANIE BAZY ///////////////////////////
    private static void loadData() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        products.clear();  // 
        //zaczytanie xmla z produktami
        XStream xstream = new XStream();                                                // obiekt klasy do obslugi xml
        xstream.alias("product", pl.renatakornas.Product.class);                        // alias dla prawidlowego odczytu

        if (file.exists()) {
            FileInputStream inputFile = new FileInputStream(file);                      //strumien„ wejs›ciowy pliku
            ObjectInputStream inputObject = xstream.createObjectInputStream(inputFile); //strumien„ wejs›ciowy obiektu
            try {
                for (;;) {     // wczytuje wszystkie obiekty z xmla
                    Product p = (Product) inputObject.readObject(); // odczytuje objekt Product 
                    products.put(p.getName(), p);                   // wrzuca do hashmapy 
                }
            } catch (EOFException e) {
            }
        } else {
            throw new Exception("UWAGA. Brak pliku " + file.getPath());
        }

    }
/////////////////////////// DODAWANIE PRODUKTOW ///////////////////////////

    public static void setProduct(Product product) throws FileNotFoundException, IOException, Exception {
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        products.put(product.getName(), product);

        XStream xstream = new XStream();
        xstream.alias("product", pl.renatakornas.Product.class);

        if (file.exists()) {
            FileOutputStream outputFile = new FileOutputStream(file);
            ObjectOutputStream outputObject = xstream.createObjectOutputStream(outputFile);

            products.forEach((String k, Product v) -> {
                try {
                    outputObject.writeObject(v);
                } catch (IOException ex) {
                }
            });
            outputObject.close();

        } else {
            throw new Exception("UWAGA. Brak pliku o podanej nazwie.");
        }
    }
}
