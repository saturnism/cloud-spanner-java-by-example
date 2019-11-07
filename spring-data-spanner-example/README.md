## Configuration

1. Use Spring Cloud BOM that contains Spring Cloud GCP:
   ```
   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-dependencies</artifactId>
               <version>${spring-cloud.version}</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
       </dependencies>
   </dependencyManagement>
   ```

1. Add Spring Cloud Data for Cloud Spanner
   ```
   <dependency>
       <groupId>org.springframework.cloud</groupId>
	   <artifactId>spring-cloud-gcp-starter</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.cloud</groupId>
	   <artifactId>spring-cloud-gcp-starter-data-spanner</artifactId>
   </dependency>
   ```

1. Configure [application.properties](src/main/resources/applications.properties)
   ```
   spring.cloud.gcp.spanner.instance-id=demo
   spring.cloud.gcp.spanner.database=demo
   ```

## Run

* From Plugin:
  ```sh
  mvn spring-boot:run
  ```

* From JAR:
  ```
  mvn package
  java -jar target/....jar
  ```