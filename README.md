# Introduction

This repository has a collection of Cloud Spanner examples in Java using different frameworks.

## Options

There are multiple ways to connect to Cloud Spanner. The choices depends on the technology
stack you prefer, or what your application is already using.

| Option | When to use? |
|---     |---           |
| [Spring Data Spanner](https://cloud.spring.io/spring-cloud-static/spring-cloud-gcp/1.1.3.RELEASE/multi/multi__spring_data_cloud_spanner.html) | Idiomatic Spring Data layer to Cloud Spanner. Consider this if you start with a new Spring Boot application, and need the most idiomatic, lightweight ORM to access Cloud Spanner features. |
| [Hibernate](https://github.com/GoogleCloudPlatform/google-cloud-spanner-hibernate) | Hibernate dialect for Cloud Spanner. Consider this if you want to use Hibernate, or already using Hibernate. You can use Cloud Spanner with Hibernate with Spring Data JPA, Microprofile, Quarkus, etc. |
| [JDBC](https://github.com/googleapis/google-cloud-java/tree/master/google-cloud-clients/google-cloud-contrib/google-cloud-spanner-jdbc) | Open source JDBC driver for Cloud Spanner. Consider this if you want to use Cloud Spanner with pure JDBC without any ORM. You can use this with Spring JDBC Template, MyBatis, etc. |
| [R2DBC](https://github.com/GoogleCloudPlatform/cloud-spanner-r2dbc)| Reactive database driver for Cloud Spanner. Consider this if you are building a fully reactive application using Reactive Stream, Reactor, RxJava, Akka Streams, etc. |
| [Cloud Spanner Client Library](https://github.com/googleapis/google-cloud-java/tree/master/google-cloud-clients/google-cloud-spanner) | Raw API access that may have raw features that any other options does not offer. Consider this as a last resort. | 

## Setup Cloud Spanner for Examples

All of the examples use the same Cloud Spanner database / schema.

1. Create a Cloud Spanner instance

   ```sh
   $ gcloud services enable spanner.googleapis.com
   $ gcloud spanner instances create demo --config=regional-us-central1 \
       --nodes=1 --description="Spanner demo"
   ```

1. Configure the Schema

   ```sql
   CREATE TABLE person (
     id STRING(36) NOT NULL,
     name STRING(MAX),
   ) PRIMARY KEY (id)
   ```
   
   or, run this through a `ddl` file:
   
   ```sh
   cat <<EOF > schema.ddl
   CREATE TABLE person (
     id STRING(36) NOT NULL,
     name STRING(MAX),
   ) PRIMARY KEY (id)
   EOF
   ```
   
   and import the file:
   
   ```
   gcloud spanner databases ddl update demo  \
     --instance=demo --ddl="$(cat schema.ddl)"
   ```

1. Make sure you have the Service Account credentials configured.

   ```sh
   # Create a service account
   gcloud iam service-accounts create spanner-demo
   
   # Get the current Project ID
   export GCP_PROJECT=$(gcloud config list --format 'value(core.project)')
   
   # Bind IAM permission
   gcloud projects add-iam-policy-binding $GCP_PROJECT \
     --member serviceAccount:spanner-demo@$GCP_PROJECT.iam.gserviceaccount.com \
     --role roles/spanner.databaseUser
   
   # Create the service account key file
   gcloud iam service-accounts keys create \
       --iam-account spanner-demo@${GCP_PROJECT}.iam.gserviceaccount.com \
       $HOME/spanner-demo.json
   
   # Configure Google Application Credentials environmental variable
   export GOOGLE_APPLICATION_CREDENTIALS=$HOME/spanner-demo.json
   ```

## Examples

Please note that every example configures the Cloud Spanner instance name differently. See each README for detail.

* [Spring Boot with Spring Cloud GCP and Spring Data Spanner](spring-data-spanner-example)
* [Spring Boot with Hibernate and Spring Data JPA](spring-data-spanner-example)
* [Quarkus with Hibernate and Panache](quarkus-jpa-spanner-example)
* [Microprofile with Hibernate and Thorntail](microprofile-jpa-spanner-example)

