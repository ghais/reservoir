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

import java.util.Collection;

/**
 * The Reservoir Manager interface. All reservoir managers must provide at least those features.
 * @author Ghais Issa
 */
public interface Manager {
    /**
     * Gets the reservoir names.
     * @return the list of reservoir names
     */
    Collection<String> getReservoirNames();

    /**
     * Create and add a reservoir to the set of reservoirs.
     * @param name the name of the reservoir
     * @param size the reservoir size
     * @throws ReservoirExistsException if the reservoir already exists
     * @throws ReservoirException if there was an error creating the reservoir
     */
    void addReservoir(String name, int size) throws ReservoirExistsException, ReservoirException;

    /**
     * Add a reservoir to the set of reservoirs.
     * @param reservoir the reservoir to add
     * @throws ReservoirExistsException if the reservoir already exists
     * @throws ReservoirException if there was an error creating the reservoir
     */
    void addReservoir(Reservoir reservoir) throws ReservoirExistsException, ReservoirException;

    /**
     * Get the reservoir with the given name.
     * @param name the reservoir name.
     * @return the reservoir with the given name
     * @throws ReservoirNotFoundException if no reservoir exists with that name
     */
    Reservoir getReservoir(String name) throws ReservoirNotFoundException;

    /**
     * Check if a reservoir exists.
     * @param name the name of the reservoir
     * @return true if the reservoir exits false otherwise
     */
    boolean reservoirExists(String name);

    /**
     * Removes a reservoir from the list of reservoirs.
     * @param name the name of the reservoir to remove.
     * @throws ReservoirNotFoundException if the reservoir doesn't exist
     */
    void removeReservoir(String name) throws ReservoirNotFoundException;

    /**
     * provide a system shutdown hook.
     */
    void shutDown();
}
