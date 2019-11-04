按条件筛选出进程，杀死对应进程
ps -ef | grep smartserver | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartzuul | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartweb | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartproducer | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep smartfront | grep -v grep | awk '{print $2}' | xargs kill -9


nohup mvn spring-boot:run -Dspring-boot.run.profiles=dev &


## 启动服务 ##

/usr/local/nginx/nginx -c /data/nginx-1.16.1/conf/nginx.conf
/usr/local/bin/redis-server /etc/redis/6379.conf


