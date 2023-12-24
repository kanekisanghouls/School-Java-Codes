public class BookArray {
    public static void main(String[] args) {
        Book[] books = new Book[10];
        books[0] = new Fiction("Book 1");
        books[1] = new Fiction("Book 2");
        books[2] = new NonFiction("Book 3");
        books[3] = new Fiction("Book 4");
        books[4] = new NonFiction("Book 5");
        books[5] = new Fiction("Book 6");
        books[6] = new NonFiction("Book 7");
        books[7] = new Fiction("Book 8");
        books[8] = new NonFiction("Book 9");
        books[9] = new Fiction("Book 10");
        for (int i = 0; i < books.length; i++) {
            System.out.println("Book " + (i + 1));
            books[i].display();
            System.out.println();
        }
    }
}
