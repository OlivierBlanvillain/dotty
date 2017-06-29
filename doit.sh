#!/bin/sh

set -eux

for commit in 07cb77a; do

  git checkout "$commit"

  sbt ";clean ;set bootstrapOptimised in ThisBuild := true; dotty-bootstrapped/publishLocal"

  (
    cd /home/olivier/workspace/compiler-benchmark
    sbt ";++0.2.0-bin-20170629-$commit-NIGHTLY cold -psource=vector -o cold-vector-$commit.log" > $commit.sbtlog
  )

done
