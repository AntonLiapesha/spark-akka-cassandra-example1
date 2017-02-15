# spark-akka-cassandra-example1
Spark app for data processing and akka rest endpoing

Required:

docker toolbox

cqlsh

docker toolbox

local spark spark-2.1.0-bin-hadoop2.7

Steps:

1. get docker-machine ip (in current code it's 192.168.99.100)
2. sbt clean compile
3. sbt "project spark-app" assembly:assembly
4. docker-compose up -d
5. cqlsh 192.168.99.100 9042 --cqlversion="3.4.4" -u cassandra -p password123 < cassandra-init.cql
6. sh ~/libs/spark-2.1.0-bin-hadoop2.7/bin/spark-submit --class com.aliapesha.testtask.spark.Main \
                                                           ./apps/spark-app/target/scala-2.11/testTask.jar \
                                                           http://archive.ics.uci.edu/ml/machine-learning-databases/housing/housing.data
7. sbt akka-app/run
8. curl http://localhost:9000/stat/sample

Next steps:
1. Use dockerized spark
2. Put spark and kafka into compose config
