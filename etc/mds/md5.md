# ThymeLeaf

## Java's Standard Bean Validation API

[Go →](http://www.beanvalidation.org)
스프링부트와 타임리프도 지원한다.

## @InitBinder

- 프리 프로세서
- 컨트롤러에 도달하기 전의 리퀘스트를 프리 프로세싱한다.

```java
@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {

		// if true, trim to null
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
```

## messages.properties

## @interface

- Custom Java Annotation

```java
// CustomConstarint.java
@Constraint(validatedBy = CustomConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomConstraint {

	// Define default custom constraint
	public String value() default "LUV";

	// Define default error message
	public String message() default "must start with LUV";

	// Define default groups
	public Class<?>[] groups() default {};

	// Define default payloads
	public Class<? extends Payload>[] payload() default {};
}
```

```java
// CustomConstraintValidator.java
public class CustomConstraintValidator implements ConstraintValidator<CustomConstraint, String> {


	private String prefix;

	@Override
	public void initialize(CustomConstraint customConstraint) {
		prefix = customConstraint.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean result = value.startsWith(prefix);

		return result;
	}
}

```

## index.html

스프링은 루트 경로에서 GET 메소드 수행시에 `src/main/resource/static`에 `index.html`이 있는지를 먼저 확인한다. 파일이 없다면 매칭되는 템플릿이 있는지를 찾는다.

```html
<meta http-equiv="refresh" content="0; URL='employees" />
```

위 코드로 해당 html 파일이 로드됐을 때, 리다이렉트나 리프레쉬 효과를 발생시킬 수 있다. `http-equiv` 속성은 문서 종류에 대한 정보 명시하거나, 혹은 페이지를 리프레쉬하는 용도로 사용된다.

# Spring Boot Sercurity

## SQL Script for PSQL

```sql
-- For users table
CREATE TABLE users (
	'username' varchar(50) NOT NULL,
	'password' varchar(68) NOT NULL,
	'enabled' smallint NOT NULL,
	PRIMARY KEY ('username')
)

-- For authorities table
CREATE TABLE authorities (
	'username' varchar(50) NOT NULL,
	'authority' varchar(50) NOT NULL,
	PRIMARY KEY ('username', 'authority')
	CONSTRAINT 'authorities_fk1' FOREIGN KEY ('username') REFERENCES 'users' ('username')
)
```

Bcrypt로 암호화된 비밀번호를 사용하기 위해서는 `password` 속성의 크기를 68로 해야 한다. `{bcrypt}` 8자와 암호화된 60자의 문자열을 더한 크기이다.

## Custom Login Page

```java
@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsManager userDetailManager(DataSource dataSource) {

		...
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(configurer ->
						configurer.anyRequest().authenticated()
				).formLogin(form->
						form
						.loginPage("showLoginForm")
						.loginProcessingUrl("/authenticateUser")
						.permitAll()
		);

		return http.build();
	}
}
```

스프링 시큐리티는 커스텀 로그인 페이지에서 `name` 속성이 각각 `username`, `password`인 `<input/>` 필드의 입력값을 전달 받아 자동으로 로그인 처리를 한다.

## Built-in Error Message for Login Page

```html
<div th:if="${param.error}">
  <p><i>Invalid username or password</i></p>
</div>
```

로그인 페이지에 잘못된 값을 입력하면 URL 파라미터에 `error`가 추가된다. 이를 통해 로그인에 실패했는지 유무를 확인할 수 있다.

## Logout Process

- 로그아웃 버튼을 만들려면 <form> 태그를 사용해야 한다.
- `GET` 메소드는 비활성화된다.
- 컨트롤러가 필요하지 않다.

```html
<form th:action="@{/logout}" method="post">
  <button type="submit">Logout</button>
</form>
```
