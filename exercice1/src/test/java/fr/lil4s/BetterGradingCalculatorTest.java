package fr.lil4s;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BetterGradingCalculatorTest {

    @ParameterizedTest
    @CsvSource({
        "95, 90, 'A'",
        "85, 90, 'B'",
        "65, 90, 'C'",
        "95, 65, 'B'",
        "95, 55, 'F'",
        "65, 55, 'F'",
        "50, 90, 'F'"
    })
    void shouldCalculateCorrectGrade(int score, int attendance, char expected) {
        // Arrange
        GradingCalculator calculator = new GradingCalculator(score, attendance);
        // Act
        char actual = calculator.getGrade();
        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}