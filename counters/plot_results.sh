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
# Plot Results
# ----------------------------------------------------------------------------

set -e

ROOT_DIR=$(dirname "$0")
RESULTS_DIR=$ROOT_DIR/results

for f in $RESULTS_DIR/*.csv; do
    output_file=${f%.*}.png
    filename=$(basename $f)
    results_title='Benchmark Results'$(echo $filename | sed 's/results\(.*\).csv/\1/')
    gnuplot -e "results_file='$f';output_file='$output_file';results_title='$results_title'" plot_results.gp
done
