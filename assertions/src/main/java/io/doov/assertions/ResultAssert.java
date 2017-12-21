/*
 * Copyright 2017 Courtanet
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.doov.assertions;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.assertj.core.api.AbstractAssert;

import io.doov.core.dsl.lang.Readable;
import io.doov.core.dsl.lang.Result;
import io.doov.core.dsl.meta.Metadata;

public class ResultAssert extends AbstractAssert<ResultAssert, Result> {

    ResultAssert(Result actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public ResultAssert isTrue() {
        if (!actual.isTrue()) {
            failWithMessage("Expected result to be true (invalidated nodes: " + getInvalidatedMetadata() + ")");
        }
        return this;
    }

    public ResultAssert isFalse() {
        if (!actual.isFalse()) {
            failWithMessage("Expected result to be false (invalidated nodes: " + getInvalidatedMetadata() + ")");
        }
        return this;
    }

    public ResultAssert hasMessage(String message) {
        if (!actual.getMessage().equals(message)) {
            failWithMessage("Expected result to have message + " + message + " but was " + actual.getMessage());
        }
        return this;
    }

    public ResultAssert hasMessageNull() {
        if (actual.getMessage() != null) {
            failWithMessage("Expected result to null message but was " + actual.getMessage());
        }
        return this;
    }

    public ResultAssert hasInvalidated(Metadata metadata) {
        if (!actual.getInvalidated().contains(metadata)) {
            failWithMessage("Expected result to have invalidated nodes + " + metadata.readable()
                            + " but was " + getInvalidatedMetadata());
        }
        return this;
    }

    public ResultAssert hasValidated(Metadata metadata) {
        if (!actual.getValidated().contains(metadata)) {
            failWithMessage("Expected result to have validated nodes + " + metadata.readable()
                            + " but was " + getValidatedMetadata());
        }
        return this;
    }

    public ResultAssert hasNoInvalidatedMetadata() {
        if (!actual.getInvalidated().isEmpty()) {
            failWithMessage("Expected result to have empty invalidated nodes but was " + getInvalidatedMetadata());
        }
        return this;
    }

    private List<String> getInvalidatedMetadata() {
        return actual.getInvalidated().stream().map(Readable::readable).collect(toList());
    }

    private List<String> getValidatedMetadata() {
        return actual.getValidated().stream().map(Readable::readable).collect(toList());
    }

}