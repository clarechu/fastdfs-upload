FROM openjdk:8-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories

RUN apk add --no-cache curl grep sed unzip bash nodejs nodejs-npm git curl vim wget openssh gradle maven mysql-client

# Set timezone to CST
ENV TZ=Asia/shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /usr/src

ENV LANG='zh_CN.UTF-8' LANGUAGE='zh_CN.UTF-8' LC_ALL='zh_CN.UTF-8' \
    PATH=${PATH}:${SONAR_RUNNER_HOME}/bin \
    MAVEN_NEXUS_REPO=http://nexus.devops.cloud2go.cn/repository/maven-public

WORKDIR /home

COPY ./.ssh /root/.ssh

COPY settings.xml /usr/share/java/maven-3/conf/settings.xml

COPY settings.xml /usr/share/maven/conf/settings.xml

COPY *.jar .

CMD ["bash", "-c", "java -jar *.jar"]