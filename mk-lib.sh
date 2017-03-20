set -eux

sbt "run $(find lib/ -name '*.scala')"

javac -d . \
  -cp $(find ~/.ivy2/ -name scala-library-2.11.5.jar) \
  $(find lib/ -name '*.java')

jar cMf lib.jar \
  $(find dotty/ -name '*.class') \
  $(find scala/ -name '*.class') \
  $(find scalaShadowing/ -name '*.class')
