# REST APIs - REST Web Services

> REST calls can be made over HTTP.
> REST is language dependent.

- terms
  - MIME ← Multipurpose Internet Mail-Extension
  - REST ← REpresentational State Transfer

## Java JSON Data Binding

자바 JSON 데이터 바인딩이란 JSON 데이터를 자바의 POJO로 변환하는 것(그 역도 포함)을 의미한다. 스프링의 `Jackson` 디펜던시가 프록시처럼 이를 behind the scenes로 수행한다.

**JSON to Java POJO**
JSON을 POJO로 변환할 때는 POJO의 세터 메소드를 호출한다.

**Java POJO to JSON**
게터 메소드를 호출한다.

## @ControllerAdvice

큰 프로젝트에서는 global exception handler를 정의하는 것이 바람직하다. 이때 `@ContorllerAdvice`와 함께 exception handler 클래스를 정의할 수 있다.

## @Service

서비스 레이어를 사용하는 이유는 다음과 같다.

- Service Facade 디자인 패턴
- 커스텀 로직을 위한 중간 레이어
- 다양한 소스로부터의 데이터를 통합

DAO 레이어와 서비스 레이어 모두 정의하는 것이 바람직하다. 또한 `@Transactional`은 서비스 레이어에 추가하는 것이 바람직하다.

## Spring Data JPA & Spring DATA REST

[Go →](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details)

- 각각을 통해 DAO 보일러 플레이트, REST 보일러 플레이트를 줄일 수 있다.
- `JpaRepository`가 `@Transactional`을 수행한다.
- Spring Data REST의 엔드포인트는 HATEOAS를 사용한다. 다른 말로, 리스폰스가 리소스에 대한 메타 데이터(url, 페이지 등)를 포함한다.

# Spring Boot REST API - Security Overview

스프링은 보안을 위해 서블렛 필터를 사용하며, 다음과 같이 동작한다.

- 리퀘스트에 대해 프리 & 포스트 프로세스를 수행한다.
- 서블렛 필터를 바탕으로 매우 다양한 보안 기능을 제공한다.
- Authentication & Authorization
  - Authentication ← ID와 비밀번호를 검증
  - Authorization ← 권한 여부를 검증

## Security User Creation

스프링 부트에서 유저 생성시에 ID와 비밀번호를 `application.properties`에 정의해서는 안 된다.

1. 인메모리 유저 생성시에는 `InMemoryUserDetailsManager` 빈을 로드한다.
2. DB 유저 생성시에는

## URL Restriction

```java

```

## CSRF Protection

[XSS와 CSRF의 차이 →](https://nordvpn.com/ko/blog/csrf/)
사용자 인증 정보를 저장하는 웹 애플리케이션에서는 디폴트로 CSRF를 방어해야만 한다. 하지만 stateless REST APIs에서는 필수가 아니며, 이유는 다음과 같다.

- CSRF는 서버가 클라이언트의 상태를 저장하고 있는 것(세션)을 이용한 취약점이다. 곧, 서버가 인증된 사용자의 리퀘스트를 신뢰하는 상황을 악용한 것이다.
- Stateless REST APIs에서는 사용자 인증 정보를 저장하지 않기 때문에 반드시 CSRF를 방어할 필요는 없다.
- Stateless REST APIs는 대게 사용자가 아닌 앱 자체가 리퀘스트를 수행하므로, CSRF 위협으로부터 안심할 수 있다.

세션 로그인 방식을 사용한다면 CSRF 방어를 고려해야만 한다.

## Bycryte Encription

1. [Bycrpt를 사용해야 하는 이유 →](www.luv2code.com/why-bcrypt)
2. [Bycrpt 알고리즘 분석 →](www.luv2code.com/bcrypt-wiki-page)
3. [비밀번호 해쉬 - 모범 사례 →](www.luv2code.com/password-hasing-best-practices)

## Security Schema Customization

스프링에 커스텀 테이블에 어떻게 퀘리하면 되는지를 알려주면 된다.
`users`, `authorities` → `members`, `roles`

## How to add security support using JPA/Hibernate

[PDF →](https://www.luv2code.com/bonus-lecture-spring-boot-rest-security-jpa-hibernate-bcrypt-pdf)

[Source Code →](https://www.luv2code.com/bonus-lecture-spring-boot-rest-security-jpa-hibernate-bcrypt-code)
