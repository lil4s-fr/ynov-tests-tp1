package fr.lil4s;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Unit tests for the PasswordValidator class")
public class PasswordValidatorTest {

    @Nested
    @DisplayName("Passwords that should be valid")
    class ValidPasswords {

        PasswordValidator validator;

        @BeforeEach
        void setUp() {
            validator = new PasswordValidator();
        }

        @Test
        @DisplayName("Should return true on isValid() for Password1!")
        void shouldReturnTrueOnIsValidForPassword1() {
            assertThat(validator.isValid("Password1!")).isTrue();
            assertThat(validator.getErrorMessage("Password1!")).isEqualTo("Password is valid");
        }

        @Test
        @DisplayName("Should return true on isValid() for Admin2024@")
        void shouldReturnTrueOnIsValidForAdmin2024() {
            assertThat(validator.isValid("Admin2024@")).isTrue();
            assertThat(validator.getErrorMessage("Admin2024@")).isEqualTo("Password is valid");
        }
    }

    @Nested
    @DisplayName("Passwords that should be invalid")
    class InvalidPasswords {

        PasswordValidator validator;

        @BeforeEach
        void setUp() {
            validator = new PasswordValidator();
        }

        @Test
        @DisplayName("Should return false on isValid() for null password")
        void shouldReturnFalseOnIsValidForNullPassword() {
            assertThat(validator.isValid(null)).isFalse();
        }

        @Test
        @DisplayName("Should return false on isValid() for password shorter than 8 characters")
        void shouldReturnFalseOnIsValidForShortPassword() {
            assertThat(validator.isValid("short1!")).isFalse();
        }

        @Test
        @DisplayName("Should return false on isValid() for password without lowercase letter")
        void shouldReturnFalseOnIsValidForPasswordWithoutLowercase() {
            assertThat(validator.isValid("PASSWORD1!")).isFalse();
        }

        @Test
        @DisplayName("Should return false on isValid() for password without uppercase letter")
        void shouldReturnFalseOnIsValidForPasswordWithoutUppercase() {
            assertThat(validator.isValid("password1!")).isFalse();
        }

        @Test
        @DisplayName("Should return false on isValid() for password without digit")
        void shouldReturnFalseOnIsValidForPasswordWithoutDigit() {
            assertThat(validator.isValid("Password!")).isFalse();
        }

        @Test
        @DisplayName("Should return false on isValid() for password without special character")
        void shouldReturnFalseOnIsValidForPasswordWithoutSpecialCharacter() {
            assertThat(validator.isValid("Password1")).isFalse();
        }

        @Test
        @DisplayName("Should return false on isValid() for empty password")
        void shouldReturnFalseOnIsValidForEmptyPassword() {
            assertThat(validator.isValid("")).isFalse();
        }
    }

    @Nested
    @DisplayName("Messages returned by getErrorMessage()")
    class ErrorMessages {

        PasswordValidator validator;

        @BeforeEach
        void setUp() {
            validator = new PasswordValidator();
        }

        @Test
        @DisplayName("Should return null message on getErrorMessage() for null password")
        void shouldReturnNullMessageOnGetErrorMessageForNullPassword() {
            assertThat(validator.getErrorMessage(null)).isEqualTo("Password must not be null");
        }

        @Test
        @DisplayName("Should return length message on getErrorMessage() for short password")
        void shouldReturnLengthMessageOnGetErrorMessageForShortPassword() {
            assertThat(validator.getErrorMessage("short1!")).isEqualTo("Password must contain at least 8 characters");
        }

        @Test
        @DisplayName("Should return lowercase message on getErrorMessage() for password without lowercase letter")
        void shouldReturnLowercaseMessageOnGetErrorMessageForPasswordWithoutLowercase() {
            assertThat(validator.getErrorMessage("PASSWORD1!")).isEqualTo("Password must contain at least one lowercase letter");
        }

        @Test
        @DisplayName("Should return uppercase message on getErrorMessage() for password without uppercase letter")
        void shouldReturnUppercaseMessageOnGetErrorMessageForPasswordWithoutUppercase() {
            assertThat(validator.getErrorMessage("password1!")).isEqualTo("Password must contain at least one uppercase letter");
        }

        @Test
        @DisplayName("Should return digit message on getErrorMessage() for password without digit")
        void shouldReturnDigitMessageOnGetErrorMessageForPasswordWithoutDigit() {
            assertThat(validator.getErrorMessage("Password!")).isEqualTo("Password must contain at least one digit");
        }

        @Test
        @DisplayName("Should return special character message on getErrorMessage() for password without special character")
        void shouldReturnSpecialCharacterMessageOnGetErrorMessageForPasswordWithoutSpecialCharacter() {
            assertThat(validator.getErrorMessage("Password1")).isEqualTo("Password must contain at least one special character");
        }

        @Test
        @DisplayName("Should return valid message on getErrorMessage() for valid password")
        void shouldReturnValidMessageOnGetErrorMessageForValidPassword() {
            assertThat(validator.getErrorMessage("Password1!")).isEqualTo("Password is valid");
        }

        @Test
        @DisplayName("Should return length message on getErrorMessage() for empty password")
        void shouldReturnLengthMessageOnGetErrorMessageForEmptyPassword() {
            assertThat(validator.getErrorMessage("")).isEqualTo("Password must contain at least 8 characters");
        }
    }

    @Nested
    @DisplayName("Parameterized tests")
    class ParameterizedTests {

        PasswordValidator validator;

        @BeforeEach
        void setUp() {
            validator = new PasswordValidator();
        }

        @ParameterizedTest
        @CsvSource(value = {
            "Password1!|true|Password is valid",
            "Admin2024@|true|Password is valid",
            "short1!|false|Password must contain at least 8 characters",
            "PASSWORD1!|false|Password must contain at least one lowercase letter",
            "password1!|false|Password must contain at least one uppercase letter",
            "Password!|false|Password must contain at least one digit",
            "Password1|false|Password must contain at least one special character",
            "[null]|false|Password must not be null"
        }, delimiter = '|', nullValues = "[null]")
        @DisplayName("Should return expected result on isValid() and getErrorMessage() with CsvSource")
        void shouldReturnExpectedResultOnIsValidAndGetErrorMessageWithCsvSource(String password, boolean expectedValid, String expectedMessage) {
            boolean actualValid = validator.isValid(password);
            String actualMessage = validator.getErrorMessage(password);

            assertThat(actualValid).isEqualTo(expectedValid);
            assertThat(actualMessage).isEqualTo(expectedMessage);
        }

        @ParameterizedTest
        @ValueSource(strings = {"Password1!", "Admin2024@", "Aa1!aaaa"})
        @DisplayName("Should return true on isValid() with ValueSource valid passwords")
        void shouldReturnTrueOnIsValidWithValueSource(String password) {
            assertThat(validator.isValid(password)).isTrue();
            assertThat(validator.getErrorMessage(password)).isEqualTo("Password is valid");
        }

        @ParameterizedTest
        @MethodSource("fr.lil4s.PasswordValidatorTest#invalidPasswordProvider")
        @DisplayName("Should return expected error message on getErrorMessage() with MethodSource")
        void shouldReturnExpectedErrorMessageOnGetErrorMessageWithMethodSource(String password, String expectedMessage) {
            assertThat(validator.isValid(password)).isFalse();
            assertThat(validator.getErrorMessage(password)).isEqualTo(expectedMessage);
        }
    }

    static Stream<Arguments> invalidPasswordProvider() {
        return Stream.of(
            arguments("short1!", "Password must contain at least 8 characters"),
            arguments("PASSWORD1!", "Password must contain at least one lowercase letter"),
            arguments("password1!", "Password must contain at least one uppercase letter"),
            arguments("Password!", "Password must contain at least one digit"),
            arguments("Password1", "Password must contain at least one special character")
        );
    }
}
