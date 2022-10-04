docker run --rm \
    --name tomcat \
    -p 8080:8080 \
    --network demo_network \
    -v "C:\TMS_Java\Java_EE\lesson_22\target\Aliaksei.Fezhanka-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/servlet.war" \
    tomcat:9.0.53-jdk8


docker build -t "servlet:latest" .
docker run --rm --name custom-tomcat -p 8080:8080 servlet:latest


docker run --rm --name tomcat -p 8080:8080 --network demo_network -v "C:\TMS_Java\Java_EE\lesson_22\target\Aliaksei.Fezhanka-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/servlet.war" tomcat:9.0.53-jdk8
docker run --network demo_network --rm curlimages/curl:7.85.0 -X POST http://tomcat:8080/servlet/test