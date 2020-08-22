package chronity.triangle.validation;

/**
 * Common validation functions for reuse
 * implemented as static methods
 * (e.g. email, min, max, simple regex etc.)
 */
public final class Validations {
    private Validations() {}

    public static boolean email(String email){
        return false;
    }

    public static boolean minMax(int min, int max){
        return false;
    }

    public static boolean uri(String uri){
        return false;
    }
}
