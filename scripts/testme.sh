#!/bin/bash
set -e

cd $HOTV_HOME

echo "[+] Running tests with flags: $@"
mvn test "$@"

if [ $? -eq 0 ]; then
  echo "All tests passed!"
else
  echo "Some tests failed!"
  exit 1
fi
