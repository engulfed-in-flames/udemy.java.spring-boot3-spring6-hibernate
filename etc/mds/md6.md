# Advanced Mappings

일반적으로 DB에서 하나의 테이블만을 조회하는 경우는 적다. 둘 이상의, 또는 연관 테이블들을 함께 조회하는 경우가 일반적이다. 이 경우에, 스프링에서 하이버네이트로 핸들링 할 수 있다.

## Entity Lifecycle

- Detach - 엔터티가 일단 분리되면, 하이버네이트 세션과 연결되지 않는다.
- Merge - 엔터티가 분리되면, 세션에 다시 연결한다.
- Persist - 다음 플러쉬 또는 커밋 시에 새 인스턴스를 DB에 영속화한다.
- Remove - 다음 플러쉬 또는 커밋 시에 인스턴스를 DB에서 제거한다.
- Refresh - DB 데이터를 동기화한다.

## @OneToOne - Cascade Types

- PERSIST - 엔터티가 영속화되면, FK 엔터티도 영속화된다.
- REMOVE - 엔터티가 제거되면, FK 엔터티도 제거된다.
- REFRESH - 엔터티가 ~ 되면, FK 엔터티도 ~
- DETACH - ~, ~
- MERGE - ~, ~
- ALL - All of the above cascade types
