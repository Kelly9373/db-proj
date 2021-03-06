
#!/usr/bin/env bash

# Setup the environment first.
echo "Begin to setup the environment..."

WORKING_DIR=`pwd`
CLASSPATH="$WORKING_DIR:$WORKING_DIR/classes:$WORKING_DIR/lib:."
COMPONENT="$WORKING_DIR"

export CLASSPATH
export COMPONENT

echo "Query environment setup successfully!"

# Begin to compile the project.
echo "Begin to build the project..."

# Creates the compilation output directory if not exists.
mkdir -p ${COMPONENT}/classes

javac -d ${COMPONENT}/classes ${COMPONENT}/lib/java_cup/runtime/*.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/qp/utils/*.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/qp/parser/*.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/qp/operators/*.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/qp/optimizer/*.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/QueryMain.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/ConvertTxtToTbl.java
javac -d ${COMPONENT}/classes ${COMPONENT}/src/RandomDB.java

echo "Project has been built successfully!"

