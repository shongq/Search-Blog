# 블로그
## 1. 블로그 검색 : 파라미터 조건에 따른 블로그 검색

### 기본정보

- `GET` /blog/search  


### Request
|Parameters|Name| Description                                             | Required |
|------|---|---------------------------------------------------------|----------|
|query|String| 검색을 원하는 질의어                                             | O        |
|sort|String| 결과 문서 정렬 방식, accuracy(정확도순), recency(최신순), 기본값 accuracy | X        |
|page|Integer| 결과 페이지 번호, 1과 50사이, 기본값 1                               | X        |
|size|Integer| 한 페이지에 보여질 문서 수, 1과 50, 기본값 10                          | X        |

### Response
status : 응답 코드  
result  
  1) meta

| Parameters     | Name    |Description|
|----------------|---------|---|
| total_count    | Integer |검색된 문서 수|
| pageable_count | Integer |total_count 중 노출 가능 문서 수|
| is_end         | Boolean |현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음|

  2) documents

|Parameters|Name|Description|
|------|---|---|
|title|String|블로그 글 제목|
|contents|String|블로그 글 요약|
|url|String|블로그 글 URL|
|blogname|String|블로그의 이름|
|thumbnail|String|검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음|
|datetime|Datetime|블로그 글 작성시간, ISO 8601|

---

---
## 2. 인기 검색어 목록 : 검색이 많은 순으로 최대 10개의 키워드 목록 조회
- `GET` /blog/list/keyword  


### Response
status : 응답 코드  
result

| Parameters  | Name    | Description |
|-------------|---------|-------------|
| word        | String  | 인기 검색어      |
| searchCount | Integer | 인기 검색어 검색수  |

---

---
## 참고사항
### * 요구사항에 대한 처리
1. _기본 블로그 검색은 카카오 OpenAPI로 하되 이외 검색소스가 추가될 수 있음을 고려_ : API 호출 시 Request를 `인터페이스(IHandlerRequest.java)를 상속`받아 만들도록 하였고 
Response는 ResponseEntity로 통일시켰습니다. 또한 `Service, Handler도 추상클래스, 인터페이스를 구현(상속)`하도록 하였습니다.
마지막으로 `Chain of Responsibility 디자인패턴을 적용`하여 추후 `Handler 처리 순서 변경, 추가, 삭제를 유연하게` 할 수 있도록 개발했습니다.
2. _트래픽이 많고, 저장된 데이터가 많음_ : 단일서버 구성이라 `ehcache를 활용하여 API 결과값을 캐싱처리` 하였습니다. 
만약, 실제 운영에서 사용하기 위해 개발한다면 다중서버 구성일 것이므로 결과값을 Redis에 저장하고 불러오는 방법으로 구현할 것 같습니다.
3. _동시성 이슈 발생 가능_ : `ConcurrentHashMap`의 key로 검색되는 키워드를 저장하고 value로 키워드에 대한 처리여부를 저장하여 동시성 제어를 구현하였습니다.
   (LocalLockProvider.java)

### * 사용한 오픈소스
1. 하이버네이트 : JPA 구현체로 사용
2. OpenFeign : Rest API call을 위해 사용
3. ehcache : api 결과값 캐싱을 위해 사용 (단일 서버 구성이기 때문에 레디스를 사용하지 않고 간단히 사용할 수 있는 ehcache 활용)
4. Jackson : Request, Response 컨트롤을 위해 사용
5. p6spy : JPQL쿼리의 파라미터를 조회하기 위해 사용
6. jasypt : 프로퍼티를 암호화하기 위해 사용
