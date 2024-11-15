#Script for Windowns
javac -cp "./target/dependency/gson-2.11.0.jar;./src/main/java" -d ./ ./src/main/java/Main.java
java -cp ".;./target/dependency/gson-2.11.0.jar" Main "$1" "$2"

#Script for Linux
#javac -cp "./target/dependency/gson-2.11.0.jar:./src/main/java" -d ./ ./src/main/java/Main.java
#java -cp ".:./target/dependency/gson-2.11.0.jar" Main "$1" "$2"

#
#Command line to run "./runner hotel_id_4,hotel_id_5 destination_id_1,destination_id_2"
#
