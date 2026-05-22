package fr.lil4s;

public class PriceCalculator {

    public double calculateTotalPrice(double unitPrice, int quantity){
        if(unitPrice < 0){
            throw new IllegalArgumentException("The unit price cannot be negative");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("The quantity cannot be negative");
        }
        return unitPrice * quantity;
    }

    public double applyDiscount(double price, double discountRate){
        if(price < 0){
            throw new IllegalArgumentException("The price cannot be negative");
        }
        if(discountRate < 0){
            throw new IllegalArgumentException("The discount rate cannot be negative");
        }
        return discountRate * price;
    }

    double calculateVat(double price, double vatRate){
        if(price < 0){
            throw new IllegalArgumentException("The price cannot be negative");
        }
        if(vatRate < 0){
            throw new IllegalArgumentException("The vat rate cannot be negative");
        }
        return vatRate * price;
    }

    double calculatePriceWithVat(double price, double vatRate){
        if(price < 0){
            throw new IllegalArgumentException("The price cannot be negative");
        }
        if(vatRate < 0){
            throw new IllegalArgumentException("The vat rate cannot be negative");
        }
        return (vatRate * price) + price;
    }
}