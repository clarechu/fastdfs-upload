#!/usr/bin/env bash

echo "mvn clean install"

mvn clean install

export HOST=harbor.cloud2go.cn
export TAG=RC4.24.0
docker build -t ${HOST}/cloudos-dev/image-upload:${TAG} .

docker login -p Harbor12345 -u admin ${HOST}

docker push ${HOST}/cloudos-dev/image-upload:${TAG}

rm -rf target/
