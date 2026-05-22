package fr.lil4s;

public class PasswordValidator {

    public boolean isValid(String password) {
        return "Password is valid".equals(getErrorMessage(password));
    }

    public String getErrorMessage(String password) {
        if (password == null) {
            return "Password must not be null";
        }

        if (password.length() < 8) {
            return "Password must contain at least 8 characters";
        }

        if (!hasLowerCase(password)) {
            return "Password must contain at least one lowercase letter";
        }

        if (!hasUpperCase(password)) {
            return "Password must contain at least one uppercase letter";
        }

        if (!hasNumber(password)) {
            return "Password must contain at least one digit";
        }

        if (!hasSpecialCharacter(password)) {
            return "Password must contain at least one special character";
        }

        return "Password is valid";
    }

    private boolean hasUpperCase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean hasLowerCase(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean hasNumber(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean hasSpecialCharacter(String password) {
        return password.matches(".*[!@#$%].*");
    }
}
