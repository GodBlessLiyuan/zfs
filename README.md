按条件筛选出进程，杀死对应进程
ps -ef | grep smartserver | grep -v grep | awk '{print $2}' | xargs kill -9 
ps -ef | grep smartzuul | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartweb | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartproducer | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartfront | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartconsumer | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smarteureka | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartpay | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep rpa/smart | grep -v grep | awk '{print $2}' | xargs kill -9



rm -rf /data/project/bin/smartzipkin-1.0.jar
cp /data/local/repo/com/rpa/smartzipkin/1.0/smartzipkin-1.0.jar /data/project/bin
ps -ef | grep smartzipkin | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smarteureka-1.0.jar --spring.profiles.active=dev >/data/project/logs/smarteureka.log 2>&1 &



rm -rf /data/project/bin/smarteureka-1.0.jar
cp /data/local/repo/com/rpa/smarteureka/1.0/smarteureka-1.0.jar /data/project/bin
ps -ef | grep smarteureka | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smarteureka-1.0.jar --spring.profiles.active=dev >/data/project/logs/smarteureka.log 2>&1 &



rm -rf /data/project/bin/smartcache-1.0.jar
cp /data/local/repo/com/rpa/smartcache/1.0/smartcache-1.0.jar /data/project/bin
ps -ef | grep smartcache | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartcache-1.0.jar --spring.profiles.active=dev >/data/project/logs/smartcache.log 2>&1 &

rm -rf /data/project/bin/smartweb-1.0.1.jar
cp /data/local/repo/com/rpa/smartweb/1.0.1/smartweb-1.0.1.jar /data/project/bin
ps -ef | grep smartweb | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartweb-1.0.1.jar --spring.profiles.active=dev >/data/project/logs/smartweb.log 2>&1 &


rm -rf /data/project/bin/smartserver-1.0.1.jar
cp /data/local/repo/com/rpa/smartserver/1.0.1/smartserver-1.0.1.jar /data/project/bin
ps -ef | grep smartserver | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartserver-1.0.1.jar --spring.profiles.active=dev >/data/project/logs/smartserver.log 2>&1 &


rm -rf /data/project/bin/smartproducer-1.0.jar
cp /data/local/repo/com/rpa/smartproducer/1.0/smartproducer-1.0.jar /data/project/bin
ps -ef | grep smartproducer | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartproducer-1.0.jar --spring.profiles.active=dev >/data/project/logs/smartproducer.log 2>&1 &

rm -rf /data/project/bin/smartconsumer-1.0.jar
cp /data/local/repo/com/rpa/smartconsumer/1.0/smartconsumer-1.0.jar /data/project/bin
ps -ef | grep smartconsumer | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartconsumer-1.0.jar  --spring.profiles.active=dev >/data/project/logs/smartconsumer.log 2>&1 &

rm -rf /data/project/bin/smartfront-1.0.jar
cp /data/local/repo/com/rpa/smartfront/1.0/smartfront-1.0.jar /data/project/bin
ps -ef | grep smartfront | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartfront-1.0.jar  --spring.profiles.active=dev >/data/project/logs/smartfront.log 2>&1 &


rm -rf /data/project/bin/smartpay-1.0.jar
cp /data/local/repo/com/rpa/smartpay/1.0/smartpay-1.0.jar /data/project/bin
ps -ef | grep smartpay | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartpay-1.0.jar --spring.profiles.active=dev >/data/project/logs/smartpay.log 2>&1 &

rm -rf /data/project/bin/smartzuul-1.0.jar
cp /data/local/repo/com/rpa/smartzuul/1.0/smartzuul-1.0.jar /data/project/bin
ps -ef | grep smartzuul | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/zuulib -jar /data/project/bin/smartzuul-1.0.jar --spring.profiles.active=dev >/data/project/logs/smartzuul.log 2>&1 &


rm -rf /data/project/bin/smartkafka-1.0.jar
cp /data/local/repo/com/rpa/smartkafka/1.0/smartkafka-1.0.jar /data/project/bin
ps -ef | grep smartkafka | grep -v grep | awk '{print $2}' | xargs kill -9 | sleep 1
nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar /data/project/bin/smartkafka-1.0.jar --spring.profiles.active=dev >/data/project/logs/smartkafka.log 2>&1 &


nohup mvn spring-boot:run -Dspring-boot.run.profiles=dev &


## 启动服务 ##

/usr/local/nginx/nginx -c /data/nginx-1.16.1/conf/nginx.conf
/usr/local/bin/redis-server /etc/redis/6379.conf

./bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
./bin/kafka-server-start.sh -daemon config/server.properties


