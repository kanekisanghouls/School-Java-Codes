import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringList {
private List<String> items;

public StringList() {
this.items = new ArrayList<>();
}

public void add(String element) {
items.add(element);
}

public void clearList() {
items.clear();
}

public void removeItem(String item) {
items.remove(item);
}

public int getListSize() {
return items.size();
}

public void display() {
for (int i = 0; i < items.size(); i++) {
System.out.println("[" + i + "] " + items.get(i));
}
}

public int searchItem(String searchItem) {
return items.indexOf(searchItem);
}

public static void main(String[] args) {
StringList stringList = new StringList();

// Load previously saved items if available
try {
FileInputStream fileIn = new FileInputStream("items.ser");
ObjectInputStream in = new ObjectInputStream(fileIn);
stringList.items = (ArrayList<String>) in.readObject();
in.close();
fileIn.close();
} catch (IOException | ClassNotFoundException e) {
// Ignore errors if the file doesn't exist or cannot be read
}

Scanner scanner = new Scanner(System.in);
int choice = 0;

while (choice != 7) {
System.out.println("");
System.out.println("Menu:");
System.out.println("1. Add Item to List");
System.out.println("2. Clear List");
System.out.println("3. Remove Item");
System.out.println("4. Display List Size");
System.out.println("5. Display All Items");
System.out.println("6. Search for Item");
System.out.println("7. Exit");
System.out.println("");

choice = scanner.nextInt();
scanner.nextLine();

if (choice == 1) {
System.out.println("Enter an element to add to the list: ");
String element = scanner.nextLine();
stringList.add(element);
} else if (choice == 2) {
stringList.clearList();
System.out.println("List cleared.");
} else if (choice == 3) {
System.out.println("Enter the name of the item to remove: ");
String itemNameToRemove = scanner.nextLine();
stringList.removeItem(itemNameToRemove);
System.out.println("Removed Item: " + itemNameToRemove);
} else if (choice == 4) {
System.out.println("List size: " + stringList.getListSize());
} else if (choice == 5) {
stringList.display();
} else if (choice == 6) {
System.out.println("Enter the item to search for: ");
String searchItem = scanner.nextLine();
int index = stringList.searchItem(searchItem);

if (index != -1) {
System.out.println("Found at index " + index + ": " + searchItem);
} else {
System.out.println("Item not found.");
}
} else if (choice != 7) {
System.out.println("Invalid choice!");
}
}
