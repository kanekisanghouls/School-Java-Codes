public StringList() {
        this.array = new String[10];
        this.size = 0;
        load();
    }

    public void add(String element) {
        if (size == array.length) {
            String[] newArray = new String[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = element;
        size++;
    }

    public void display() {
        System.out.println("String List:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] " + array[i]);
        }
    }

    public void clear() {
        array = new String[10];
        size = 0;
    }

    public int search(String element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void save() {
        try (FileWriter writer = new FileWriter("stringlist.txt")) {
            for (int i = 0; i < size; i++) {
                writer.write(array[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stringlist.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(String element) {
        int index = search(element);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
        } else {
            System.out.println("The element \"" + element + "\" is not found in the list.");
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        StringList stringList = new StringList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Add Item to List");
        System.out.println("2. Display All Items");
        System.out.println("3. Search for Item in List");
        System.out.println("4. Clear List");
        System.out.println("5. Search Item to Remove");
        System.out.println("6. Show Size of the List");
        System.out.println("7. Exit");

        int choice = scanner.nextInt();

        while (choice != 7) {
            if (choice == 1) {
                System.out.println("Enter an element to add to the list: ");
                String element = scanner.next();
                stringList.add(element);
            } else if (choice == 2) {
                stringList.display();
            } else if (choice == 3) {
                System.out.println("Enter an element to search for: ");
                String element = scanner.next();
                int index = stringList.search(element);
                if (index != -1) {
                    System.out.println("The element \"" + element + "\" is found at index " + index + ".");
                } else {
                    System.out.println("The element \"" + element + "\" is not found in the list.");
                }
            } else if (choice == 4) {
                stringList.clear();
                System.out.println("The list has been cleared.");
            } else if (choice == 5) {
                System.out.println("Enter an element to remove from the list: ");
                String element = scanner.next();
                stringList.remove(element);
            } else if (choice == 6) {
                System.out.println("The size of the list is: " + stringList.size());
            } else {
                System.out.println("Invalid choice!");
            }

            System.out.println("Menu:");
            System.out.println("1. Add Item to List");
            System.out.println("2. Display All Items");
            System.out.println("3. Search for Item in List");
            System.out.println("4. Clear List");
            System.out.println("5. Search Item to Remove");
            System.out.println("6. Show Size of the List");
            System.out.println("7. Exit");

            choice = scanner.nextInt();
        }

        stringList.save();
        System.out.println("Goodbye!");
    }
}
