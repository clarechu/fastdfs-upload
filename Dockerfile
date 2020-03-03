FROM openjdk:8-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories

RUN apk add --no-cache curl grep sed bash git curl vim wget openssh maven

# Set timezone to CST
ENV TZ=Asia/shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /usr/src

ENV LANG='zh_CN.UTF-8' LANGUAGE='zh_CN.UTF-8' LC_ALL='zh_CN.UTF-8' \
    PATH=${PATH}:${SONAR_RUNNER_HOME}/bin \
    MAVEN_NEXUS_REPO=http://nexus.devops.cloud2go.cn/repository/maven-public

WORKDIR /home

COPY ./target/*.jar .

CMD ["bash", "-c", "java -jar *.jar"]