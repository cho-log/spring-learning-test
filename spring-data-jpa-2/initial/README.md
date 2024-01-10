# 1. Entity Relationships Mapping

<br>

객체는 참조를 통해 관계를 정의하지만, 데이터베이스는 외래 키를 사용하여 테이블 관계를 정의합니다.
엔티티 연관관계 매핑(Entity Relationship Mapping)은 두 데이터베이스 테이블 간의 관계를 도메인 모델의 속성으로 모델링합니다.
즉, 데이터베이스 테이블 간의 관계를 객체 간의 관계로 매핑하는 것을 의미합니다.

엔티티 연관관계는 일대일, 다대일, 다대다 등 연결 방식으로 분류되기도 하며,
연결 방향에 따라 엔티티가 상호 접근이 가능하면 양방향, 한쪽 방향으로만 접근이 가능하면 단방향으로 분류됩니다.

엔티티 연관관계는 연결 방식과 연결 방향에 따라 다른 쿼리가 생성되며,
간혹 의도하지 않은 쿼리가 생성되는 경우도 있습니다.
따라서 상황에 따른 적절한 연결 방식과 연결 방향을 선택하는 것이 중요합니다.

<br>

<img src="https://docs.jboss.org/hibernate/orm/6.4/introduction/html_single/images/associations.png" width="500">

<br> 

## 2. 다대일 단방향

<br>

하나의 `Publisher`는 여러개의 `Book`을 가질 수 있는 다대일 관계를 예시가 있습니다.
데이터베이스 테이블에서는 `Book` 테이블의 외래 키가 `Publisher` 테이블의 기본 키를 참조하게 설계하는게 일반적입니다.
이 경우, 객체 설계 방법으로 `Book` 클래스에 `Publisher` 클래스를 참조하는 멤버 변수를 추가할 수 있습니다.
다대일 관계의 멤버 변수를 설정 할 때는 `@ManyToOne` 어노테이션을 사용합니다.

<br>

```java
@Entity
class Book {
    @Id @GeneratedValue
    Long id;
    
    @ManyToOne
    Publisher publisher;
    
    // ...
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ManyToOneTest.uniDirection`
- 수행 방법
  - `cholog.Book` 을 이용하여 학습 테스트를 성공시키세요.
  - `@ManyToOne` 어노테이션을 활용하세요.
  - 테스트 실행 시 출력되는 ddl 로그를 확인하세요.

<br>

# 3. 다대일 양방향

<br>

만약 특정 `Publisher`가 발행한 모든 책을 조회해야 한다면, `Publisher`도 `Book`을 멤버 변수로 관계를 맺으면 좋습니다.
이 경우 `Book`이 `Publisher`에 의존하는 상황에서 `Publisher`도 `Book`에 의존하는 관계를 양방향이라고 합니다.
양방향 연관관계를 만들기 위해서는, `Publisher` 클래스에 콜렉션 값 속성을 추가하고 이를 @OneToMany으로 지정합니다.
이렇게 하면 `Book` 클래스와 `Publisher` 클래스 사이에 양방향 연관관계가 설정됩니다.

양방향 연관관계임을 명확히 표시하고, 이미 지정된 매핑 정보를 재사용하기 위해서는 'mappedBy'를 사용해야 합니다.

데이터베이스에서 테이블간의 관계를 맺을 때, 외래키를 가지는 테이블을 연관관계의 주인이라고 합니다.
엔티티 클래스에서도 연관관계의 주인을 지정해야 합니다.
연관관계의 주인은 mappedBy 속성을 통해 지정할 수 있습니다.
mappedBy 속성은 연관관계의 주인이 아닌 엔티티 클래스의 멤버 변수 이름을 지정해야 합니다.

<br>

```java
@Entity
class Publisher {
    @Id @GeneratedValue
    Long id;
    
    @OneToMany(mappedBy="Ppublisher")
    Set<Book> books;
    
    // ...
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ManyToOneTest.biDirection`
- 수행 방법
  - `cholog.Publisher` 을 이용하여 학습 테스트를 성공시키세요.
  - `@OneToMany` 어노테이션과 mappedBy 상태를 활용하세요.
  - 테스트 실행 시 출력되는 ddl 로그를 확인하세요.
  - mappedBy 설정을 변경하거나 제외하면 어떤 쿼리가 생성되는지 확인하세요.

<br>

다대일 관계 매핑 후 repository를 이용하여 데이터를 조회하면 어떤 쿼리가 생성되는지 확인해보세요.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ManyToOneTest.findByIdForBook`
- 수행 방법
  - 학습 테스트를 실행시켜 어떤 쿼리가 생성되는지 확인하세요.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ManyToOneTest.findByIdForPublisher`
- 수행 방법
  - 학습 테스트를 실행시켜 어떤 쿼리가 생성되는지 확인하세요.
  - select 쿼리에 join이 포함되어 있는지 확인하세요.
  - `cholog.Publisher`에서 @OneToMany의 속성으로 fetch를 FetchType.EAGER로 변경하면 어떤 쿼리가 생성되는지 확인하세요.

<br>

# 4. 일대일 단방향

<br>

일대일 관계는 UNIQUE 제약 조건이 있는 외래 키 열에 매핑된다는 점을 제외하면 @ManyToOne 연관과 거의 동일합니다.
`Author` 테이블에는 연결된 `Person`의 식별자를 보유하는 외래키 컬럼이 있습니다.

<br>

```java
@Entity
class Author {
    @Id @GeneratedValue
    Long id;

    @OneToOne
    Person person;

    // ...
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.OneToOneTest.uniDirection`
- 수행 방법
  - `cholog.Author` 을 이용하여 학습 테스트를 성공시키세요.
  - `@OneToOne` 어노테이션을 활용하세요.
  - 테스트 실행 시 출력되는 ddl 로그를 확인하세요.

<br>

# 5. 일대일 양방향

<br>

`Person` 엔터티의 `Author`에 대한 참조를 다시 추가하여 이 연결을 양방향으로 만들 수 있습니다.
mappedBy로 표시되지 않은 쪽이 의존 관계의 주인이기 때문에, `Author` 엔티티의 author 멤버 변수에는 mappedBy 속성을 지정해야 합니다.

<br>

```java
@Entity
class Person {
  @Id @GeneratedValue
  Long id;

  @OneToOne(mappedBy = "author")
  Author author;

  // ...
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.OneToOneTest.biDirection`
- 수행 방법
  - `cholog.Person` 을 이용하여 학습 테스트를 성공시키세요.
  - `@OneToOne` 어노테이션과 mappedBy 상태를 활용하세요.
  - 테스트 실행 시 출력되는 ddl 로그를 확인하세요.
  - mappedBy 설정을 변경하거나 제외하면 어떤 쿼리가 생성되는지 확인하세요.

<br>

다대일 관계 매핑 후 repository를 이용하여 데이터를 조회하면 어떤 쿼리가 생성되는지 확인해보세요.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.OneToOneTest.findByIdForAuthor`
- 수행 방법
  - 학습 테스트를 실행시켜 어떤 쿼리가 생성되는지 확인하세요.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.OneToOneTest.findByIdForPerson`
- 수행 방법
  - 학습 테스트를 실행시켜 어떤 쿼리가 생성되는지 확인하세요.
  - select 쿼리에 join이 포함되어 있는지 확인하세요.
  - @ManyToOne과 다르게 fetch 설정을 해주지 않아도 join이 포함되는 이유를 고민해보세요.

<br>

# 6. 다대다

<br>

다대다 연관관계는 컬렉션 값 속성으로 표현됩니다.
다대다 관계 매핑을 구현할 때 주의해야 할 점은 이런 관계가 데이터베이스 스키마에서 직접적으로 표현되기 어렵다는 것입니다.
대부분의 관계형 데이터베이스는 다대다 관계를 직접 지원하지 않으므로,
이를 구현하기 위해서는 보통 '조인 테이블' 또는 '연결 테이블'을 사용합니다.
이 테이블은 두 엔티티 간의 관계를 연결하는 데 사용되며, 각 엔티티의 키를 외래 키로 포함합니다.

<br>

```java
@Entity
class Book {
    @Id @GeneratedValue
    Long id;

    @ManyToMany
    Set<Author> authors;

    // ...
}
```

양방향 연관관계인 경우, mappedBy를 지정하여 연관관계의 주인이 아님을 나타내야 합니다.
이는 `Book` 측에서 설정된 속성이 연관관계에서 이미 정의된 매핑을 따른다는 것을 명시하는 것입니다.

<br>

```java
@Entity
class Author {
    // ...

    @ManyToMany(mappedBy="authors")
    Set<Book> books;

    // ...
}
```

<br>

초기에는 `Author` 클래스와 `Book` 사이의 연관 관계를 명료하게 설정할 수 있지만,
추가 정보가 필요해 지는 경우 연관 관계 테이블에 추가 열이 필요해집니다.
추가 정보는 연관 테이블에 속해야 하는데, 이러한 속성들은 Book 속성이나 Author 속성으로 쉽게 저장할 수 없습니다.
따라서 이 경우 연관 테이블에 대한 새로운 엔티티 클래스를 도입하는 것이 바람직합니다.
예를 들어 'BookAuthor',에 저장되며, 이는 작가와 책 사이의 @OneToMany 및 @ManyToOne 연관관계로 매핑됩니다.
이 접근법은 다대다 연관관계를 중간 엔티티를 사용하여 표현함으로써 추가 정보를 쉽게 관리할 수 있게 해줍니다.

<br>

```java
@Entity
class BookAuthor {
    @Id @GeneratedValue
    Long id;

    @ManyToOne
    Book book;

    @ManyToOne
    Author author;

    // ...
}
```

```java
@Entity
class Book {
    // ...

    @OneToMany(mappedBy="book")
    Set<BookAuthor> authors;

    // ...
}
```

```java
@Entity
class Author {
    // ...

    @OneToMany(mappedBy="author")
    Set<BookAuthor> books;

    // ...
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ManyToManyTest.intermediateEntity`
- 수행 방법
  - `cholog.Book`, `cholog.Author`, `cholog.BookAuthor`를 이용하여 학습 테스트를 성공시키세요.
  - @OneToMany, @ManyToOne 어노테이션과 mappedBy 상태를 활용하세요.
  - 테스트 실행 시 출력되는 ddl 로그를 확인하세요.

<br>

# 7. 참고자료
- [Jakarta Persistence - 2.10. Entity Relationships](https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2-m1#a516)
- [An Introduction to Hibernate 6 - 3.15. Associations](https://docs.jboss.org/hibernate/orm/6.4/introduction/html_single/Hibernate_Introduction.html#associations)
