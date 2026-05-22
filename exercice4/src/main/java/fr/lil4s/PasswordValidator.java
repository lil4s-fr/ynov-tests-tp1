package fr.lil4s;

public class PasswordValidator {

    public boolean isValid(String password) {
        if (password == null){
            return false;
        }

        if (password.length() < 8) {
            return false;
        }

        if (!hasUpperCase(password)) {
            return false;
        }

        if (!hasLowerCase(password)) {
            return false;
        }

        if (!hasNumber(password)) {
            return false;
        }

        if (!hasSpecialCharacter(password)) {
            return false;
        }

        return true;
    }

    public String getErrorMessage(String password) {
        if (password == null){
            return "Password must not be null";
        }

        if (password.length() < 8) {
            return "Password must contain at least 8 characters";
        }

        if (!hasUpperCase(password)) {
            return "Password must contain at least one uppercase letter";
        }

        if (!hasLowerCase(password)) {
            return "Password must contain at least one lowercase letter";
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
        return password.matches("^[A-Z]+$");
    }

    private boolean hasLowerCase(String password) {
        return password.matches("^[a-z]+$");
    }

    private boolean hasNumber(String password) {
        return password.matches("^[0-9]+$");
    }

    private boolean hasSpecialCharacter(String password) {
        return password.matches(".*[!@#$%].*");
    }
}
