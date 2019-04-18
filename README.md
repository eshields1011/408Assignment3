# 408Assignment3
Java program that uses a Python script to generate fake data to be inserted into an online database.

External repositories used:

Java:

  MYSQL: mysql:mysql-connector-java:8.0.11

  CSV: org.apache.commons:commons-csv:1.6

Python:

  Faker: version 1.0.5
  
Additional instructions:

For the command line for the python script, the first argument is the number of animals to be generated, and the second is the name of the file.

i.e. running this on the command line:

python fakerfakerdatamaker.py 420 animals.csv

will create 420 fake animal records and output them to the file animals.csv

* Make sure that the shelters.csv file is in your current directory before running the Java program.
