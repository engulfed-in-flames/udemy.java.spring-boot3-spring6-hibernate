# Hibernate & JPA

## What is Hibernate?

- 로우 레벨 SQL을 다루는 자바의 ORM 프레임워크
- JPA 사양을 준수하는 가장 유명한 자바 ORM 프레임워크

## What is JPA?

Jakarta Persisten API로, ORM의 표준 API이다.
장점은 다음과 같다.

- 표준 API이기 때문에, 유지보수가 용이하다.
- JPA 사양을 준수하는 프레임워크 간의 이전이 용이하다.

하이버네이트/JPA가 백그라운드에서 JDBC(Java DB Connectivity)를 활용하여 DB와 소통한다.

## Spring Boot with JPA

### How to do

1. spring initializer Dependencies

   - PostgreSQL Driver
   - Spring Data JPA

2. (Optional) CommandLineRunner

```java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		// CommandLineRunner is exectued after the spring bean have been loaded
		// 데이터베이스 초기화, 환경 설정, 헬스 체크 등 일반적으로 스프링 부트 앱의 스타트업 프로세스로서 실행된다.
		return runner -> {
			System.out.println("Hello World!");
		};
	}
}
```

3. application.properties

````sh
# PSQL properties
spring.datasource.url=jdbc:postgresql://localhost:5432/psql_user
spring.datasource.driver=org.postgresql.Driver
spring.datasource.username=psql_user
spring.datasource.password=1q2w3e4r

# Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql = true
spring.jpa.show-sql = true

# Turn off the Spring Boot Banner
spring.main.banner-mode=off

# Reduce logging level to warn
logging.level.root=warn
```ㄴ

4. Setup Postgresql

### Constructors in Java - Refresher

- 어떤 생성자도 선언하지 않았을 때는 기본 생성자가 디폴트로 제공된다.
- 인자가 있는 생성자를 선언했을 때는 기본 생성자가 제공되지 않는다.
````

### DAO

### JPA Entity Manager

- 스프링 앱은 JPA Entity Manager로 DB와 통신하며, 이떄 Data Source가 필요하다.
- Data Source는 DB 연결 정보를 정의한다.
- 스프링 앱이 JPA Entity Manager와 Data Source를 제공한다.

### @Repository

`@Repository`은 repositories를 위한 annotation으로, 다음과 같은 효과가 있다.

- `@Component`와 마찬가지로 스프링 앱의 컴포넌트 스캐닝을 지원한다.
- JDBC 예외를 해석한다.

### Some SQL Statements

```sql
SELECT setval(pg_get_serial_sequence('psql_user.student', 'id'), 3000, false);

TRUNCATE psql_user.student;
```

### JPQL

### DDL Auto

배포 및 기업용이나 실시간 프로젝트에서는 DB 자동 생성 기능을 사용하지 않는 것이 바람직하다. 너무나 쉽게 DB 데이터가 삭제될 수 있기 때문이다. 대신 SQL 스크립트를 사용하는 것이 바람직하다.

- `spring.jpa.hibernate.ddl-auto`
  - `create` ← 앱 실행시마다 기존 테이블을 제거하고 다시 생성한다.
  - `update` ← 기존 테이블 데이터를 유지한다.

### SQL Logging Configs
