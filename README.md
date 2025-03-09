# 🔶 Simple Hexagonal CRUD API Server

이 프로젝트는 **Spring Boot 기반으로 개발**되었으며,  
**애플리케이션 코드는 Java, 테스트 코드는 Kotlin**을 활용하여 작성된 **헥사고날 아키텍처(Hexagonal Architecture)** 기반의 **CRUD API 서버**입니다.

## ✨ 주요 기능

- **게시판 CRUD 기능** (게시글 생성, 조회, 수정, 삭제)
- **헥사고날 아키텍처 적용** (adapter, application, domain, port 분리)
- **RESTful API** 기반의 데이터 처리
- **PostgreSQL을 Docker Compose로 실행**

## 🛠 기술 스택

- **프레임워크:** Spring Boot
- **언어:** Java (메인 코드), Kotlin (테스트 코드)
- **빌드 도구:** Gradle
- **데이터베이스:** PostgreSQL (Docker Compose 사용)
- **ORM:** JPA (Java Persistence API)
- **DTO 변환:** MapStruct (Mapper)
- **컨테이너화:** PostgreSQL을 Docker Compose로 실행

## 📂 프로젝트 구조

```bash
me.nettee/
├── common/                     # 공통 유틸리티 및 전역 기능
└── board/                      # 게시판 도메인 관련 코드
    ├── adapter/                # 외부와의 상호작용을 처리하는 계층
    │   ├── driving/            # 외부로부터의 입력을 처리하는 어댑터
    │   │   └── web/            # Web 요청을 처리하는 컨트롤러 및 DTO 정의
    │   │       ├── dto/
    │   │       └── mapper/     # DTO ↔ Domain 매핑 클래스
    │   └── driven/             # 외부로의 출력을 처리하는 어댑터 (DB, Event, Messaging)
    │       └── persistence/    # 영속성 계층의 구현체 (JPA)
    │           ├── entity/
    │           └── mapper/     # Entity ↔ Domain 매핑 클래스
    ├── application/            # 핵심 비즈니스 로직이 포함된 계층
    │   ├── domain/             # 비즈니스 도메인 정의
    │   ├── port/               # Adapter와 상호작용을 위한 인터페이스 정의
    │   ├── service/            # 비즈니스 로직을 처리하는 서비스 클래스
    │   └── usecase/            # 특정 유스케이스(기능) 인터페이스 정의
└── test/                       # 테스트 코드 (Kotlin 사용)
    └── me.nettee.board/        # Board 도메인 테스트
```

---

## 🚀 설치 및 실행 방법

### 1. 프로젝트 다운로드

```bash
git clone https://github.com/shin-jingyu/demo-hexagonal-simple.git
```

### 2. 사전 준비 사항
- Docker Compose가 설치되어 있어야 합니다.
  [Docker Desktop 설치 가이드](https://www.docker.com/products/docker-desktop)
- Docker가 실행 중이어야 합니다.

### 3. 데이터베이스 실행 (Docker Compose)

```bash
docker compose up -d
```

### 4. 컨테이너 종료 방법
- 컨테이너 내리기 (데이터 보존)

  ```bash
  docker compose down
  ```
  
- 컨테이너 내리기 (데이터 삭제)

  ```bash
  docker compose down -v
  ```

### 5. VM 옵션에 액티브 프로파일 입력

<details>
<summary>액티브 프로파일 입력란이 있을 때</summary>

다음을 입력합니다.

```shell
local
```
</details>
<details>
<summary>VM Option을 직접 입력할 때</summary>

다음을 입력합니다.

```shell
-Dspring.profiles.active=local
```

</details>

