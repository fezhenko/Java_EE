docker run --rm `
    --name tomcat `
    --network demo_network `
    -p 8080:8080 `
    -v "C:/TMS_Java/Java_EE/lesson_24/target/login-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/servlet.war" `
    tomcat:9.0.53-jdk17-corretto



docker run --rm `
    --name demo-postgres `
    --network demo_network `
    -e POSTGRES_USER=postgres `
    -e POSTGRES_PASSWORD=postgres `
    -p 15432:5432 `
    -v "C:/TMS_Java/Java_EE/lesson_24/local-env/Postgres/init.sql:/docker-entrypoint-initdb.d/1-init.sql" `
    postgres:13.4-alpine
