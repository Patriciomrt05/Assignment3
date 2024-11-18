import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVLoader {

    // Method to load products from CSV into Red-Black Tree
    public static void loadProductsFromCSV(String filename, RedBlackBST<String, Product> tree) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Trim any leading/trailing whitespace
                line = line.trim();

                // Find the first comma (for the Uniq Id) and the last comma (for the price)
                int firstComma = line.indexOf(',');
                int lastComma = line.lastIndexOf(',');

                // Extract the Uniq Id
                String productId = line.substring(0, firstComma).trim();

                // Extract the price (everything after the last comma)
                String priceStr = line.substring(lastComma + 1).trim();
                double price = parsePrice(priceStr);  // Parse price

                // Extract the product name and category (everything between first and last comma)
                String middleSection = line.substring(firstComma + 1, lastComma).trim();

                // Find the last comma in the middle section to separate category and name
                int lastCategoryComma = middleSection.lastIndexOf(',');
                String productName = middleSection.substring(0, lastCategoryComma).trim();
                String category = middleSection.substring(lastCategoryComma + 1).trim();

                // Create a product and insert it into the tree
                Product product = new Product(productId, productName, category, price);
                if (!tree.contains(productId)) {
                    tree.put(productId, product);
                } else {
                    System.out.println("Product with ID " + productId + " already exists.");
                }
            }
        }
    }

    // Method to parse a price string into a double
    private static double parsePrice(String priceStr) {
        try {
            StringBuilder sanitizedPrice = new StringBuilder();

            for (int i = 0; i < priceStr.length(); i++) {
                char c = priceStr.charAt(i);
                // Allow digits and the decimal point
                if (Character.isDigit(c) || c == '.') {
                    sanitizedPrice.append(c);
                }
            }

            if (sanitizedPrice.length() == 0) {
                return -1;
            }

            return Double.parseDouble(sanitizedPrice.toString());  // Parse the numeric part as a double
        } catch (NumberFormatException e) {
            return -1;  // Return -1 if the price format is invalid
        }
    }

}
