/*
 * Copyright 2016 M. Isuru Tharanga Chrishantha Perera
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
package com.github.chrishantha.microbenchmark.counter.impl;

import com.github.chrishantha.microbenchmark.counter.Counter;

import java.util.concurrent.locks.StampedLock;

public class OptimisticStampedCounter implements Counter {

    private StampedLock rwlock = new StampedLock();

    private long counter;

    public long getCount() {
        long stamp = rwlock.tryOptimisticRead();
        long currentCount;
        if (rwlock.validate(stamp)) {
            currentCount = counter;
        } else {
            stamp = rwlock.readLock();
            try {
                currentCount = counter;
            } finally {
                rwlock.unlockRead(stamp);
            }
        }
        return currentCount;
    }

    public void increment() {
        long stamp = rwlock.writeLock();
        try {
            ++counter;
        } finally {
            rwlock.unlockWrite(stamp);
        }
    }

}
