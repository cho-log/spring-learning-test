# 1. JdbcTemplate

<br>

스프링은 데이터베이스와의 연동을 쉽게 도와주는 여러 가지 도구와 방식을 제공합니다.
JDBC(Java Database Connectivity)는 자바에서 데이터베이스에 접속할 수 있게 도와주는 API입니다.
JdbcTemplate은 이러한 JDBC를 좀 더 편리하게 사용할 수 있도록 스프링에서 제공하는 템플릿 클래스입니다. 
이를 사용하면 데이터베이스 연동 코드를 좀 더 간결하고 안정적으로 작성할 수 있습니다.
JdbcTemplate은 스프링 JDBC의 핵심이며 다른 고수준의 기능들도 결국 내부에서는 이 JdbcTemplate을 활용합니다.

<br>

JdbcTemplate은 핵심 JDBC 작업 흐름(예: 문장 생성 및 실행)의 기본적인 업무를 수행하며, 애플리케이션 코드는 SQL을 제공하고 결과를 추출하는 역할을 담당합니다.
JdbcTemplate 클래스는 다음과 같은 기능을 제공합니다.

- SQL 쿼리 실행
- statements 및 저장된 procedure all 업데이트
- ResultSet 인스턴스를 반복하고 반환된 매개 변수 값의 추출을 수행
- JDBC 예외를 캡처하여 org.springframework.dao 패키지에 정의된 일반적이고 더 유용한 예외 계층으로 변환

<br>

# 2. Querying (SELECT)
<br>

JdbcTemplate을 이용하여 SELECT 쿼리를 실행하는 여러가지 방법을 제공합니다.
queryForObject, query, queryForList, queryForRowSet, queryForMap 등의 메서드를 이용하여 쿼리를 실행할 수 있습니다.

<br>

# 3. Querying for a Single Object

<br>

JdbcTemplate의 queryForObject 메서드를 이용하여 단일 객체를 조회할 수 있습니다.

<br>

## 3.1 Object with Count

<br>

queryForObject의 첫 번째 매개변수는 쿼리문이며, 두 번째 매개변수는 조회 결과를 매핑할 클래스 타입입니다.

<br>

```java
int rowCount = jdbcTemplate.queryForObject("select count(*) from customers", Integer.class);
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.QueryingDaoTest.count`
- 수행 방법
  - `cholog.QueryingDAO.count` 을 이용하여 학습 테스트를 성공시키세요.

<br>

## 3.2 Object with Parameter

<br>

queryForObject의 세 번째 매개변수를 이용하여 쿼리문에 바인딩할 파라미터를 전달할 수 있습니다.

<br>

```java

String lastName = jdbcTemplate.queryForObject("select last_name from customers where id = ?", String.class, id);
```
<br>

### 학습 테스트
- 테스트 메서드: `cholog.QueryingDaoTest.getLastName`
- 수행 방법
  - `cholog.QueryingDAO.getLastName` 을 이용하여 학습 테스트를 성공시키세요.

<br>

## 3.3 Object with RowMapper

<br>

queryForObject의 두 번째 매개변수에 RowMapper를 전달하여 조회 결과를 매핑할 수 있습니다.

<br> 

```java
Customer customer = jdbcTemplate.queryForObject(
        "select id, first_name, last_name from customers where id = ?",
        (resultSet, rowNum) -> {
            Customer customer = new Customer(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
            return customer;
        }, id);
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.QueryingDaoTest.findCustomerById`
- 수행 방법
  - `cholog.QueryingDAO.findCustomerById` 을 이용하여 학습 테스트를 성공시키세요.

<br>

# 4. Querying for a List

## 4.1 List with RowMapper

<br>

JdbcTemplate의 query 메서드를 이용하여 여러 개의 객체를 조회할 수 있습니다.
두 번째 매개변수에 RowMapper를 전달하여 조회 결과를 매핑할 수 있습니다.

<br>

```java
List<Customer> customers = jdbcTemplate.query(
        "select id, first_name, last_name from customers",
        (resultSet, rowNum) -> {
            Customer customer = new Customer(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
            return customer;
        });
```

<br>

queryForObject와 마찬가지로 의 세 번째 매개변수를 이용하여 쿼리문에 바인딩할 파라미터를 전달할 수 있습니다.
RowMapper의 경우 별도로 선언하여 사용할 수 있습니다.

<br>

```java
private final RowMapper<Customer> rowMapper = (resultSet, rowNum) -> {
    Customer customer = new Customer(
            resultSet.getLong("id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"));
    return customer;
};

        ...

List<Customer> customers = jdbcTemplate.query("select id, first_name, last_name from customers where first_name = ?", rowMapper, firstName);
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.QueryingDaoTest.findAllCustomers`
- 수행 방법
  - `cholog.QueryingDAO.findAllCustomers` 을 이용하여 학습 테스트를 성공시키세요.

<br>

# 5. Updating (INSERT, UPDATE, and DELETE)

<br>

JdbcTemplate을 이용하여 INSERT, UPDATE, DELETE 쿼리를 실행하는 여러가지 방법을 제공합니다.
update, batchUpdate, execute 메서드를 이용하여 쿼리를 실행할 수 있습니다.

<br>

## 5.1 Update (INSERT)

<br>

JdbcTemplate의 update 메서드를 이용하여 INSERT, UPDATE, DELETE 쿼리를 실행할 수 있습니다.

<br>

```java

jdbcTemplate.update("insert into customers (first_name, last_name) values (?, ?)", customer.getFirstName(), customer.getLastName());
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.UpdatingDaoTest.insert`
- 수행 방법
  - `cholog.UpdatingDAO.insert` 을 이용하여 학습 테스트를 성공시키세요.

<br>

## 5.2 Update (DELETE)

<br>

```java

jdbcTemplate.update("delete from customers where id = ?", Long.valueOf(id));
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.UpdatingDaoTest.delete`
- 수행 방법
  - `cholog.UpdatingDAO.delete` 을 이용하여 학습 테스트를 성공시키세요.

<br>

## 5.3 KeyHolder

<br>

JdbcTemplate을 사용하여 데이터베이스에 새로운 정보를 추가하고, 
그 때 생성된 primary key (여기서는 id)를 가져오기 위해서 KeyHolder를 사용할 수 있습니다.

<br>

```java
KeyHolder keyHolder = new GeneratedKeyHolder();
jdbcTemplate.update(connection -> {
    PreparedStatement ps = connection.prepareStatement(
            "insert into customers (first_name, last_name) values (?, ?)", 
            new String[]{"id"});
    ps.setString(1, customer.getFirstName());
    ps.setString(2, customer.getLastName());
    return ps;
}, keyHolder);

Long id = keyHolder.getKey().longValue();
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.UpdatingDaoTest.keyHolder`
- 수행 방법
  - `cholog.UpdatingDAO.insertWithKeyHolder` 을 이용하여 학습 테스트를 성공시키세요.

<br>

# 6. 참고자료
- [Spring - JdbcTemplate > Querying](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-JdbcTemplate-examples-query)
- [Spring - JdbcTemplate > Updating](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-JdbcTemplate-examples-update)
