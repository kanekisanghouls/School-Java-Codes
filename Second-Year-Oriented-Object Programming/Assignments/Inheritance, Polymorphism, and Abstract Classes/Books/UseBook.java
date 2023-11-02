public class UseBook {
    public static void main(String[] args) {
        Book fictionBook = new Fiction("Harry Potter");
        fictionBook.display();
        System.out.println();
        Book nonFictionBook = new NonFiction("Introduction to Programming");
        nonFictionBook.display();
    }
}