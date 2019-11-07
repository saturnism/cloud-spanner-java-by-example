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

1. Configure [persistence.xml](src/main/resources/META-INF/persistence.xml)
   ```xml
   <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
   http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
                version="2.1">
       <persistence-unit name="spanner-example">
           <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
   
           <class>com.example.microprofile.Person</class>
   
           <properties>
               <property name="javax.persistence.jdbc.url" value="jdbc:cloudspanner:/projects/wise-coyote-827/instances/demo/databases/demo" />
               <property name="javax.persistence.jdbc.driver" value="com.google.cloud.spanner.jdbc.JdbcDriver" />
               <property name="hibernate.dialect" value="com.google.cloud.spanner.hibernate.SpannerDialect"/>
               <property name="show_sql" value="true"/>
           </properties>
       </persistence-unit>
   </persistence>
   ```
   
1. Create a producer so that `EntityManager can be injected:
   ```java
   @ApplicationScoped
   public class Producer {
   	@Produces EntityManager entityManager() {
   		return Persistence.createEntityManagerFactory("spanner-example")
   				.createEntityManager();
   	}
   
   	public void close(EntityManager entityManager) {
   		entityManager.close();
   	}
   }

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