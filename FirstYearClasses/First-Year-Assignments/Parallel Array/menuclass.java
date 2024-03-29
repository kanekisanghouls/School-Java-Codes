import java.util.Scanner;
public class MENU {
    static Scanner sc = new Scanner(System.in);
    static Person[] persons = new Person[100];
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("0 - Exit \n1 - Add Data \n2 - Search Data \n3 - Update Data \n4 - Display All");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 0:
                    System.out.println("Thank you for using the program.");
                    break;
                case 1:
                    addData();
                    break;
                case 2:
                    searchData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    displayData();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }
    public static void addData() {
        int idNo;
        String lastname, firstname;
        System.out.print("Enter ID No.: ");
        idNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Lastname: ");
        lastname = sc.nextLine();
        System.out.print("Enter Firstname: ");
        firstname = sc.nextLine();
        Person person = new Person(idNo, lastname, firstname);
        for (int i = 0; i < persons.length; i++) {
            if (persons[i] == null) {
                persons[i] = person;
                System.out.println("Data added successfully.");
                break;
            }
        }
    }
    public static void searchData() {
        System.out.print("Enter ID No. to search: ");
        int idNo = sc.nextInt();
        sc.nextLine();
        boolean found = false;
        for (Person person : persons) {
            if (person != null && person.getIdNo() == idNo) {
                System.out.println("ID No.: " + person.getIdNo());
                System.out.println("Lastname: " + person.getLastname());
                System.out.println("Firstname: " + person.getFirstname());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Data not found.");
        }
    }
    public static void updateData() {
        boolean found; int idNo;
        String lastname, firstname;
        System.out.print("Enter ID No. to update: ");
        idNo = sc.nextInt();
        sc.nextLine();
        found = false;
        for (Person person : persons) {
            if (person != null && person.getIdNo() == idNo) {
                System.out.print("Enter new Lastname: ");
                lastname = sc.nextLine();
                person.setLastname(lastname);
                System.out.print("Enter new Firstname: ");
                firstname = sc.nextLine();
                person.setFirstname(firstname);
                System.out.println("Data updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Data not found.");
        }
    }
    public static void displayData() {
        System.out.println("ID No.\tLastname\tFirstname");
        for (Person person : persons) {
            if (person != null) {
                System.out.println(person.getIdNo() + "\t" + person.getLastname() + "\t\t" + person.getFirstname());
            }
        }
    }
}
