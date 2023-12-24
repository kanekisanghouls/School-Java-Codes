class Life extends Insurance {
    public Life() {
        super("Life");
        setCost();
    }
    public void setCost() {
        monthlyPrice = 36.0;
    }
    public void display() {
        System.out.println("Type of insurance: " + getType());
        System.out.println("Monthly Price: $" + getMonthlyPrice());
    }
}