sbt "run $(find library/ -name '*.scala')"

javac -d . \
  -cp $(find ~/.ivy2/ -name scala-library-2.11.5.jar) \
  $(find library/ -name '*.java')

jar cMf lib.jar \
  $(find dotty/ -name '*.class') \
  $(find scala/ -name '*.class') \
  $(find scalaShadowing/ -name '*.class')
