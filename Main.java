import java.io.IOException;
import java.util.Scanner;

/*
The tree remains balanced after multiple operations due to the set of
rules and operations (rotations and color flips) during each insertion
or deletion. When a new node is inserted, the tree checks for violations
of red-black properties, such as having two consecutive red nodes, which
are then fixed using rotations and color flipping. These operations
ensure the tree remains balanced maintaining a logarithmic height of O(log n).
This balancing ensures that the time complexity for both insertion and search
remains O(log n), even after many operations, effectively supporting the
management of large product datasets by guaranteeing that operations remain
efficient despite the increasing size of the tree.
 */

public class Main {
    public static void main(String[] args) {
        RedBlackBST<String, Product> tree = new RedBlackBST<>();

        // Start measuring time for insertion
        long startTime = System.nanoTime();

        // Load products from CSV file into Red-Black Tree
        try {
            CSVLoader.loadProductsFromCSV("src/amazon-product-data.csv", tree);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken to insert products: " + elapsedTime + " ns");

        Scanner scanner = new Scanner(System.in);

        // Query 1
        System.out.print("Enter product ID to search: ");
        String queryId = scanner.nextLine().trim();
        startTime = System.nanoTime();
        Product result = tree.get(queryId);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        if (result != null) {
            System.out.println("Query 1: Found Product - " + result);
        } else {
            System.out.println("Query 1: Product not found.");
        }
        System.out.println("Time taken for Query 1 (search): " + elapsedTime + " ns");

        // Query 2
        System.out.print("Enter product ID to search: ");
        String queryId2 = scanner.nextLine().trim();
        startTime = System.nanoTime();
        Product result2 = tree.get(queryId2);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        if (result2 != null) {
            System.out.println("Query 2: Found Product - " + result2);
        } else {
            System.out.println("Query 2: Product not found.");
        }
        System.out.println("Time taken for Query 2 (search): " + elapsedTime + " ns");

        // Query 3
        System.out.print("Enter product ID to search: ");
        String queryId3 = scanner.nextLine().trim();
        startTime = System.nanoTime();
        Product result3 = tree.get(queryId3);
        endTime = System.nanoTime();
        if (result3 != null) {
            System.out.println("Query 3: Found Product - " + result3);
        } else {
            System.out.println("Query 3: Product not found.");
        }
        System.out.println("Time taken for Query 3 (search): " + elapsedTime + " ns");

        // Regular Insertion
        Product newProduct = new Product("4v75b61333fc16e7084b43fc926e502d", "New Product", "Category", 50.00);
        if (tree.contains(newProduct.getProductId())) {
            System.out.println("Edge Case: Duplicate insertion attempt - Product with ID " + newProduct.getProductId() + " already exists.");
        } else {
            startTime = System.nanoTime();
            tree.put(newProduct.getProductId(), newProduct);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Inserted new product.");
            System.out.println("Time taken to insert product: " + elapsedTime + " ns");
        }

        // Edge Case: Insert a duplicate product ID
        Product duplicateProduct = new Product("4c69b61db1fc16e7013b43fc926e502d", "Duplicate Product", "Category", 100.00);
        if (tree.contains(duplicateProduct.getProductId())) {
            System.out.println("Edge Case: Duplicate insertion attempt - Product with ID " + duplicateProduct.getProductId() + " already exists.");
        } else {
            startTime = System.nanoTime();
            tree.put(duplicateProduct.getProductId(), duplicateProduct);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Inserted new product.");
            System.out.println("Time taken to insert product: " + elapsedTime + " ns");
        }

        // Scalability Test
        System.out.println("\n--- Scalability Test (Large Dataset) ---");
        int numProducts = 10000;
        startTime = System.nanoTime();
        for (int i = 0; i < numProducts; i++) {
            String id = "prod" + i;
            Product product = new Product(id, "Product " + i, "Category", Math.random() * 100);
            tree.put(id, product);
        }
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime);
        System.out.println("Time taken for inserting " + numProducts + " products: " + elapsedTime + " nanoseconds.");
    }
}
