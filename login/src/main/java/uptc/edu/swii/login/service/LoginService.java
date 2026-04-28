package uptc.edu.swii.login.service;

import uptc.edu.swii.login.model.Login;
import uptc.edu.swii.login.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public Login save(Login login){
        login.setId(null);
        return loginRepository.save(login);
    }

    public Login findByCustomerId(String customerid){
        Optional<Login> optional = loginRepository.findByCustomerid(customerid);
        return optional.orElse(null);
    }

    public boolean auth(String customerid,String password){
        Login login = findByCustomerId(customerid);

        if(login == null){
            return false;
        }

        if(login.getPassword().equals(password)){
            return true;
        }

        return false;
    }


}