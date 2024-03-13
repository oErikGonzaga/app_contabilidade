package com.egti.app_contabilidade.util;

import java.util.Objects;

public class ValidationUtil {

    public static <T> boolean isValueValid(T newValue, T currentValue) {

        if (!Objects.equals(newValue, currentValue)) {
            if (newValue instanceof Enum<?> enumNewValue && currentValue instanceof Enum<?> enumCurrentValue) {
                return !enumNewValue.name().equals(enumCurrentValue.name());
            }

            if (newValue instanceof String) {
                return !((String) newValue).isEmpty();

            }
            return Objects.nonNull(newValue);

        }

        return false;
    }
}
