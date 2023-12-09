package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {
    @GetMapping("/template")
    public String template(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "template";
    }

    @GetMapping("/person")
    @ResponseBody
    public Person person() {
        return new Person("Brown", 20);
    }
}
