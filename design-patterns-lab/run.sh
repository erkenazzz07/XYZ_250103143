#!/bin/sh
# Compile everything and run all five task runners plus the verification tests.
set -e
rm -rf out && mkdir out
javac -d out $(find src test -name '*.java')
for i in 1 2 3 4 5; do
  echo "================ TASK $i ================"
  java -cp out task$i.Task${i}Runner
  echo
done
echo "================ TESTS ================"
java -cp out tests.AllTests
