/*
 * Copyright (c)  2010 Ghais Issa and others. All rights reserved.
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
 *
 * Contributors :
 *     ...
 */

package com.convert.reservoir;

import java.io.Serializable;
import java.util.Collection;

/**
 * The reservoir interface.
 * @author Ghais Issa <ghais.issa@gmail.com>
 */
public interface Reservoir {

    /**
     * The reservoir default size.
     */
    static final int DEFAULT_SIZE = 1000;

    /**
     * Optionally insert an object in the reservoir.
     * <p/>
     * The reservoir might end up ignoring the object based on the reservoir implementation.
     * If any element is already in the reservoir then we perform an update operation and
     * ignore the reservoir algorithm.
     * @param t the object presented to the reservoir
     */
    void put(Serializable... t);

    /**
     * Get all the elements in the reservoir.
     * @return the members of the reservoir
     */
    Collection<Serializable> get();

    /**
     * Get all the members of the reservoir that match a particular @Link{Predicate}.
     * @param predicate the predicate to match the members against
     * @return All the members that match the given predicate
     */
    Collection<Serializable> get(Predicate<Serializable> predicate);

    /**
     * Gets the size of the reservoir.
     * @return the size of the reservoir
     */
    int getSize();

    String getName();
}
