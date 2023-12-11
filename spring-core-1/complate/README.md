# 1. Spring Bean

<br>

스프링은 애플리케이션의 복잡성을 줄이고 유지보수를 용이하게 하기 위해 객체의 생성, 설정 및 생명주기를 관리하는 스프링 컨테이너를 제공합니다. 
이 컨테이너가 관리하는 객체를 스프링 빈이라고 하며, 이를 통해 의존성 주입 및 객체 관리가 자동화됩니다.

<br>

## 1.1. Bean Registration

<br>

스프링에서 빈을 등록하는 방법은 다양한데, 그 중 애너테이션을 이용한 방법으로 클래스에 `@Component` 어노테이션을 추가하는 방법이 있습니다.

<br>

```java
@Component
public class SpringBean {
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.BeanTest.registerBean`
- 수행 방법
  - `cholog.bean.SpringBean` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

## 1.2 Bean Autowiring

<br>

스프링 컨테이너에 등록된 객체는 매번 새로이 생성할 필요없이 컨테이너에서 가져와서 사용할 수 있습니다.

<br>

```java
@Autowired
private SpringBean springBean;
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.BeanTest.autowiredBean`
- 수행 방법
  - `cholog.bean.AutowiredBean` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

# 2. Dependency Injection

<br>

스프링 컨테이너에 등록된 스프링 빈 간의 의존성을 관리하는 방법은 다양합니다. 
그 중 애너테이션을 이용한 방법으로 생성자, 세터, 필드에 `@Autowired` 어노테이션을 추가하는 방법이 있습니다.

<br>

## 2.1. Constructor Injection

<br>

스프링 컨테이너에 등록된 스프링 빈 간의 의존성을 생성자를 통해 주입하는 방법입니다.

<br>

```java
private InjectionBean injectionBean;

public ConstructorInjection(InjectionBean injectionBean) {
    this.injectionBean = injectionBean;
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.DependencyInjectionTest.constructorInjection`
- 수행 방법
  - `cholog.di.ConstructorInjection` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

## 2.2. Setter Injection

<br>

스프링 컨테이너에 등록된 스프링 빈 간의 의존성을 세터를 통해 주입하는 방법입니다.

<br>

```java 
private InjectionBean injectionBean;

@Autowired
public void setInjectionBean(InjectionBean injectionBean) {
    this.injectionBean = injectionBean;
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.DependencyInjectionTest.setterInjection`
- 수행 방법
  - `cholog.di.SetterInjection` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

## 2.3. Field Injection

<br>

스프링 컨테이너에 등록된 스프링 빈 간의 의존성을 필드를 통해 주입하는 방법입니다.

<br>

```java 
@Autowired
private InjectionBean injectionBean;
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.DependencyInjectionTest.autowiredInjection`
- 수행 방법
  - `cholog.di.FieldInjection` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

# 3. Component Scan

<br>

스프링 컨테이너에 등록된 스프링 빈을 자동으로 찾아서 등록하는 방법입니다.

<br>

## 3.1. @ComponentScan

<br>

`@ComponentScan` 어노테이션을 이용하여 스캔할 패키지를 지정할 수 있습니다.
앞서 수행한 학습 테스트에서 `@ComponentScan`을 사용하지 않고도 정상동작했던 이유는 `@SpringBootApplication`이 `@ComponentScan`을 포함하고 있기 때문입니다. 

<br>

```java
...
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
  ...
}
```    

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ComponentScanTest.scanComponent`
- 수행 방법
  - `cholog.scan.ContextConfiguration` 클래스를 이용하여 학습 테스트를 성공시키세요.

<br>

# 6. 참고자료
- [Spring - @Component](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-stereotype-annotations)
- [Spring - Dependencies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-dependencies)
- [Spring - Constructor Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-constructor-injection)
- [Spring - Setter Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-setter-injection)
- [Spring - Field Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-autowired-annotation)
