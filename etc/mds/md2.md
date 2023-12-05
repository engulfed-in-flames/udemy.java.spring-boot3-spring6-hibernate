# Spring Core

## 제어 역전(IoC, Inversion of Control)

사람이 차를 운전하는 것은 사람이 자동차를 제어하는 것이라 바꿔 말할 수 있다.
만약 운전 기사를 고용하면 본인은 운전할 필요가 없게 되어 자기 일에 집중할 수 있게 된다.
이는 제어 역전(IoC)의 사례이며, 소프트웨어 공학의 주요 원칙 중 하나이다.

개발자는 IoC를 통해 다음의 효과를 얻을 수 있다.

- 보일러 플레이트를 줄어든다.
- 클래스 간의 디펜던시가 줄어든다.
- 앱 테스트와 보수 및 유지가 원활해진다.

## Spring Container

스프링에서는 스프링 컨테이너를 통해 제어 역전을 수행할 수 있다.
스프링 컨테이너의 주요 기능은 다음과 같다.

- 오브젝트를 생성하고 관리한다. (IoC)
- 오브젝트 디펜던시 주입. (DI, Dependency Injection)

스프링 컨테이너 설정은 어노테이션이나 소스 코드로 할 수 있다. (XML is deprecated)

## Dependency Injection

### What is DI?

Dependency Injection이란 클라이언트의 객체 생성에 대한 디펜던시를 클라이언트의 행위로부터 분리하는 것을 의미한다.
이로 인해 얻을 수 있는 효과는 다음과 같다.

- IoC와 클래스의 단일 책임 원칙을 준수하여 클래스 간의 결합도를 낮춘다.

### Injection Types

권장되는 두 가지 인젝션 타입

- 생성자 인젝션
  - 어떤 디펜던시가 필수일 때
  - 프로젝트 개발 초기에
- 세터 인젝션
  - 어떤 디펜던시가 선택일 때

### DI

1. 디펜던시 인터페이스와 클래스를 정의

   - `@Component` 어노테이션은 해당 클래스를 빈으로 등록한다. ※ 빈은 스프링이 관리하는 자바 클래스를 의미한다.
   - `@Component` 어노테이션은 빈이 DI 기능을 수행할 수 있도록 한다.

2. REST 컨트롤러 생성

3. 인젝션을 위해 클래스에 생성자를 정의

   - `@Autowired` 어노테이션을 통해 스프링은 해당 클래스 생성자에 디펜던시를 주입한다.
   - 생성자가 하나일 때, 어노테이션은 생략할 수 있다.

4. URL 매핑

### Component Scaning

`@SpringBootApplication`은 다음의 어노테이션들로 구성되어 있다.

- `@EnableAutoConfiguration`
- `@ComponentScan`
- `@Configuration`

스프링부트는 메인 앱에서 시작하여 재귀적으로 하위 디렉토리에 컴포넌트가 존재하는지를 스캔한다.
그러나 기본적으로 메인 디렉토리 바깥에 위치한 패키지는 스캔하지 않는다.
다른 패키지들을 스캔하려면 `@SpringBootApplication`을 통해 명시해야 한다.

### Constructor Injection

```java
@RestController
public class CoachController {

	private Coach coach;

	@Autowired
	public CoachController(Coach coach) {
		this.coach = coach;
	}

	@GetMapping("/daily-workout")
	public String getDailyWorkout() {
		return coach.getDailyWorkout();
	}
}
```

위의 코드에서 클라이언트가 생성자를 직접 호출하지 않았음에도 `coach` 인스턴스를 사용할 수 있는 것은 스프링이 IoC를 수행했기 때문이다.

### Setter Injection

세터 함수를 호출함으로써 디펜던시를 주입하는 것으로 다음의 방법으로 사용할 수 있다.

1. 클래스에 세터 함수를 정의한다.
2. `@Autowired`로 디펜던시를 주입한다.

### Field Injection

[!warning] ❌ 유닛 테스트를 어렵게 하기 때문에 권장되지 않음
(`private`을 포함하여) 클래스의 필드에 값을 직접 할당하는 것을 의미하며, 자바 리플렉션을 사용한다. REST 컨트롤러에 어떠한 생성자나 세터도 정의하지 않았을 때 동작한다.

### @Qualifier

특정 인터페이스를 구현한 클래스가 여렷일 때, 스프링은 어떤 클래스의 생성자를 호출할 것인지를 판단해야 한다.

1. Constructor Injection

```java
@RestController
public class CoachController {

    private Coach coach;

    @Autowired
    public CoachController(@Qualifier("cricketCoach") Coach coach) { // Class name starts with lower case.
    	this.coach = coach;
    }

    @GetMapping("/daily-workout")
    public String getDailyWorkout() {
    	return coach.getDailyWorkout();
    }
}
```

2. Setter Injection

```java
@RestController
public class CoachController {

    private Coach coach;

    @Autowired
    public void setCoach(@Qualifier("cricketCoach") Coach coach) { // Class name starts with lower case.
    	this.coach = coach;
    }

    @GetMapping("/daily-workout")
    public String getDailyWorkout() {
    	return coach.getDailyWorkout();
    }
}
```

### @Primary

`@Primary` 어노테이션으로 `@Qualifier`를 대체할 수도 있다. `@Primary`는 단 하나의 클래스에만 적용할 수 있다.

```java
@Component
@Primary
public class CricketCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes!";
	}

}

```

`@Qaulifier`와 `@Primary`를 같이 사용할 수도 있다. 하지만 `@Qaulifier`의 우선 순위가 더 높다. `@Primary`보다는 `@Qaulifier`를 사용하는 것이 바람직하다.

## Lazy Initialization

앱이 시작 시에 디폴트로 스프링은 모든 빈들을 Initializing한다. `@Lazy` 어노테이션으로 lazy initialization을 할 수 있으며, 특정 경우에만 빈이 initialization되게 할 수 있다.

`application.properties`에 다음의 속성을 입력하면 모든 빈들을 lazy initialization한다.

```
spring.main.lazy-initialization=true
```

Lazy initialization을 사용하는 데에 여러 장점이 있지만, 이로 인해 발생하는 사이드 이펙트에 익숙치 않다면 사용하지 않는 것이 바람직하다.

## Bean

### Bean Scopes

- 빈의 라이프 사이클, 얼마나 많은 인스턴스가 생성되는지, 어떻게 공유되는지
- 디폴트 스코프는 싱글톤이다.
  - 싱글톤이란 어떤 빈에 대해서 단 하나의 인스턴스만이 생성되는 것을 의미하며, 메모리에 캐싱된다.
  - 싱글톤에서는 둘 이상의 인젝션이 수행되더라도 각 메모리 주소는 하나의 인스턴스만을 가리킨다.
- 스코프의 종류는 다음과 같다.
  - 싱글톤, 프로토타입, 리퀘스트, 세션, 글로벌 세션
  - 리퀘스트, 세션, 글로벌 세션 스코프는 오직 웹 앱에서만 사용된다.

### Bean Lifecycle

- `@PostConstruct`
- `@PreDestroy`

프로토 타입 빈의 경우 소멸자가 호출되지 않는다. 따라서, 프로토 타입 빈을 사용하게 될 경우 리소스 낭비가 될 위험이 있다.

### Config Bean

방법

1. 자바 클래스를 생성하고 `@Configuration` 어노테이션을 할당
2. `@Bean` 메소드를 정의; 빈 ID는 메소드 이름이 디폴트
3. 앱에 빈을 주입

`@Component` 대신 `@Bean`을 사용하는 이유

> 서드 파티 클래스의 소스 코드에 접근할 수는 없으나, 서드 파티 클래스를 빈으로 사용하고 싶을 때

소스 코드에 접근할 수 없기 때문에 해당 클래스에 `@Component`를 추가할 수 없다. 이때 `@Bean`을 사용한다.
