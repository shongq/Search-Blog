# 블로그
## 1. 블로그 검색


Request URL  
GET http://{apiDomain}/blog/search

Parameters
|Name|Type|Description||Required|
|------|---|---|---|
|query|String|검샐을 원하는 질의어|O|
|sort|String|결과 문서 정렬 방식, accuracy(정확도순), recency(최신순), 기본값 accuracy|X|
|page|Integer|결과 페이지 번호, 1~50, 기본값 1|X|
|size|Integer|한 페이지에 보여질 문서 수, 1~50, 기본값 10|X|
