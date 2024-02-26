start: build  docker-up

build:
	mvn clean install

docker-up:
	docker-compose up -d

stop:
	docker-compose down