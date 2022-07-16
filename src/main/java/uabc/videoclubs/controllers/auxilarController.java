package uabc.videoclubs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class auxilarController {

        @RequestMapping("/prueba")
        public String helloWorld() {
                return "views/";
        }

}
