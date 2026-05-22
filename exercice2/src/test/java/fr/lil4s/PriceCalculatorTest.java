package fr.lil4s;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PriceCalculatorTest {

    @Test
    void shouldReturn30WhenUnitPriceIs10AndQuantityIs3(){
        // Arrange
        double unitPrice = 10.0;
        int quantity = 3;

        PriceCalculator calculator = new PriceCalculator();

        double expected = 30.0;
        // Act
        double actual = calculator.calculateTotalPrice(unitPrice, quantity);
        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenUnitPriceIsNegative(){
        // Arrange
        double unitPrice = -10.0;
        int quantity = 3;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.calculateTotalPrice(unitPrice, quantity);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The unit price cannot be negative");
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsNegative(){
        double unitPrice = 10.0;
        int quantity = -1;
        PriceCalculator calculator = new PriceCalculator();

        assertThatThrownBy(() -> {
            calculator.calculateTotalPrice(unitPrice, quantity);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The quantity cannot be negative");
    }

    @Test
    void shouldReturn5WhenPriceIs100AndDiscountRateIs0Dot05(){
        // Arrange
        double price = 100.0;
        double discountRate = 0.05;
        PriceCalculator calculator = new PriceCalculator();

        double expected = 5.0;
        // Act
        double actual = calculator.applyDiscount(price, discountRate);
        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenApplyDiscountPriceIsNegative(){
        // Arrange
        double price = -100.0;
        double discountRate = 0.05;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.applyDiscount(price, discountRate);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The price cannot be negative");
    }

    @Test
    void shouldThrowExceptionWhenDiscountRateIsNegative(){
        // Arrange
        double price = 100.0;
        double discountRate = -0.05;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.applyDiscount(price, discountRate);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The discount rate cannot be negative");
    }

    @Test
    void shouldReturn20WhenPriceIs100AndVatRateIs0Dot20(){
        // Arrange
        double price = 100.0;
        double vatRate = 0.20;
        PriceCalculator calculator = new PriceCalculator();

        double expected = 20.0;
        // Act
        double actual = calculator.calculateVat(price, vatRate);
        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenCalculateVatPriceIsNegative(){
        // Arrange
        double price = -100.0;
        double vatRate = 0.20;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.calculateVat(price, vatRate);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The price cannot be negative");
    }

    @Test
    void shouldThrowExceptionWhenVatRateIsNegative(){
        // Arrange
        double price = 100.0;
        double vatRate = -0.20;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.calculateVat(price, vatRate);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The vat rate cannot be negative");
    }

    @Test
    void shouldReturn120WhenPriceIs100AndVatRateIs0Dot20(){
        // Arrange
        double price = 100.0;
        double vatRate = 0.20;
        PriceCalculator calculator = new PriceCalculator();

        double expected = 120.0;
        // Act
        double actual = calculator.calculatePriceWithVat(price, vatRate);
        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenCalculatePriceWithVatPriceIsNegative(){
        // Arrange
        double price = -100.0;
        double vatRate = 0.20;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.calculatePriceWithVat(price, vatRate);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The price cannot be negative");
    }

    @Test
    void shouldThrowExceptionWhenCalculatePriceWithVatRateIsNegative(){
        // Arrange
        double price = 100.0;
        double vatRate = -0.20;
        PriceCalculator calculator = new PriceCalculator();

        // Act And Assert
        assertThatThrownBy(() -> {
            calculator.calculatePriceWithVat(price, vatRate);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The vat rate cannot be negative");
    }
}