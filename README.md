① 프로젝트 소개

프로젝트 주제
    
    영화정보 등록 웹페이지

Render 배포 URL

    https://github.com/2026-2-WebService/assign06-c01-22300644

프로젝트 구조

    Layered Architecture를 적용하였습니다.
    
    controller: HTTP의 요청을 받아 Model에 데이터를 담고 HTML 화면 반환
    
    service: 예외 처리 및 비즈니스 로직 처리, DTO 변환
    
    repository: 메모리를 기반으로 데이터 저장 및 조회
    
    domain/dto: 영화 데이터 구조 및 폼 입력 데이터를 전달받을 객체
    
    templates: Thymeleaf와 Bootstrap을 이용해 사용자에게 보여줄 HTML 화면

실행 방법
    
    IntelliJ에서 해당 폴더를 열고 Week05Application.java 실행->
    브라우저에서 링크(http://localhost:8080/movies) 접속 -> 상황에 맞게 버튼을 누르거나 URL 입력

주요 화면 Web Endpoint 표

| 기능     | Method | URL | 결과                |
|--------|   ---  | --- |-------------------|
| 전제목록   | GET | `/movies` | 영화 목록 화면출력        |
| 등록form | GET | `/movies` | 신규영화등록 화면         |
| 데이터등록  | POST | `/movies/{id}` | 데이터 저장 후 Redirect |
        

데이터 등록

    목록 화면과 등록 화면의 실행 결과 Screenshot을 포함합니다.

② 이번 과제 키워드

    @Controller

    Thymeleaf
    
    Model
    
    @ModelAttribute
    
    HTML Form
    
    redirect:
    
    PRG Pattern
    
    Bootstrap 5
    
    Layered Architecture


③ 핵심 내용 정리

    @Controller와 @RestController의 차이는 어떻게 되나요?

    기존에는 데이터를 JSON으로 바로 뱉어내는 용도였다면, 이번에는 @Controller를 달고 Model에  
    데이터를 담아서사용자에게 보여줄 HTML 화면 이름을 반환하는 역할로 바뀌었습니다.
    
    Controller에서 HTML로 데이터를 어떻게 넘기나요?

    Controller의 메서드 매개변수로 Model을 받고, model.addAttribute를 사용해 넘겨줍니다. 
    그러면 Thymeleaf HTML에서 th:each나 th:text로 그 데이터를 꺼내서 반복 출력할 수 있습니다.
    
    HTML Form 데이터는 어떻게 받아오나요?

    클라이언트가 HTML Form에 입력한 값들을 Controller에서 @ModelAttribute를 사용해
    통째로 DTO 객체(MovieRequest)로 묶어서 받아올 수 있습니다. 이때 Form의 input name과 DTO 필드명이 같아야 됩니다.
    
    새로고침 시 중복 등록 방지는 어떻게 하나요?

    POST로 데이터를 등록하고 나서 바로 화면을 띄우지 않고, return "redirect:/movies" 를 썼습니다.
    이렇게 하면 저장이 끝난 후 GET 방식으로 다시 목록 화면을 부르기 때문에 새로고침해도 중복 등록되지 않습니다.


④ Weekly Report

Key Learning
    
    코드에 Bootstrap과 Thymeleaf를 붙여서 눈에 보이는 화면을 만드는 법을 알게 되었습니다.

    Browser → Controller → Service → Repository → Model → Thymeleaf → HTML로 이어지는 전체 실행 흐름을 이해했습니다.
    
    HTML Form에서 데이터를 입력하고 POST 요청을 보내면, 저장 후 redirect를 통해 다시 화면을 그리는 흐름을 알게 되었습니다.

Web UI Flow
Browser → Controller → Service → Repository → Model → Thymeleaf → HTML의 실행 흐름
    
    Browser: 주소창에 /movies를 입력하거나 링크를 클릭하여 서버로 GET 요청을 보냅니다.

    Controller: @GetMapping이 매핑된 컨트롤러가 요청을 받고, 데이터를 가져오기 위해 Service를 호출합니다.
    
    Service & Repository: Service는 Repository를 호출하고, Repository는 데이터 저장소에서 전체 영화 목록 데이터를 반환합니다.
    
    Model: Controller는 가져온 영화 데이터에 이름을 붙여 model.addAttribute를 통해 Model 객체에 담아둡니다.
    
    Thymeleaf: 서버는 list.html 템플릿 파일을 열고, Model에 담긴 데이터를 th:each 등의 문법을 사용해 묶어 줍니다.
    
    HTML: 완성된 HTML 문서가 브라우저로 응답되어 화면에 Table로 출력됩니다.

Form Flow
HTML Form → Controller → Service → Repository → redirect → GET의 실행 흐름
    
    HTML Form: 사용자가 /movies/new 화면에서 영화 제목, 감독 등을 입력하고 '등록' 버튼을 누르면, 
    브라우저가 입력된 값들을 모아 HTTP POST로 서버에 전송합니다.

    Controller: @PostMapping이 매핑된 컨트롤러가 요청을 받습니다.이때 @ModelAttribute가 동작하여 HTML Form의 name 속성과
    일치하는 MovieRequest 객체의 필드들에 값들을 통째로 연결해 줍니다.
    
    Service & Repository: Controller는 데이터가 담긴 객체를 Service로 넘기고,
    Service는 Repository의 save 메서드를 호출해 메모리에 새 영화 데이터를 최종 저장합니다.
    
    redirect: 데이터 저장이 무사히 끝나면 Controller는 특정 HTML 파일을 반환하는 대신 return "redirect:/movies"를 반환합니다. 
    이는 브라우저에게 상태 코드(302)를 보냅니다.
    
    GET: 응답을 받은 브라우저는 자동으로 /movies 경로로 새로운 GET 요청을 보냅니다. 이 구조를 PRG(Post-Redirect-Get) 패턴이라고 부르며,
    사용자가 등록 완료 후 실수로 새로고침을 누르더라도 데이터 등록이 중복해서 발생하지 않도록 막아주는 아주 중요한 역할을 합니다.

Problem & Solution

    처음에는 Controller에서 가져온 데이터를 어떻게 HTML 화면으로 넘겨주고 연결해야 하는지 그 방법을 잘 몰라서 막혔습니다. 
    그래서 강의 자료와 AI을 통해 찾아보았고, Controller에서 Model 객체에 데이터를 담아 넘겨준 뒤 HTML 화면에서 
    Thymeleaf 문법을 사용해 꺼내 쓰는 구조라는 것을 알게 되어 무사히 연결을 해결할 수 있었습니다.

Code Review

    @PostMapping
    public String Post(@ModelAttribute MovieRequest request){
        movieService.Create(request);
        return "redirect:/movies";
    }

    기존의 JSON 방식에서 웹 폼 방식으로 바뀌면서 @ModelAttribute를 쓰고, redirect:/movies로 마무리하는 이 부분이
    이번 과제의 핵심인 PRG 패턴을 가장 잘 보여준다고 생각해서 골랐습니다.

AI Usage

    화면을 만들때 Bootstrap 5를 적용해서 뼈대와 테이블 디자인을 만드는 데 AI의 도움을 받았습니다.
    만들어진 코드에서 input의 name들이 MovieRequest 변수들이랑 정확히 맞는지확인 했습니다.

Reflection

    저번 과제까지는 화면 없이 데이터로만 동작을 확인했는데, 이번에는 실제 버튼이 있고 데이터가 추가되는 웹 페이지가 만들어지는 걸 
    보니 확실히 더 재밌고 직관적으로 이해가 되었습니다.
