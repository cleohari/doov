/*
 * Copyright 2017 Courtanet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package io.doov.core;

import io.doov.core.dsl.meta.Readable;

/**
 * Properties of a {@code FieldId}, generated from the model java bean
 */
public interface FieldInfo extends Readable {

    /**
     * @return the referenced {@code FieldId}
     */
    FieldId id();

    /**
     * @return the other {@code FieldId} mapped on the same property
     */
    FieldId[] siblings();

    /**
     * @return the {@code FieldId} type
     */
    Class<?> type();

    /**
     * @return the {@code FieldId} type parameters
     */
    Class<?>[] genericTypes();

}