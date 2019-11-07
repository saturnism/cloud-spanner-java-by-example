## Configuration

1. Add Cloud Spanner JDBC driver and Hibernate dialect
   ```xml
   <dependency>
       <groupId>com.google.cloud</groupId>
       <artifactId>google-cloud-spanner-hibernate-dialect</artifactId>
       <version>0.1.0</version>
   </dependency>
   <dependency>
       <groupId>com.google.cloud</groupId>
       <artifactId>google-cloud-spanner-jdbc</artifactId>
       <version>1.7.0</version>
   </dependency>
   ```

1. Configure [application.properties](src/main/resources/applications.properties)
   ```
   quarkus.datasource.url=jdbc:cloudspanner:/projects/YOUR_PROJECT_ID/instances/demo/databases/demo
   quarkus.datasource.driver=com.google.cloud.spanner.jdbc.JdbcDriver
   quarkus.hibernate-orm.dialect=com.google.cloud.spanner.hibernate.SpannerDialect
   ```

## Run

* From Plugin:
  ```sh
  mvn clean quarkus:dev
  ```

* From JAR:
  ```
  mvn package
  java -jar target/...-runner.jar
  ```