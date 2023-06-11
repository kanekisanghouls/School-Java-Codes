import java.util.Scanner;

public class ConversionTester {
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter meters: ");
        int meters = input.nextInt();
        
        System.out.print("Enter feet: ");
        int feet = input.nextInt();
        
        System.out.print("Enter miles: ");
        double miles = input.nextDouble();
        
        System.out.print("Enter pounds: ");
        int pounds = input.nextInt();
        
        System.out.print("Enter grams: ");
        int grams = input.nextInt();
        
        System.out.print("Enter kilograms: ");
        int kilograms = input.nextInt();
        
        MetricConversion metricConverter = new MetricConversion();
        
        double centimeters = metricConverter.MeterToCentimeter(meters);
        int inches = metricConverter.FeetToInch(feet);
        double kilometers = metricConverter.MileToKilometer(miles);
        
        UnitConversion unitConverter = new UnitConversion();
        
        int ounces = unitConverter.PoundToOunce(pounds);
        int milligrams = unitConverter.GramToMilligram(grams);
        int carats = unitConverter.KilogramToCarat(kilograms);
        
        System.out.println("\nConversions");
        System.out.println("-----------");
        System.out.println(meters + " meters = " + centimeters + " centimeters");
        System.out.println(feet + " feet = " + inches + " inches");
        System.out.println(miles + " miles = " + kilometers + " kilometers");
        System.out.println(pounds + " pounds = " + ounces + " ounces");
        System.out.println(grams + " grams = " + milligrams + " milligrams");
        System.out.println(kilograms + " kilograms = " + carats + " carats");
    }
}