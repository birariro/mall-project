# mall project

## tool
- IntelliJ
- Postman
- DBeaver
- Git

## architecture
<p>
    <img src="https://github.com/k4keye/echo-chat-server/blob/master/etc/architecture.png?raw=true"/>
</p>


## Environment
### Active Port

| name          | port       |
|---------------|------------|
| main server   | 8081       |
| order server  | 8085       |
| mail server   | 8090       |
| main mariadb  | 4445       |
| order mariadb | 4446       |
| redis         | 6379       |
| zookeeper     | 2181       |
| kafka         | 9092       |
| elasticsearch | 9200, 9300 |
| filebeat      | 5044       |
| kibana        | 5601       |



### Docker Compose setup
You will need to install Docker and docker-compose.

a Docker Compose setup is provided. It comes with the following databases:

- mariadb:10.8.3
- redis:7.0.4

and kafka :
- kafka:2.12-2.0.1
- zookeeper:3.4.6

and ELK :
- filebeat:7.10.2
- logstash:7.11.2-arm64
- elasticsearch:7.10.2-arm64
- kibana:7.10.2


To launch the database and kafka and ELK containers:

```
 $ docker-compose up -d
```

## Usage

### kafka
topic list
```
./opt/kafka_2.12-2.0.1/bin/kafka-topics.sh --list --zookeeper zookeeper:2181
```

topic create
```
./opt/kafka_2.12-2.0.1/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic message-topic
```

topic message send
```
./opt/kafka_2.12-2.0.1/bin/kafka-console-producer.sh --topic message-topic --broker-list localhost:9092
```

### redis
```
> redis-cli 
> info
> keys * 
> flushall 
```
## Make
| 카테고리 | 기능        | url                  |  |
| --- |-----------|----------------------| --- |
| 초기화 | 회원 생성     | :8081/init/member    | 완료 |
|  | 상품 생성     | :8081/init/product   | 완료 |
|  | 주문 생성     | :8081/init/order     | 완료 |
|  |           |                      |  |
| 로그인 | 로그인       | :8081/login          | 완료 |
|  | 회원가입      | :8081/login/join     | 완료 |
|  |           |                      |  |
| 회원 | 회원 전체 조회  | :8081/member         | 완료 |
|  | 회원 조회     | :8081/member/{id}    | 완료 |
|  | 회원 수정     | :8081/member/{id}    |  |
|  | 회원 삭제     | :8081/member/{id}    | 완료 |
|  |           |                      |  |
| 상품 | 상품 전체 조회  | :8081/product        | 완료 |
|  | 상품 조회     | :8081/product/{id}   | 완료 |
|  | 베스트 상품 조회 | :8085/statistics/best | 완료 |
|  | 상품 수정     | :8081/product/{id}   |  |
|  | 상품 삭제     | :8081/product/{id}   |  |
|  |           |                      |  |
| 주문 | 주문        | :8081/order          | 완료 |
|  | 주문 전체 조회  | :8081/order          | 완료 |
|  | 주문 조회     | :8081/order/{id}          | 완료 |


## ETC
http://localhost:8081/swagger-ui/index.html#/ </br>
http://localhost:5601/app/home#/
