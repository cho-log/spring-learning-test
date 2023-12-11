package cholog;

import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    public String world() {
        // TODO: /hello 요청 시 resources/templates/hello.html 페이지가 응답할 수 있도록 설정하세요.
        // TODO: 쿼리 파라미터로 name 요청이 들어왔을 때 해당 값을 hello.html에서 사용할 수 있도록 하세요.
        return null;
    }

    public Person json() {
        // TODO: /json 요청 시 {"name": "brown", "age": 20} 데이터를 응답할 수 있도록 설정하세요.
        return null;
    }
}
