sbt "run $(find library/ -name '*.scala' -or -name '*.java')"

jar cMf lib.jar \
  $(find dotty/ -name *.class) \
  $(find scala/ -name *.class) \
  $(find scalaShadowing/ -name *.class)
