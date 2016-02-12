package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Bogdan on 2/7/2016.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String method() {
        return ("/");
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String method2() {
        return ("/");
    }
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String method3() {
        return ("/");
    }
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String method2() {
        return ("/index");
    }*/
}
