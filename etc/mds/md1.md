# Spring Boot Overview

## The problem of Spring

- 스프링은 자바 앱을 만들기에 굉장히 좋은 프레임워크이다.
- 그러나 스프링으로 작업하는 것은 난이도가 높다.

## Spring Boot Solution

- 스프링 부트는 'opinionated framework'이다. cf) 'Battery-included framework' like Django
- 스프링 부트는 스프링 개발을 보다 편리하게 한다.
- 스프링 부트에는 임베드 된 HTTP 서버가 있기 때문에, 서버를 따로 구축하지 않아도 된다.

## FAQ

- 스프링 부트가 스프링 MVC와 REST를 대체하는가?
  - No. 대신 스프링 부트는 여전히 스프링의 기술들을 적극 활용한다. 스프링 부트는 기본적으로 스프링을 기반으로 설계된 프레임워크이다.

# spring initializr

- [Go →](http://start.spring.io)
- 빠르게 스프링 스타터 프로젝트를 생성할 수 있다.
- 디펜던시를 지정할 수 있다.
- 메이븐이나 그레이들 프로젝트를 생성할 수 있다.
- IDE로 임포트할 수 있다.

## Maven Solution

- 메이븐을 통해 프로젝트에 필요한 JAR 파일들을 찾아 다운로드할 수 있다.

# Spring Overview

- 코어 컨테이너 - 스프링의 핵심 요소로, 빈(Bean)의 팩토리이며, 빈의 디펜던시를 관리한다.
- 인프라 - AOP(Aspect Oriented Programming), 오브젝트에 명시적으로(주석 등) 기능을 추가하는 프로그래밍 방식이다.
- 데이터 액세스 레이어
  - JDBC - JDBC 헬퍼 클래스가 JDBC 구현에 필요한 코드의 상당량을 줄인다.
  - ORM - Hibernate & JPA의 통합
  - Transactions - 트랜잭션을 관리
  - JMS(Java Message Service) - 메시지 브로커에 비동기로 메시지를 전달
- 웹 레이어
  - 웹 - 웹소켓 - 서블렛

# What is Maven?

## Sprinb Boot and Maven

- 메이븐은 프로젝트 매니지먼트 툴 - 빌드 매니지먼트와 디펜던시
- 자바 프로젝트를 빌드할 때 스프링, 하이버네이트, JSON과 같은 JAR 파일들이 필요한데, 일일이 다운로드 할 필요없이 메이븐을 통해 해당 파일들을 준비할 수 있다.
- 메이븐을 통해 프로젝트 생성과 환경 설정에 필요한 노력을 줄일 수 있다.

## Maven - How It Works

- 메이븐이 컨피그 파일을 읽고 로컬 저장소를 확인한다.
- 로컬 저장소에 필요한 파일들이 없다면 원격 저장소에서 다운로드하여 로컬 저장소에 저장하며, 동시에 디펜던시 문제도 해결한다.

## Maven - Key Concepts

- pom.xml
  - **P**roject **O**bject **M**odel file
  - 프로젝트에 대한 환경 설정 파일
  - 메이븐이 참고하는 파일
  - 프로젝트 메타 데이터, 디펜던시, 플러그인 등에 관한 명세
- Project Coordinates - Elements (GAV)
  - 그룹 ID ← 그룹명; 도메인명을 거꾸로 사용하는 것이 관례
  - 아티팩트 ID ← 프로젝트명
  - Version - 선택 사항이나, 포함하는 것이 바람직하
- 디펜던시 조회
  - [Go →](https://central.sonatype.com/)

# File Structure

## Maven Wrapper Files

- mvnw를 통해 메이븐 프로젝트를 실행할 수 있다.
- 메이븐 버전이 올바르지 않을 때 자동으로 올바른 버전을 설치한다.
- .cmd는 윈도우용, .sh는 리눅스/맥용 확장자이다.

## Resources

- `application.properties`
  - 스프링부트는 `application.properties`를 참고하여 프로젝트에 속성을 로드한다.
- `static`
- `templates`

❌ 앱이 JAR로 패키징되어 있다면 `src/main/webapp` 디렉토리에는 접근하지 말 것! 해당 디렉토리는 WAR 패키징에만 사용된다.

# Features of Spring Boot

## Spring Boot Starter

- 메이븐 디펜던시의 컬렉션으로, spring-web, spring-webmvc, hibernate-validator 등 많은 디펜던시를 포함하고 있다.
- spring-web은 기본적으로 spring MVC와 톰캣을 지원한다.
- (예로 spring intializr에서) 디펜던시를 선택하면 pom.xml에 명세된다.
- 30개 이상의 스프링 부트 스타터가 있다.

## Spring Boot Starter Parent

- 스트링 부트 스타터는 버전을 명시하지 않아도 된다.
- Spring Boot Starter Parnet의 장점
  - 메이븐의 디폴트 환경 설정 제공
  - 디펜던시 관리
  - 스프링 부트 플러그인의 디폴트 환경 설정 제공

## Spring Boot Dev Tools

- 코드를 수정할 때마다 앱을 재실행하는 것은 번거롭다.
- spring-boot-devtools이 자동으로 재실행한다.
- POM 파일에 디펜던시를 추가하기만 하면 된다.

```xml
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
</dependency>
```

## Spring Boot Actuator

> 애플리케이션의 관리, 모니터링, 헬스체크는 어떻게 하면 좋을까?

- 새로운 REST 엔드포인트(`/actuator`)를 추가하는 것만으로 간단하게 애플리케이션을 관리할 수 있도록 한다.
- POM 파일에 디펜던시를 추가하기만 하면 된다.
- `/health`, `/info`, `/auditevents`, `/beans` 등 여러가지 엔드포인트를 지원한다.

```txt
# Use wildcard "*" to expose all endpoints
managements.endpoints.web.exposure.include=*
```

- 보안은? 액츄에이터를 잠금하는 방법도 있다.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## Securing Endpoints

- 엔드포인트를 잠금할 수 있다.
- POM 파일에 디펜던시를 추가하기만 하면 된다.

```xml

```

- 디폴트 유저 네임과 비밀번호를 덮어쓸 수 있다.
- 특정 엔드포인트를 잠금 목록에서 제외할 때는 `application.properties`에 다음과 같이 입력하면 된다.

```
management.endpoints.web.exposure.exclude=health,info
```

# Run Spring Boot Apps from CL

Option 1 - `mvnw` allows you to run a Maven project

```sh
./mvnw clean compile test
```

Option 2 - Use spring boot maven plugin

```xml
<plugin>
    <groupdId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```

# Spring Boot Properties

## Properties

1. Core Properties
   로깅 레벨

2. Web Properties
   포트나 서블렛, HTTP 세션 등 웹 관련 설정

3. Actuator Properties
   액츄에이터 관련 설정

4. 그외
   Security properties, data properties etc...

## Configure spring app
