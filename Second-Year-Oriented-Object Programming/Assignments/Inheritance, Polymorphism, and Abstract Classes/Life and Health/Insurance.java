abstract class Insurance {
    protected String type;
    protected double monthlyPrice;
    public Insurance(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public double getMonthlyPrice() {
        return monthlyPrice;
    }
    public abstract void setCost();
    public abstract void display();
}