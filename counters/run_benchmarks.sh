#!/bin/bash
# Copyright 2016 M. Isuru Tharanga Chrishantha Perera
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# ----------------------------------------------------------------------------
# Run Benchmarks
# ----------------------------------------------------------------------------

ROOT_DIR=$(dirname "$0")
RESULTS_DIR=$ROOT_DIR/results
mkdir -p $RESULTS_DIR

counter_benchmark() {
echo Running benchmarks with thread group distribution: $1,$2
java -jar $ROOT_DIR/target/counters*.jar -wi 5 -i 10 -tg $1,$2 -rff "$RESULTS_DIR/results-$1-$2.csv" -rf csv -e Dirty
}

counter_benchmark 1 1
counter_benchmark 2 2
counter_benchmark 4 4
counter_benchmark 16 16
counter_benchmark 64 64
counter_benchmark 128 128

counter_benchmark 1 19
counter_benchmark 19 1
counter_benchmark 4 16
counter_benchmark 16 4
counter_benchmark 5 5
counter_benchmark 10 10
