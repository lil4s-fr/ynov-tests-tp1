package fr.lil4s;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GradingCalculatorTest {

    @Test
    void shouldGradeBeAWhenScoreIs95AndAttendanceIs90(){
        // Arrange
        int score = 95;
        int attendance = 90;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'A';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGradeBeBWhenScoreIs85AndAttendanceIs90(){
        // Arrange
        int score = 85;
        int attendance = 90;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'B';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGradeBeCWhenScoreIs65AndAttendanceIs90(){
        // Arrange
        int score = 65;
        int attendance = 90;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'C';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGradeBeBWhenScoreIs95AndAttendanceIs65(){
        // Arrange
        int score = 95;
        int attendance = 65;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'B';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGradeBeFWhenScoreIs95AndAttendanceIs55(){
        // Arrange
        int score = 95;
        int attendance = 55;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'F';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void shouldGradeBeFWhenScoreIs65AndAttendanceIs55(){
        // Arrange
        int score = 65;
        int attendance = 55;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'F';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGradeBeFWhenScoreIs50AndAttendanceIs90(){
        // Arrange
        int score = 50;
        int attendance = 90;
        GradingCalculator calculator = new GradingCalculator(score, attendance);

        char expected = 'F';
        // Act
        char actual = calculator.getGrade();
        // Assert

        assertThat(actual).isEqualTo(expected);
    }
}