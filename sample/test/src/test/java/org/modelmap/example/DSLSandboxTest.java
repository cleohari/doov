/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.modelmap.example;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.modelmap.core.dsl.lang.EValidity.INVALID;
import static org.modelmap.core.dsl.lang.EValidity.VALID;
import static org.modelmap.sample.field.SampleFieldIdInfo.accountId;
import static org.modelmap.sample.field.SampleFieldIdInfo.birthdate;
import static org.modelmap.sample.field.SampleFieldIdInfo.preferencesMail;
import static org.modelmap.sample.field.SampleFieldIdInfo.timezone;
import static org.modelmap.sample.model.EmailType.ADMINISTRATOR;
import static org.modelmap.sample.model.EmailType.PRIVATE;
import static org.modelmap.sample.model.Timezone.ETC_GMT;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;

import org.junit.Test;
import org.modelmap.core.FieldModel;
import org.modelmap.core.dsl.DSL;
import org.modelmap.core.dsl.lang.StepValidate;
import org.modelmap.sample.model.EmailType;
import org.modelmap.sample.model.SampleModels;

public class DSLSandboxTest {

    private FieldModel model = SampleModels.wrapper();

    @Test
    public void sample1() {
        StepValidate step = DSL.when(accountId().eq(1L)).validate().withMessage("incorrect account id");
        System.out.println(step.readable());
        assertThat(step.executeOn(model).validity()).isEqualTo(INVALID);
        assertThat(step.executeOn(model).message()).isEqualTo("incorrect account id");
    }

    @Test
    public void sample2() {
        StepValidate step = DSL.when(accountId().eq(1L)).validate();
        System.out.println(step.readable());
        assertThat(step.executeOn(model).validity()).isEqualTo(INVALID);
        assertThat(step.executeOn(model).message()).isEqualTo("account id equals 1");
    }

    @Test
    public void sample3() {
        StepValidate step = DSL.when(accountId().eq(1L).not()).validate().withMessage("incorrect account id");
        System.out.println(step.readable());
        assertThat(step.executeOn(model).validity()).isEqualTo(VALID);
        assertThat(step.executeOn(model).message()).isNull();
    }

    @Test
    public void sample4() {
        StepValidate step = DSL.when(birthdate().eq(LocalDate.of(1980, 8, 1))).validate()
                        .withMessage("you can't be born the August 1, 1980");
        System.out.println(step.readable());
        assertThat(step.executeOn(model).validity()).isEqualTo(VALID);
        assertThat(step.executeOn(model).message()).isNull();
    }

    @Test
    public void sample5() {
        StepValidate step = DSL.when(birthdate().between(LocalDate.of(1980, 1, 1), LocalDate.of(1980, 12, 31)))
                        .validate().withMessage("you can't be born in 1980");
        System.out.println(step.readable());
        assertThat(step.executeOn(model).validity()).isEqualTo(VALID);
        assertThat(step.executeOn(model).message()).isNull();
    }

    @Test
    public void sample6() {
        StepValidate step = DSL
                        .when(birthdate().between(LocalDate.of(1980, 1, 1), LocalDate.of(1980, 12, 31))
                                        .and(accountId().notEq(9L)).or(timezone().eq(ETC_GMT)))
                        .validate()
                        .withMessage("you can't be born in 1980 and have an ID different of 9 or having timezone "
                                        + "equals to ETC_GMT");
        System.out.println(step.readable());
        assertThat(step.executeOn(model).validity()).isEqualTo(VALID);
        assertThat(step.executeOn(model).message()).isNull();
    }

    @Test
    public void sample7() {
        StepValidate step = DSL.when(preferencesMail().eq(EnumSet.of(ADMINISTRATOR, PRIVATE)))
                        .validate()
                        .withMessage("les préférence d'email sont incorrects");
        System.out.println(step.readable());
        assertThat(step.executeOn(model).message()).isNull();
    }
}
