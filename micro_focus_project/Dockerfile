FROM openjdk:15-jdk AS app-build
ENV GRADLE_OPTS -Dorg.dradle.daemon=false
COPY . /build
WORKDIR /build
RUN ./gradlew build