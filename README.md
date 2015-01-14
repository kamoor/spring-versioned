This is a simple proof of concept to show versioning features at method level.

How?
====
In any spring bean, you can add @Versioned(feature, version) annotation at method level. And provide the feature version in any spring properties.
Runtime will execute active version of code only. 

Example:

@Versioned("feature.helloworld", 1)
public String hello(String message){
   return "Hello "+ message;
}

@Versioned("feature.helloworld", 2)
public String helloV2(String message){
   return "Hola "+ message;
}

Properties Entry:
feature.helloworld=1 will make sure return message is "Hello"+ message
feature.helloworld=2 will make sure return message is "Hola"+ message

Usage:
------
This can be used to roll out bug fixes and features to production and keep it versioned in runtime. Activate or rollback with out code change or too much  hassle. 


Note:
-----
This is thread safe because version-ed methods will execute on the same target object all the time.

Build and Deploy
----------------
1. mvn clean install && java -jar target/*.jar
2. curl http://localhost:8080/v1/hello-world
