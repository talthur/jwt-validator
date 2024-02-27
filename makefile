start: build docker-build docker-up

build:
	mvn clean install

docker-up:
	docker-compose up -d

docker-build:
	docker build -t jwt-validator .

tag-push:
	docker tag jwt-validator talthur/jwt-validator:latest
	docker push talthur/jwt-validator:latest

stop:
	docker-compose down