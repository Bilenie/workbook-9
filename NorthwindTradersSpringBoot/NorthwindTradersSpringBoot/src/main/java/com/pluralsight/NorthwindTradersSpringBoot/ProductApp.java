package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ProductApp implements CommandLineRunner {

    @Autowired
    @Qualifier("jdbcProductDao")
    private ProductDao productDao;//spring boot create an instance of the dao for me

    @Override
    public void run(String... args) throws Exception {


        System.out.println(" __        __   _                            _          _   _             _ ");
        System.out.println(" \\ \\      / /__| | ___ ___  _ __ ___   ___  | |_ ___   | |_ ___ _ __ ___ | |");
        System.out.println("  \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | __/ _ \\ '_ ` _ \\| |");
        System.out.println("   \\ V  V /  __/ | (_| (_) | | | | | |  __/ | || (_) | | ||  __/ | | | | | |");
        System.out.println("    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/   \\__\\___|_| |_| |_|_|");
        System.out.println();
        System.out.println("   ____          _            __ _ _ ");
        System.out.println("  /\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\");
        System.out.println(" ( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\");
        System.out.println("  \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )");
        System.out.println("   '  |____| .__|_| |_|_| |_|\\__, | / / / /");
        System.out.println("  =========|_|==============|___/=/_/_/_/");


        // We create a Scanner object so we can read user input from the console.
        Scanner myScanner = new Scanner(System.in);

// This is a "loop" that will keep showing the menu until the user chooses to exit.
        while (true) {
            // Print the menu options to the screen.
            System.out.println("\n=== Product Menu ===");
            System.out.println("1. List products");
            System.out.println("2. Add product");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Search product by name");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice as a String.
            String choice = myScanner.nextLine();

            // Use a "switch" to handle each possible choice.
            switch (choice) {

                case "1":
                    // The user chose option 1 → List all products.

                    // Call the DAO to get a list of all products.
                    List<Product> products = productDao.getAll();

                    // Print the films to the screen.
                    System.out.println("\nProducts:");
                    for (Product product : products) {
                        System.out.println(product);
                    }

                    break;

                case "2":
                    // The user chose option 2 → Add a new product.

                    // Ask the user for the film's title.
                    System.out.print("Enter product Name : ");
                    String productName = myScanner.nextLine();

                    System.out.print("Enter category Id : ");
                    int categoryId = myScanner.nextInt();
                    myScanner.nextLine();//i can also parse it

                    // Ask the user for the film's rental rate.
                    System.out.print("Enter Product price: ");
                    double unitPrice = Double.parseDouble(myScanner.nextLine());

                    // Create a new Product object and set its data.
                    Product newProduct = new Product();
                    newProduct.setProductName(productName);
                    newProduct.setCategoryId(categoryId);
                    newProduct.setUnitPrice(unitPrice);

                    // Add the new product to the DAO (which stores it in memory).
                    productDao.add(newProduct);

                    // Let the user know that the product was added.
                    System.out.println("Product added successfully.");

                    break;

                case "3": // Update a product
                    System.out.print("Enter product ID to update: ");
                    int updateId = Integer.parseInt(myScanner.nextLine());

                    System.out.print("Enter new product Name: ");
                    String newName = myScanner.nextLine();

                    System.out.print("Enter new category Id: ");
                    int newCategoryId = Integer.parseInt(myScanner.nextLine());

                    System.out.print("Enter new Product price: ");
                    double newPrice = Double.parseDouble(myScanner.nextLine());

                    Product updatedProduct = new Product(updateId, newName, newCategoryId, newPrice);
                    boolean updated = productDao.update(updatedProduct);

                    if (updated) {
                        System.out.println("Product updated successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case "4": // Delete a product
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = Integer.parseInt(myScanner.nextLine());

                    boolean deleted = productDao.deleteById(deleteId);

                    if (deleted) {
                        System.out.println("Product deleted successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case "5": // Search for a product
                    System.out.print("Enter name to search: ");
                    String searchTerm = myScanner.nextLine();

                    List<Product> searchResults = productDao.searchByName(searchTerm);

                    if (searchResults.isEmpty()) {
                        System.out.println("No matching products found.");
                    } else {
                        System.out.println("Matching products:");
                        for (Product p : searchResults) {
                            System.out.println(p);
                        }
                    }
                    break;

                case "6":
                    // The user chose option 3 → Exit the program.

                    // Print a goodbye message.
                    System.out.println("Goodbye!");

                    // End the program with a success status (0).
                    System.exit(0);

                default:
                    // The user entered something that is not a valid option.
                    // Tell the user the input was invalid and show the menu again.
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}























