package uptc.edu.swii.login.controller;

import uptc.edu.swii.login.model.Login;
import uptc.edu.swii.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/addlogin")
    public String addLogin(@RequestBody Login login){
        return loginService.save(login).toString();
    }

    @PostMapping("/editlogin")
    public void editLogin(@RequestBody Login login){
        loginService.update(login);
    }

    @GetMapping("/findlogin/{customerid}")
    public Login findByCustomerId(@PathVariable String customerid){
        return loginService.findByCustomerId(customerid);
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login){
       boolean auth = loginService.auth(login);
       if (auth){
           return "Inicio de sesion correcto";
       }else {
           return "credenciales incorrectas";
       }
    }

}