package kata.academy.socialnetwork.web.util;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.function.Supplier;

public final class ApiValidationUtil {

    public static void requireTrue(boolean value, String message) {
        if (!value) {
            throw validationExceptionSupplier(message).get();
        }
    }

    public static void requireFalse(boolean value, String message) {
        if (value) {
            throw validationExceptionSupplier(message).get();
        }
    }

    public static void requireNotNull(Object value, String message) {
        if (value == null) {
            throw validationExceptionSupplier(message).get();
        }
    }

    public static void requireNull(Object value, String message) {
        if (value != null) {
            throw validationExceptionSupplier(message).get();
        }
    }

    private static Supplier<ConstraintViolationException> validationExceptionSupplier(String message) {
        return () -> new ConstraintViolationException(message, Collections.emptySet());
    }
}
