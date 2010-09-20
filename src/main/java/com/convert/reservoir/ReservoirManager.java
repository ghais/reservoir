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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Default reservoir manager.
 * @author Ghais Issa
 */
public class ReservoirManager implements Manager {

    private ConcurrentMap<String, Reservoir> reservoirs = new ConcurrentHashMap<String, Reservoir>();

    @Override
    public Collection<String> getReservoirNames() {
        return this.reservoirs.keySet();
    }

    public void addReservoir(String name, int maxSize, GammaFunction skipFunction)
            throws ReservoirExistsException, ReservoirException {
        if (reservoirExists(name)) {
            throw new ReservoirExistsException("Reservoir with name " + name + " already exists");
        }
        Reservoir r = new ReservoirImpl(name, maxSize, skipFunction);
        this.reservoirs.putIfAbsent(name, r);
    }

    @Override
    public void addReservoir(String name, int size) throws ReservoirExistsException, ReservoirException {
        addReservoir(name, size, new Z(size));
    }

    @Override
    public void addReservoir(Reservoir reservoir) throws ReservoirExistsException, ReservoirException {
        addReservoir(reservoir.getName(), Reservoir.DEFAULT_SIZE, new Z(Reservoir.DEFAULT_SIZE));
    }

    @Override
    public Reservoir getReservoir(String name) throws ReservoirNotFoundException {
        Reservoir r = this.reservoirs.get(name);
        if (r == null) {
            throw new ReservoirNotFoundException("Couldn't find a reservoir with name: " + name);
        }
        return r;
    }

    @Override
    public boolean reservoirExists(String name) {
        return this.reservoirs.containsKey(name);
    }

    @Override
    public void removeReservoir(String name) throws ReservoirNotFoundException {
        if (!reservoirExists(name)) {
            throw new ReservoirNotFoundException("couldn't find a reservoir with name: " + name);
        }
        this.reservoirs.remove(name);
    }

    @Override
    public void shutDown() {
        //do nothing
    }
}

