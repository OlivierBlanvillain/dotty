#!/bin/sh

set -x

for commit in 18b6cd70 aaecf81f 20e51125 622d519a 1e0c4a98 2ad686c9 7f7cad17 aeb1d0fd 9b1f2b5d 26c61740 098a42c8 1ce90917 fff06ef6 17611d46 13ff46e7 1058ae55 4dc26915 6841bc42 7c0d2668; do

  git checkout "$commit"
  msg=$(git log --format=%B -n 1)

  sbt ";clean ;set bootstrapOptimised in ThisBuild := true; dotty-bootstrapped/publishLocal"

  (
    cd /home/olivier/workspace/compiler-benchmark
    sbt ";++0.2.0-bin-20170629-$commit-NIGHTLY cold -psource=vector -o cold-vector-$msg.log" > $msg.sbtlog
  )

done
