#!/bin/bash
set -e
cd $LogParserHome

echo "[+] Cleaning and building..."
mvn clean compile

mvn package

if [ $? -eq 0 ]; then
    echo "[+] Build successful. JAR file created in target/ directory."
else
    echo "[-] Build failed. Check Maven output for details."
    exit 1
fi
