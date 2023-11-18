# Cafe

Shift scheduling application for a cafe.

### User stories

- [ ] As the manager, I can query staff info.
  - [X] GET `/staff`
  - [X] GET `/staff/{id}`
    - [ ] also return availability info
- [ ] As the manager, I can add new staff.
  - [X] POST `/staff`
- [ ] As the manager, I can update staff info.
  - [X] PUT `/staff/{id}`
- [ ] As a staff, I can submit upcoming _un_-availability.
  - [X] POST `/availability/unavailable_period/{staffId}`
  - [X] GET `/availability/unavailable_period/{staffId}`
  - [X] DELETE `/availability/unavailable_period/{unavailabilityId}`
  - According to the manager, asking staff to input unavailable times instead of available times results in more available hours.
- [ ] As the manager, I can query upcoming staff availability.
  - [ ] GET `/availability`
  - [X] GET `/availabiliity/{staffId}`

## Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.5/gradle-plugin/reference/html/#build-image)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#data.sql.jdbc)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.liquibase)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

