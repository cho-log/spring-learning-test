# 1. Java-based Configuration

<br>

[spring-core-1](./../../spring-core-1)에서는 `@Component`, `@Autowired` 어노테이션을 사용해 스프링 빈을 생성하고 빈간 의존성을 주입하는 방법을 익혔습니다. 

이 문서에서는 Java 코드와 스프링이 제공하는 어노테이션을 사용해 스프링 컨테이너를 정의하는 법을 알아봅니다.

<br>

## 1.1. Declaring a Bean

<br>

`@Configuration` 어노테이션이 달린 클래스와 `@Bean` 어노테이션이 달린 메서드를 통해 Java 코드에서 스프링 빈을 등록할 수 있습니다.

가장 단순한 `@Configuration` 클래스는 다음과 같습니다.

<br>

```java
@Configuration
public class AppConfig {
  @Bean
  public MyServiceImpl myService() {
    return new MyServiceImpl();
  }
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.configuration.ConfigTest.shouldCreateBean`
- 수행 방법
  - `cholog.configuration.config.AppConfig` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

## 1.2 Bean Dependencies

<br>

`@Configuration` 어노테이션이 있는 클래스 내의 메서드는 같은 클래스 내의 다른 `@Bean` 메서드를 호출하여 빈 간의 의존성을 정의할 수 있습니다.

<br>

```java
@Configuration
public class AppConfig {

  @Bean
  public BeanOne beanOne() {
    return new BeanOne(beanTwo());
  }

  @Bean
  public BeanTwo beanTwo() {
    return new BeanTwo();
  }
}
```

<br>

다른 `@Configuration` 클래스 내의 빈을 사용해서도 의존성을 정의할 수 있습니다. 이 방법에 대해서는 [Spring - Bean Dependencies](https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html#beans-java-dependencies) 문서를 참고해 주세요.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.configuration.ConfigTest.shouldCreateBeansThatHasDependency`
- 수행 방법
  - `cholog.configuration.config.AppConfig` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

# 2. Property

<br>

프로퍼티는 애플리케이션의 구성값을 키-값 쌍으로 저장합니다. 예를 들어, 데이터베이스 연결 정보나 API 키 같은 설정값입니다.

스프링의 `Environment` 인터페이스는 이러한 프로퍼티 소스들을 통합하여 관리하고, 필요한 프로퍼티 값을 조회하는 기능을 제공합니다.

<br>

## 2.1. Using @PropertySource and Environment

<br>

`@PropertySource` 어노테이션을 사용해 프로퍼티 파일을 로드하고 `Environment`를 사용해 프로퍼티 값을 읽어올 수 있습니다.

<br>

```java
@Configuration
@PropertySource("classpath:/com/myco/app.properties")
public class AppConfig {

  @Autowired
  Environment env;

  @Bean
  public TestBean testBean() {
    TestBean testBean = new TestBean();
    testBean.setName(env.getProperty("testbean.name"));
    return testBean;
  }
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.property.PropertySourceConfigTest.getSecretKeyFromUsingEnvironment`
- 수행 방법
  - `cholog.property.config.PropertySourceConfig` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

## 2.2. Using @PropertySource and @Value

<br>

`@PropertySource`를 사용해 로드한 프로퍼티 파일의 값을 `@Value` 어노테이션을 통해 주입할 수 있습니다.

<br>

```java 
@Configuration
@PropertySource("classpath:/com/myco/app.properties")
public class AppConfig {
  @Bean
  public TestBean testBean(@Value("${testbean.name}") String name) {
    TestBean testBean = new TestBean();
    testBean.setName(name);
    return testBean;
  }
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.property.PropertySourceConfigTest.getSecretKeyFromUsingAnnotation`
- 수행 방법
  - `cholog.property.config.PropertySourceConfig` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

# 3. Profile

<br>

프로파일은 애플리케이션 설정의 논리적인 그룹입니다. 예를 들어, 개발(development), 테스트(testing), 운영(production)과 같이 다른 환경에 적합한 설정을 분리할 수 있습니다.

프로파일을 사용하면 같은 애플리케이션이지만 환경에 따라 다른 구성을 적용할 수 있습니다.

스프링이 제공하는 `Environment` 인터페이스는 현재 활성화되어 있는 프로파일과 기본 프로파일을 관리합니다.

<br>

## 3.1. @Profile

<br>

`@Profile` 어노테이션을 이용하여 특정 프로파일에 따라 빈을 등록할 수 있습니다.
`@Profile` 어노테이션은 클래스 레벨, 메서드 레벨에 모두 적용 가능합니다.

<br>

#### @Configuration 클래스에 적용하는 경우
클래스 내에서 정의된 Bean들은 `development` profile일 때만 등록됩니다.
```java
@Configuration
@Profile("development")
public class StandaloneDataConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("classpath:com/bank/config/sql/schema.sql")
			.addScript("classpath:com/bank/config/sql/test-data.sql")
			.build();
	}
}
```

#### @Bean 메서드에 적용하는 경우
profile에 따라 등록되는 Datasource Bean이 달라집니다. 
```java
@Configuration
public class AppConfig {

  @Bean("dataSource")
  @Profile("development")
  public DataSource standaloneDataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .addScript("classpath:com/bank/config/sql/test-data.sql")
            .build();
  }

  @Bean("dataSource")
  @Profile("production")
  public DataSource jndiDataSource() throws Exception {
    Context ctx = new InitialContext();
    return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
  }
}
```    

<br>

### 학습 테스트
- 테스트 메서드: 
  - `cholog.profile.ProfileConfigTest.shouldCreateInmemoryRepositoryBean`
  - `cholog.profile.ProfileConfigTest.shouldCreateJdbcRepositoryBean`
- 수행 방법
  - `cholog.profile.config.ProfileConfig` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

# 4. 더 생각해보기
- 스프링 컨테이너를 정의하는 방법은 다양합니다.`@Component`, `@Autowired` 어노테이션을 사용하는 방법과 비교하여 Java 코드로 빈을 관리할 때의 장단점에 대해 생각해보고, 어떤 상황에서 어떤 방식을 택할지 고민해보세요.
- 이 외에도 XML을 사용해 스프링 컨테이너를 정의할 수도 있습니다. XML을 사용하는 방법에 대해 알아보고, Java 코드와 XML을 사용하는 방법을 비교해보세요.

# 5. 참고자료
- [Spring - @Configuration](https://docs.spring.io/spring-framework/reference/core/beans/java/configuration-annotation.html)
- [Spring - @Bean](https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html)
- [Spring - @PropertySource](https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-using-propertysource)
- [Spring - @Value](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html)
- [Spring - @Profile](https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-definition-profiles-java)
