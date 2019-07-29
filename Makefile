.PHONY: build run test

build:
	./gradlew clean build

run:
	./gradlew bootRun

test:
	./gradlew test
