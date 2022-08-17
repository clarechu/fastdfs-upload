#!/usr/bin/env bash

echo "mvn clean install"

mvn clean install

export HOST=docker.io
export TAG=v0.0.5
docker build -t ${HOST}/clarechu/fastdfs-upload:${TAG} .

docker push ${HOST}/cloudos-dev/fastdfs-upload:${TAG}

rm -rf target/
