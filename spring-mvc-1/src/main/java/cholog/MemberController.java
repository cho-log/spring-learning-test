package cholog;

import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    public String world() {
        // TODO: /hello 요청 시 resources/templates/hello.html 페이지가 응답할 수 있도록 설정한다.
        // TODO: /hello?name=Brie 요청이 왔을 때 name에 해당하는 값을 View로 넘겨줄 수 있도록 설정한다.
        return null;
    }

    public Person json() {
        // TODO: /json 요청 시 {"name": "brown", "age": 20} 응답할 수 있도록 설정한다.
        return null;
    }
}
