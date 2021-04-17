FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE
ARG JAVA_ARGS="--spring.config.location=/usr/local/arm/conf/spring-conf/"
ARG EXTRA_JAVA_ARGS=""
ENV JVM_ARGS="-XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=75.0 -XX:+PrintCommandLineFlags -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/java/java_heapdump_pid%p.hprof"
COPY target/*.jar app.jar
CMD java ${JVM_ARGS}  ${EXTRA_JAVA_ARGS} ${JVM_ARGS} -jar /app.jar