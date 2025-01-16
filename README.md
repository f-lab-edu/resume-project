# 이력서 프로젝트 

이 프로젝트는 핵사고날 아키텍처(Hexagonal Architecture)를 기반으로 설계된 이력서 관리 시스템입니다.

주요 기능으로는 이력서의 생성, 수정, 삭제, 조회를 지원하며, 읽기/쓰기 작업의 분리를 통해 성능과 유지보수성을 개선했습니다.

아키텍처 구성

핵사고날 아키텍처 개요
핵사고날 아키텍처는 시스템을 도메인 중심으로 설계하고, 외부 의존성을 도메인 경계 외부로 밀어냅니다.
이는 시스템의 유연성과 테스트 가능성을 높이며, 비즈니스 로직이 외부 구현 세부사항에 의존하지 않도록 보장합니다.

레이어 구성
1) Adapter (어댑터 계층) : 외부 요청을 처리하고, 도메인 계층과 상호작용합니다.
   1) controller: REST API 요청 및 응답 처리
   2) payload: 요청(Request) 및 응답(Response) 데이터 객체 정의
   3) persistence: JPA를 통한 데이터베이스 접근
   4) proxy: Controller와 Application 계층(유스케이스)을 연결하는 중간 계층
   5) converter: 데이터 변환(엔티티 ↔ DTO)

2) Application (응용 계층) : 유스케이스를 구현하고, 도메인 로직을 호출합니다.
   1) service: 읽기(Query)와 쓰기(Command) 작업을 분리하여 책임을 명확히 정의.
   2) usecase: 유스케이스 인터페이스 정의.

3) Domain (도메인 계층) : 핵심 비즈니스 로직과 규칙을 캡슐화합니다.
   1) entity: 도메인 엔티티 정의.
   2) value: 값 객체(Value Object) 정의.
   3) service: 복잡한 비즈니스 로직 구현.
   4) dto: 데이터 전송 객체.

4) Infrastructure (인프라 계층) : 외부 기술 및 설정 관련 구현을 담당합니다.
   1) config: 스프링 설정(Spring Configuration) 클래스.

