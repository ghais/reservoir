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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Default reservoir implementation.
 * @author Ghais Issa
 */
public class ReservoirImpl implements Reservoir {

    private static final Random RANDOM = new Random();

    private static final int DEFAULT_SIZE = 1000;


    private final String name;
    private long skipCount;
    private int currentCount;
    final private int size;

    final private GammaFunction skipFunction;

    private List<Serializable> items = new ArrayList<Serializable>(DEFAULT_SIZE);

    public ReservoirImpl(String name, int size, GammaFunction skipFunction) {
        super();
        this.name = name;
        this.size = size;
        this.currentCount = 0;
        this.skipCount = 0;
        this.skipFunction = skipFunction;
    }

    @Override
    synchronized public void put(Serializable... ts) {
        int i = 0;
        while (i < ts.length && this.size != this.items.size()) {
            this.items.add(ts[i++]);
            this.currentCount++;
        }

        while (i < ts.length) {
            if (this.skipCount > 0) {
                this.skipCount--;
            } else {
                this.items.set(RANDOM.nextInt(this.size), ts[i]);
                this.skipCount = this.skipFunction.apply(this.currentCount);
            }
            i++;
            this.currentCount++;
        }
    }

    @Override
    synchronized public Collection<Serializable> get() {
        return Collections.unmodifiableCollection(this.items);
    }

    @Override
    synchronized public Collection<Serializable> get(Predicate<Serializable> predicate) {
        Collection<Serializable> result = new ArrayList<Serializable>(this.size);
        for (Serializable item : this.items) {
            if (predicate.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    synchronized public int getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
