# 📌BodyBuddy
>오픈 API를 활용한 건강관리 커뮤니티🖥️ <br>
>http://bodybuddy.kro.kr

<br>

## 1. 제작 기간 & 참여 인원
- 2023년 3월 10일 ~ 4월 3일
- 팀 프로젝트 5명

<br>

## 2. 사용 기술
#### `Back-end`
- Java 11
- Spring Framework 4.3.30
- Maven
- Mybatis 3.4.6
- MySQL 5.1.49

#### `Front-end`
- javascript
- JQuery
- AJAX
- Vue.js

## 3. ERD 설계 - 내 파트(보라색 ERD)
![바디바디ERD](https://github.com/lukejihwan/Bodybuddy_project/assets/111648451/577d1012-c156-43ca-8136-b792f67c7bc1)

## 4. 내역할
- 기상청API를 활용한 날씨 업데이트 기능
- 영양성분 API를 활용한 음식명 검색으로 영양분 조회 기능
- 신체, 식단, 운동기록 CRUD담당
- Google 🌎Geolocation API를 안드로이드 러닝 기록 기능과 연동

## 5. 구현기능
- 기상청 API 요청해 지역별 온도, 습도, 풍속, 강수확률 데이터를 JSON으로 변환 후 AJAX(비동기통신)로 응답받아 Front에 구현
- 달력에 선택한 날짜에 개인 신체, 운동, 식단 기록을 등록하는 기능 구현
- 등록된 신체 기록 수정, 삭제 기능 및 월간 신체기록을 선형 그래프로 구현
- Google Geolocation API사용하여 안드로이드로 등록된 러닝 기록을 웹운동기록 카테고리에서 
일자별 경로 조회 기능구현
- 일자별 식단 수정 삭제 기능 및 영양소 성분을 chart API원형 그래프 사용하여 구현

## 6. 주요기능 소개
- 달력에 선택한 날짜에 <strong>근력운동기록(운동명, kg수, 갯수, 세트수)</strong>을 등록하는 기능

<details>
<summary><b>근력운동기록 전체 설명도 펼치기</b></summary>
  
### 6.1.1 근력운동기록 전체 흐름도
![운동기능전체그림](https://github.com/lukejihwan/Bodybuddy_project/assets/111648451/1b1548e4-a2ba-410b-b35b-8c87e227d7bf)

### 6.1.2 근력운동기록 Front-end 설계
![근력운동기록Front-end편집후](https://github.com/lukejihwan/Bodybuddy_project/assets/111648451/ab7b1562-7494-43bd-ba2f-657db6571e58)

### 6.1.3 근력운동기록 Controller 설계
![근력운동기록Controller편집후](https://github.com/lukejihwan/Bodybuddy_project/assets/111648451/e3efd0e1-24bc-424e-9134-c51b7f73437f)

### 6.1.4 근력운동기록 Service 설계
![근력운동기록Service편집후](https://github.com/lukejihwan/Bodybuddy_project/assets/111648451/6b12cc9e-9ded-4341-9464-3bf4e439438c)

### 6.1.5 근력운동기록 DAO 설계
![운동기록DAO편집후](https://github.com/lukejihwan/Bodybuddy_project/assets/111648451/d9005572-81f7-4485-97e2-0ab028b215a1)
</details>










