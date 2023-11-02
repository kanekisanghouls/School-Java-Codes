class Health extends Insurance {
    public Health() {
        super("Health");
        setCost();
    }
    public void setCost() {
        monthlyPrice = 196.0;
    }
    public void display() {
        System.out.println("Type of insurance: " + getType());
        System.out.println("Monthly Price: $" + getMonthlyPrice());
    }
}