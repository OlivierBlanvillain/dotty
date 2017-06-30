#!/bin/sh

set -x

for commit in 18b6cd7 aaecf81 20e5112 622d519 1e0c4a9 2ad686c 7f7cad1 aeb1d0f 9b1f2b5 26c6174 098a42c 1ce9091 fff06ef 17611d4 13ff46e 1058ae5 4dc2691 6841bc4 7c0d266; do

  git checkout "$commit"
  msg=$(git log --format=%B -n 1)

  version="0.2.0-bin-20170629-$commit-NIGHTLY"
  needsPublish=$(find ~/.ivy2/ -name "$version" | wc -l)
  if [ needsPublish -eq 0 ]; then
    NIGHTLYBUILD=yes sbt ";clean ;set bootstrapOptimised in ThisBuild := true; dotty-bootstrapped/publishLocal"
  fi

  (
    cd /home/olivier/workspace/compiler-benchmark
    sbt ";++$version hot -psource=vector -o hot-vector-$msg.log"
  )

done
