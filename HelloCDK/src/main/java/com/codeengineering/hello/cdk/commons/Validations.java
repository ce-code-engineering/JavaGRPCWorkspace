package com.codeengineering.hello.cdk.commons;

public class Validations {
    public final static void requireNonEmpty(final String value, final String message) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
