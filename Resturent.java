import java.util.*;

class MenuItem {
    private String name;
    private double price;
    private String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " - ₹" + price;
    }
}

class Menu {
    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void displayMenu() {
        System.out.println("===== Vintage Restaurant Menu =====");
        Map<String, List<MenuItem>> categorized = new HashMap<>();

        for (MenuItem item : items) {
            categorized.putIfAbsent(item.getCategory(), new ArrayList<>());
            categorized.get(item.getCategory()).add(item);
        }

        for (String category : categorized.keySet()) {
            System.out.println("\n-- " + category + " --");
            for (MenuItem item : categorized.get(category)) {
                System.out.println(item);
            }
        }
    }

    public MenuItem findItem(String name) {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}

public class VintageRestaurantApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        // Adding sample items
        menu.addItem(new MenuItem("Tomato Soup", 120, "Starters"));
        menu.addItem(new MenuItem("Paneer Tikka", 220, "Starters"));
        menu.addItem(new MenuItem("Butter Naan", 40, "Main Course"));
        menu.addItem(new MenuItem("Dal Makhani", 180, "Main Course"));
        menu.addItem(new MenuItem("Gulab Jamun", 90, "Desserts"));
        menu.addItem(new MenuItem("Masala Chai", 50, "Beverages"));

        List<MenuItem> order = new ArrayList<>();
        boolean running = true;

        while (running) {
            menu.displayMenu();
            System.out.println("\nEnter item name to order (or type 'done' to finish): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("done")) {
                running = false;
            } else {
                MenuItem item = menu.findItem(choice);
                if (item != null) {
                    order.add(item);
                    System.out.println(item.getName() + " added to your order.");
                } else {
                    System.out.println("Item not found. Please try again.");
                }
            }
        }

        // Display bill
        System.out.println("\n===== Your Bill =====");
        double total = 0;
        for (MenuItem item : order) {
            System.out.println(item);
            total += item.getPrice();
        }
        System.out.println("Total: ₹" + total);
        System.out.println("Thank you for dining at Vintage Restaurant!");
    }
}
