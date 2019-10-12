FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/gank-0.0.1-SNAPSHOT-standalone.jar /gank/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/gank/app.jar"]
