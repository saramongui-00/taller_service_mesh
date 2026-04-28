package uptc.edu.swii.login.controller;

import co.edu.uptc.edakafka.model.Login;
import co.edu.uptc.edakafka.service.LoginEventProducer;
import co.edu.uptc.edakafka.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginEventProducer loginEventProducer;

    @Autowired
    private LoginService loginService;


    @PostMapping("/addlogin")
    public String addLogin(@RequestBody Login login){
        loginEventProducer.sendAddLoginEvent(login);
        return loginEventProducer.toString();
    }

    @PostMapping("/editlogin")
    public void editLogin(@RequestBody Login login){
        loginEventProducer.sendEditLoginEvent(login);
    }

    @GetMapping("/findlogin/{customerid}")
    public Login findByCustomerId(@PathVariable String customerid){
        return loginService.findByCustomerId(customerid);
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login){
        loginEventProducer.sendLogin(login);
        return "Procesando inicio de sesión";
    }

}