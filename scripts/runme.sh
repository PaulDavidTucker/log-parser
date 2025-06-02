#!/bin/bash
set -e

echo "[+] Entering Directory Marked as Project Home: $LogParserHome..."
cd "$LogParserHome"

case "$1" in
--help)
  echo "Usage: $0 [options] [logfile_path]"
  echo "Options:"
  echo "  --help        Display this help message"
  echo "  --version     Display the application version"
  echo "  --jar         Run the prebuilt JAR file (with optional logfile path)"
  echo "                Or pipe input: cat logfile.log | $0 --jar"
  exit 0
  ;;

--version)
  echo "Log Parser Version 1.0"
  exit 0
  ;;

--jar)
  JAR_FILE="target/log-parser-1.0-SNAPSHOT.jar"

  if [ ! -f "$JAR_FILE" ]; then
    echo "[-] Error: JAR file $JAR_FILE not found. Run buildme.sh first."
    exit 1
  fi

  echo "[+] Executing prebuilt JAR: $JAR_FILE"

  # Shift to handle $2 as optional argument (path to logfile)
  shift

  if [ -p /dev/stdin ]; then
    echo "[+] Reading from stdin..."
    java -jar "$JAR_FILE"
  else
    if [ -z "$1" ]; then
      echo "[-] No input file provided and no stdin detected."
      echo "Usage: $0 --jar [logfile_path]"
      echo "Or pipe input: cat logfile.log | $0 --jar"
      exit 1
    fi
    echo "[+] Reading from file: $1"
    java -jar "$JAR_FILE" "$1"
  fi

  if [ $? -eq 0 ]; then
    echo "[+] JAR execution completed successfully."
    exit 0
  else
    echo "[-] Error: JAR execution failed. Check the output for details."
    exit 1
  fi
  ;;

"")
  echo "[+] No argument provided, proceeding with default behavior: compile & run"
  ;;

*)
  echo "[-] Error: Invalid option '$1'"
  echo "Usage: $0 [--help | --version | --jar]"
  exit 1
  ;;
esac

echo "[+] Cleaning and compiling..."
if ! mvn clean compile; then
  echo "[-] Error: Maven clean or compile failed. Check the output for details."
  exit 1
fi

echo "[+] Running application..."
if ! mvn exec:java -Dexec.mainClass="com.tucker.parser.Main"; then
  echo "[-] Error: Application failed to run. Check the main class or dependencies."
  exit 1
fi

echo "[+] Application ran successfully."
