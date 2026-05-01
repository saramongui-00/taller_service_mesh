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
        return loginRepository.save(login);
    }

    public Login findByCustomerId(String customerid){
        Optional<Login> optional = loginRepository.findByCustomerid(customerid);
        return optional.orElse(null);
    }

    public boolean auth(Login login){
        Optional<Login> existingLogin = loginRepository.findByCustomerid(login.getCustomerid());
        return existingLogin.isPresent() && login.getPassword().equals(existingLogin.get().getPassword());

    }

    public Login update(Login login){
        Optional<Login> existingLogin = loginRepository.findByCustomerid(login.getCustomerid());

        if(existingLogin.isPresent()){
            Login loginToUpdate = existingLogin.get();

            if(login.getCustomerid() != null){
                loginToUpdate.setCustomerid(login.getCustomerid());
            }
            if(login.getPassword() != null){
                loginToUpdate.setPassword(login.getPassword());
            }

            return loginRepository.save(loginToUpdate);
        }

        throw new RuntimeException("Login no encontrado de usuario " + login.getCustomerid());
    }


}