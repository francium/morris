#!/bin/bash
if [ ! -d bin/se2aa4/morris ]; then
    mkdir -p bin/se2aa4/morris
fi
javac -d bin -cp src src/se2aa4/morris/*.java
cp src/se2aa4/morris/UI.fxml bin/se2aa4/morris/UI.fxml
