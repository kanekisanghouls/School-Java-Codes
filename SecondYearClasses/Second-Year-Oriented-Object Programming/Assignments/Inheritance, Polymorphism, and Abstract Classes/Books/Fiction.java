class Fiction extends Book {
    public Fiction(String title) {
        super(title);
        setPrice();
    }

    public void setPrice() {
        setPrice(24.99);

    }
}