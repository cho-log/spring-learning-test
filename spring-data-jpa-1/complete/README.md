# 1. JPA

<br>

Java Persistence API (JPA)는 Java 개발자들이 관계형 데이터베이스를 더 쉽고 효율적으로 다룰 수 있도록 돕는 인터페이스입니다.
JPA는 Java의 표준 ORM (Object-Relational Mapping) 기술로서, ORM은 객체 지향 프로그래밍의 원리를 관계형 데이터베이스에 적용하여, 데이터베이스 테이블을 자바 객체로 매핑하고 관리하는 방법입니다.

<br>

예를 들어, JdbcTemplate을 사용하는 개발자들은 SQL 쿼리를 작성하고 실행하는 데 익숙합니다.
그러나 JPA를 사용하게 되면, 'User'라는 클래스를 정의하고, 'findAll()'과 같은 간단한 메서드 호출만으로 사용자 정보를 검색할 수 있게 됩니다.
이는 데이터베이스 작업을 훨씬 직관적이고 효율적으로 만들어 줍니다.

<br>

JPA를 깊이 있게 이해하기 위해서는 '영속성 컨텍스트' 외에도 여러 중요한 개념과 키워드가 있습니다.
이에는 '엔티티 관리자(Entity Manager)', 'JPQL (Java Persistence Query Language)', '트랜잭션 관리', 그리고 '캐시 메커니즘' 등이 포함됩니다.
이러한 개념들은 JPA의 전체적인 작동 방식과 효율성을 이해하는 데 필수적입니다.
하지만, 이번 소개에서는 이러한 주제들을 깊게 다루지는 않을 것이며, 단지 JPA의 다양한 측면과 가능성을 엿볼 수 있다는 점만 기억해 주시면 좋겠습니다.
이러한 추가적인 개념들을 학습하면, JPA를 사용하여 데이터베이스 작업을 수행하는 데 있어서 더욱 폭넓은 관점과 이해를 얻을 수 있을 것입니다.

<br>

# 2. EntityMapping

<br>

엔티티 매핑은 JPA를 사용하여 객체와 데이터베이스 테이블 간의 관계를 설정하는 과정입니다.
아래 애너테이션을 사용하여 엔티티 매핑을 수행할 수 있습니다.

- @Entity: 클래스가 JPA 엔티티임을 나타냅니다.
- @Id: 엔티티의 기본 키를 나타냅니다.
- @GeneratedValue: 기본 키 값 생성 전략을 지정합니다.

<br>

```java
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // ...
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.EntityMappingTest.mapEntity`
- 수행 방법
  - `cholog.Customer` 을 이용하여 학습 테스트를 성공시키세요.
  - Customer 클래스에 @Entity, @Id 어노테이션을 활용하세요.

<br>

# 3. Repository

<br>

스프링 데이터 JPA는 리포지토리 인터페이스를 사용하여 엔티티에 대한 CRUD 작업을 수행하는 메서드를 제공합니다.

<br>

```java
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
```

<br>

### 학습 테스트

- 테스트 클래스: `cholog.RepositoryTest`
- 수행 방법
  - `cholog.CustomerRepository` 을 이용하여 학습 테스트를 성공시키세요.
  - CustomerRepository 인터페이스를 생성하고, CrudRepository를 상속하세요.
  - CrudRepository가 제공하는 메서드를 활용하세요.

# 4. Query Creation

<br>

스프링 데이터 JPA는 메서드 이름을 분석하여 쿼리를 생성하는 기능을 제공합니다.

<br>

```java

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}
```

<br>

### 학습 테스트

- 테스트 클래스: `cholog.QueryCreationTest`
- 수행 방법
  - `cholog.CustomerRepository` 을 이용하여 학습 테스트를 성공시키세요.
  - CustomerRepository에 테스트를 성공시키기 위해 메서드를 추가하세요.
  - 로그를 통해 쿼리가 생성되는 것을 확인하세요.

<br>

# 5. 참고자료
- [Spring Data JPA - Getting Started(Entity Mapping)](https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html)
- [Spring Data JPA - Core concepts(CrudRepository Interface)](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)
- [Spring Data JPA - Defining Query Methods > Query Creation](https://docs.spring.io/spring-data/jpa/reference/repositories/definition.html)
