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
# gnuplot script
# ----------------------------------------------------------------------------

# Labels
set title results_title
set ylabel 'Operations per second'
set xlabel 'Benchmark'
set xtics nomirror rotate by -45

# Ranges
#set autoscale

# Input
set datafile separator ','

# Output
set terminal pngcairo enhanced font "Verdana,9"
set output output_file
set grid
set key off
set boxwidth 0.6 relative

# box style
set style line 1 lc rgb "skyblue" lt 1
set style fill solid

# remove top and right borders
set style line 2 lc rgb "black" lt 1
set border 3 back ls 2
set tics nomirror

plot results_file every 3::1 using 0:5:xticlabels(stringcolumn(1)[64:90]) with boxes ls 1 title "ops/s",\
 '' every 3::1 using 0:5:(sprintf("%d",$5)) with labels rotate left