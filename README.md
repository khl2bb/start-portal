# start-portal

포탈서비스개발방법론

<hr>

### 2015104146 이진우

<hr>

## 기말고사 프로젝트

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
