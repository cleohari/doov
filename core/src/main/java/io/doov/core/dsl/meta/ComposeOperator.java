/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package io.doov.core.dsl.meta;

public class ComposeOperator implements Operator {
    private final Operator operator;
    private final FieldMetadata other;

    public ComposeOperator(Operator operator, FieldMetadata other) {
        this.operator = operator;
        this.other = other;
    }

    @Override
    public String readable() {
        return  operator.readable() + " " + other.readable();
    }
}