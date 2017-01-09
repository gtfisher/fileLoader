Spring-Boot file uploader
=======

```
Spring Boot + Spring Cloud AWS
```

Simple file upload example built with spring boot and maven

Noddy example that has a server uploader and a S3 bucket uploader.

The server uploader with load files to a location pointed to by storage.location in the application.properties file

The S3 Bucket upload will do mulipart upload to a buck that is configured in the applications.properties file. 
The bucket name needs to be set to an exiting AWS S3 bucket and 
the aws access key and secret key also need to be setup in the same 
properties file.

To get the access credentials create an IAM user with access to the bucket
then get the secrete key and access key for that iam user

To run ensure you have maven 3 installed, then from the porject root run mvn sping-boot:run, this will run with an embedded tomcat server, by default this will use port 8080.
Navigate to localhost:8080 to see links the controllers

The project also supports an api that exposes rest end points 


