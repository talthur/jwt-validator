start: build docker docker-up

build:
	mvn clean install

docker-up:
	docker-compose up -d

docker:
	docker build -t jwt-validator .

stop:
	docker-compose down