# start-portal

포탈서비스개발방법론

<hr>

### 2015104146 이진우

<hr>

## 기말고사 프로젝트

'''
기말 프로젝트 공지드립니다.
SpringBoot, Spring MVC, JPA, Mysql 을 활용하여 개인이 스스로 기획해서 한번 만들어보세요.
어떤 내용이던 관계없습니다.
UI 는 Rest Api + JavaScript 를 활용해도 좋고, UI Template을 활용해도 좋습니다.
결과는 6월 26일 강의실에서 실제 구현되는 환경을 발표해주세요.
프로젝트 진행하실때 commit 로그를 자주 남겨주세요.
commit 로그의 내용을 기반으로 본인이 직접 개발했는지를 판단합니다.
지금부터 준비하셔야 6월 26일 결과물을 볼 수 있을거에요.
성적 기준 알려드립니다.
강의 실습 진도 (50%)
프로젝트 (50%)
프로젝트 점수는 상대평가로 진행합니다. 프로젝트 난이도와 수행결과 및 코드 퀄리티를 상대평가로 평가합니다.
여러분들이 담합하여 난이도 조율? 하셔도 관계없습니다. ㅎ
학점은 정상적으로 모두(프로젝트까지) 따라오셨다면 B0 부터 학점이 나가게 되고, 정상적으로 모두 따라온 학생들을 기준으로 50%(A+ 또는 A0) 50\$​
아이고 다쓰기전에 리턴되었네요..
학점은 정상적으로 모두(프로젝트까지) 따라오셨다면 B0 부터 학점이 나가게 되고, 정상적으로 모두 따라온 학생들을 기준으로 50%(A+ 또는 A0) 50%(B+ 또는 B0) 로 나갈거구요.. 프로젝트 미제출시 C 이하로 평가됩니다.
프로젝트 평가는 6월 26일 오후 4시에 강의실에서 뵙는걸로 하고요.. 1:1 면담 방식으로 진행할거에요.
오프라인으로 평가 면담이 어려우신분은 말씀주시면 6월 26일 이전에 날 잡아서 google meet 로 화상 면담 진행하겠습니다.
'''

<hr>

### 2020년 6월 13일 토요일

- 포털서비스개발방법론 프로젝트 아이디어를 이것저것 생각해보았습니다.
- 요즘 논문 쓰는 게 있어서 정신이 없지만, 최대한 기억을 살려서 배운 것을 활용해보려고 합니다.
- 아이디어 구상
- 고급웹개발론에서 간단한 todo 사이트를 본 기억이 납니다.
  > 평소 네이버 메모, 구글 킵스 등 다양한 메모 사이트를 써봤지만, 별로 만족감을 못 느끼는 상황입니다. 그런데 이제 백앤드를 다루는 것을 해볼 기회가 왔으니 나만의 메모 사이트를 만들어보는 것은 어떨까 생각했습니다.

1.  기존 프로젝트를 복사해서, html 프론트앤드를 먼저 넣어보기
2.  mysql에 메모 body string이 얼마나 들어갈지 보기
3.  get 방식으로 서버에 있는 메모를 불러오고, post 방식으로 메모를 등록,생성 하기

### 2020년 6월 14일 일요일

- 단순히 js 에서 포스트를 날리는 것보다 스프링을 사용하려고 했습니다.
- todo보다는 메모를 남기는 것, 그리고 메모를 마크다운으로 표현하는 것에 중점을 맞춥니다.
- todo 사이트 기존 코드를 view 쪽에 집어 넣어서 단순하게 열기만 해보려고 시도 했으나
- js, css 자원이 연결이 안되네요. 방법을 찾아봐야겠습니다.

1.  기존 todo 사이트를 넣어보기, 컨트롤러로 url만 연결해주기
2.  html과 js css 연결 찾아보기
    > 연결법을 찾았습니다. 출처: 개인블로그
    > https://m.blog.naver.com/PostView.nhn?blogId=reilove333&logNo=220744058086&proxyReferer=https:%2F%2Fwww.google.com%2F, https://m.blog.naver.com/PostView.nhn?blogId=javaking75&logNo=220074858236&proxyReferer=https:%2F%2Fwww.google.com%2F
    > 대부분 xml 기반의 파일에서 스프링 세팅을 하는 것으로 해결하네요. 수업에서는 annotation 기반으로 다 바꾸었기 때문에 xml파일을 만들지 않고 하는 방법을 찾아보려구요.

- 1. dispatcher-servlet.xml 안에 선언
     <mvc:resources mapping="/resource/\*\*" location="/WEB-INF/view/" />
- 2. jsp 파일에서 위의 "/resource/" 형태로 접근하도록 작성
     <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
     <script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
  <hr>

### 2020년 6월 15일 월요일

- js, css 연결, annotation resource mapping 사용법 찾기
- 스프링 resource annotation 사용법을 찾아봅시다. https://gmlwjd9405.github.io/2018/12/02/ spring-annotation-types.html, https://freehoon.tistory.com/84, https://cornswrold.tistory.com/8
- 블로그 참고 스프링 부트를 사용해서 todo사이트를 만들어볼까? https://alwayspr.tistory.com/33 첨부터 다시 시작
- 오픈소스를 활용할까 https://todobackend.com/
- 나만의 메모장을 만들기보다는 지금은 시간도 부족하고 아는 것도 많지 않으니까, todo 오픈 소스를 참고하면서 공부하듯이 플젝을 해야겠다.
- todo 오픈 소스를 봐도 전에 봤던 코드들이랑 조금씩 설정이 달라서 따라하기가 쉽지 않았다.
- 참고한 todo 오픈 소스 https://github.com/jcsantosbr/todo-backend-spring4-java8
- todo 말고 userDao를 한 번 보았으니까, userDao나 로그인, 아니면 게시판 같은 사이트를 해보자.
- jsp를 다루는 것부터 익혀야겠다.
- jsp 사이트를 따라하려 하니 조금 옛날 버전이어서 https://www.youtube.com/watch?v=MtxFWczSFqU&list=PLRx0vPvlEmdAZv_okJzox5wj2gG_fNh_6&index=2
- 스프링 책을 구해서 보고 진행 해봐야겠다.
- 스프링 부트를 배워서 써봐야겠습니다. https://private.tistory.com/36?category=655784 (블로그)
- 2020.01 블로그 https://velog.io/@emawlrdl/Spring-project-%EC%A0%9C%EC%9E%91-%EA%B3%BC%EC%A0%95-8yk5n8bogp
- 자바스크립트는 MDN에 검색하면서 공부했는데, 스프링은 어떤지 찾아봐야겠다.

### 2020년 6월 17일 수요일

- '코드로 배우는 스프링 웹 프로젝트', '토비의 스프링 3.1' 책을 구하였습니다.
  ...
  교수님의 강의를 계속 다시 봐서 익혀야겠습니다.
  ...

### 2020년 6월 19일 금요일

- 책에 나온 코드도 결국 인터넷에 있는 예제들과 똑같았습니다.
- 이제 알았으니, 교수님의 강의를 다시 보고, 책도 보고, 인터넷도 보면서, 모든 것을 활용해서 만들어나갈 때가 왔습니다.
- 부딪히면서 하나하나 고쳐나가는 겁니다.
- ...
- 교수님 강의 (12)를 듣고 나니 스프링부트를 해야겠다고 마음 먹었습니다.
- 스프링부트 intellij mysql gradle을 사용하는 예제가 있는지 인터넷에서 찾아봅니다.
- 우선 게시판도 메모니까 게시판 사이트를 만들어봅니다. 3fc42d4
- 실패 6b09bcf

### 2020년 6월 20일 토요일

- 강의 복습, jsp js 불러오기 테스트 1aa9501

<hr>

<hr>

### 2020년 6월 21일 일요일

- 프로젝트 생성 87ef970
- 기본 html 파일 맵핑 8caf19d
- 프론트앤드 인터페이스 설계 d69ac59
<hr>

- 메모를 저장하기 위한 Data Transfer Object 생성
<hr>

- MemoDto, MemoEntity 생성
- Memo Data Transfer Object와 MemoEntity를 만들었습니다
- id, title, tag, content 생성시간, 수정시간 정도로 구성했습니다.
- @Entity @Table 어노테이션을 위해 javax.persistence를 다운로드했습니다.
<hr>

- 주석 추가하면서 검토하고 공부하기
- MemoRepository로 JpaReposiroty 인터페이스 상속
- MemoService로  서비스 계층, save 저장 기능 구현 준비
<hr>

- 컨트롤러에 memoService.savePost(memoDto); 를 통해서
- DB에 정상적으로 저장되는 것을 확인했습니다.
- 근데 생성시각 수정시각이 잘 기입이 안되는 것 같아서 차차 수정하겠습니다.
<hr>

- 메모를 조회하는 memoList.html  /list  맵핑 했습니다.
- MemoService에 기능을 만들었습니다. memo들을 전부 가져와서 리스트로 만들었습니다. 이는 html에서 보여지게 할 때 사용합니다.
- 부트스트랩 css를 조금 가져왔습니다.
- html을 조금 수정 했습니다.
- 저번 커밋에서 시간이 제대로 입력이 안 되었는데, timeEnitiy를 memoEntity가 상속하는 것으로 해결하였습니다.
<hr>

- detail.html에서 메모를 하나씩 살펴보는 기능입니다.
- controller 에서 detail 부분에서 맵핑을 했구요
- MemoService 에서는 getPost를 추가했습니다.
<hr>

- 메모 수정 기능 완료
  > 메모 하나 씩 보기 기능(detail.html)에서 수정을 누르면
post/edit/id 로 가게 되고 post/edit/id로 가면 메모 작성 공간으로 가고, 메모 내용을 불러오게 된다.
거기서 작업하고 수정을 누르면 memoService.savePost() 가 작동되고
이미 DB에 존재하는 id 여서 update(수정)으로 처리된다.
<hr>

- 메모 삭제 기능 완료
- MemoService에서 deletePost 를 통해 삭제
- boardRepository 기능을 이용해 간단하게 구현.
- boardRepository.deleteById(id);
