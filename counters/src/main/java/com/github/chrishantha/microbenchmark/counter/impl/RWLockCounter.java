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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockCounter implements Counter {
    private final ReadWriteLock rwlock;

    private final Lock rlock;
    private final Lock wlock;

    private long counter;

    public RWLockCounter(boolean fair) {
        rwlock = new ReentrantReadWriteLock(fair);
        rlock = rwlock.readLock();
        wlock = rwlock.writeLock();
    }

    public long getCount() {
        try {
            rlock.lock();
            return counter;
        } finally {
            rlock.unlock();
        }
    }

    public void increment() {
        try {
            wlock.lock();
            ++counter;
        } finally {
            wlock.unlock();
        }
    }
}
