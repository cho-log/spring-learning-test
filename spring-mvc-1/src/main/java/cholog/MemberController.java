package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MemberController {

    public Model world() {
        // TODO: /hello 요청 시 resources/templates/hello.html 페이지가 응답할 수 있도록 설정한다.
        return null;
    }

    public Person json() {
        // TODO: /json 요청 시 {"name": "brown", "age": 20} 응답할 수 있도록 설정한다.
        return null;
    }
}
