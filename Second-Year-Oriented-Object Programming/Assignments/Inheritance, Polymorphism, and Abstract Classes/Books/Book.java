abstract class Book {
    private String title;
    private double price;
    public Book(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    protected void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public abstract void setPrice();
    public void display() {
        System.out.println("Title: " + getTitle());
        System.out.println("Price: $" + getPrice());
    }
}
