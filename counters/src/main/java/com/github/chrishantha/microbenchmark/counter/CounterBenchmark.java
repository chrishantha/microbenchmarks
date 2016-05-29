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
package com.github.chrishantha.microbenchmark.counter;

import com.github.chrishantha.microbenchmark.counter.impl.AdderCounter;
import com.github.chrishantha.microbenchmark.counter.impl.AtomicCounter;
import com.github.chrishantha.microbenchmark.counter.impl.DirtyCounter;
import com.github.chrishantha.microbenchmark.counter.impl.LockCounter;
import com.github.chrishantha.microbenchmark.counter.impl.OptimisticStampedCounter;
import com.github.chrishantha.microbenchmark.counter.impl.RWLockCounter;
import com.github.chrishantha.microbenchmark.counter.impl.SemaphoreCounter;
import com.github.chrishantha.microbenchmark.counter.impl.StampedCounter;
import com.github.chrishantha.microbenchmark.counter.impl.SynchronizedCounter;
import com.github.chrishantha.microbenchmark.counter.impl.SynchronizedMethodCounter;
import com.github.chrishantha.microbenchmark.counter.impl.VolatileCounter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
public class CounterBenchmark {

    @Benchmark
    @Group("Adder")
    @GroupThreads(60)
    public void incAdder(AdderCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("Adder")
    @GroupThreads(40)
    public long getAdder(AdderCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("Atomic")
    @GroupThreads(60)
    public void incAtomic(AtomicCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("Atomic")
    @GroupThreads(40)
    public long getAtomic(AtomicCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("Dirty")
    @GroupThreads(60)
    public void incDirty(DirtyCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("Dirty")
    @GroupThreads(40)
    public long getDirty(DirtyCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("LockFair")
    @GroupThreads(60)
    public void incFairLock(FairLockCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("LockFair")
    @GroupThreads(40)
    public long getFairLock(FairLockCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("LockNonFair")
    @GroupThreads(60)
    public void incNonFairLock(NonFairLockCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("LockNonFair")
    @GroupThreads(40)
    public long getNonFairLock(NonFairLockCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("RWLockFair")
    @GroupThreads(60)
    public void incFairRWLock(FairRWLockCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("RWLockFair")
    @GroupThreads(40)
    public long getFairRWLock(FairRWLockCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("RWLockNonFair")
    @GroupThreads(60)
    public void incNonFairRWLock(NonFairRWLockCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("RWLockNonFair")
    @GroupThreads(40)
    public long getNonFairRWLock(NonFairRWLockCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("Synchronized")
    @GroupThreads(60)
    public void incSynchronized(SynchronizedCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("Synchronized")
    @GroupThreads(40)
    public long getSynchronized(SynchronizedCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("SynchronizedMethod")
    @GroupThreads(60)
    public void incSynchronizedMethod(SynchronizedMethodCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("SynchronizedMethod")
    @GroupThreads(40)
    public long getSynchronizedMethod(SynchronizedMethodCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("Volatile")
    @GroupThreads(60)
    public void incVolatile(VolatileCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("Volatile")
    @GroupThreads(40)
    public long getVolatile(VolatileCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("StampedOptimistic")
    @GroupThreads(60)
    public void incOptimisticStamped(OptimisticStampedCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("StampedOptimistic")
    @GroupThreads(40)
    public long getOptimisticStamped(OptimisticStampedCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("Stamped")
    @GroupThreads(60)
    public void incStamped(StampedCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("Stamped")
    @GroupThreads(40)
    public long getStamped(StampedCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("SemaphoreFair")
    @GroupThreads(60)
    public void incFairSemaphore(FairSemaphoreCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("SemaphoreFair")
    @GroupThreads(40)
    public long getFairSemaphore(FairSemaphoreCounterState state) {
        return state.counter.getCount();
    }

    @Benchmark
    @Group("SemaphoreNonFair")
    @GroupThreads(60)
    public void incNonFairSemaphore(NonFairSemaphoreCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("SemaphoreNonFair")
    @GroupThreads(40)
    public long getNonFairSemaphore(NonFairSemaphoreCounterState state) {
        return state.counter.getCount();
    }

    @State(Scope.Group)
    public static class AdderCounterState {
        Counter counter = new AdderCounter();
    }

    @State(Scope.Group)
    public static class AtomicCounterState {
        Counter counter = new AtomicCounter();
    }

    @State(Scope.Group)
    public static class DirtyCounterState {
        Counter counter = new DirtyCounter();
    }

    @State(Scope.Group)
    public static class FairLockCounterState {
        Counter counter = new LockCounter(true);
    }

    @State(Scope.Group)
    public static class NonFairLockCounterState {
        Counter counter = new LockCounter(false);
    }

    @State(Scope.Group)
    public static class FairRWLockCounterState {
        Counter counter = new RWLockCounter(true);
    }

    @State(Scope.Group)
    public static class NonFairRWLockCounterState {
        Counter counter = new RWLockCounter(false);
    }

    @State(Scope.Group)
    public static class SynchronizedCounterState {
        Counter counter = new SynchronizedCounter();
    }

    @State(Scope.Group)
    public static class SynchronizedMethodCounterState {
        Counter counter = new SynchronizedMethodCounter();
    }

    @State(Scope.Group)
    public static class VolatileCounterState {
        Counter counter = new VolatileCounter();
    }

    @State(Scope.Group)
    public static class OptimisticStampedCounterState {
        Counter counter = new OptimisticStampedCounter();
    }

    @State(Scope.Group)
    public static class StampedCounterState {
        Counter counter = new StampedCounter();
    }

    @State(Scope.Group)
    public static class FairSemaphoreCounterState {
        Counter counter = new SemaphoreCounter(true);
    }

    @State(Scope.Group)
    public static class NonFairSemaphoreCounterState {
        Counter counter = new SemaphoreCounter(false);
    }


}
