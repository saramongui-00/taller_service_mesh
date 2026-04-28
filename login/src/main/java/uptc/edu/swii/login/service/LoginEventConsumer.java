package uptc.edu.swii.login.service;

import uptc.edu.swii.login.dto.Customer;
import uptc.edu.swii.login.dto.CustomerRequest;
import uptc.edu.swii.login.event.CustomerEvent;
import uptc.edu.swii.login.model.Login;
import uptc.edu.swii.login.utils.JsonUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoginEventConsumer {

    private final LoginService loginService;
    private final JsonUtils jsonUtils;

    public LoginEventConsumer(LoginService loginService,
                              JsonUtils jsonUtils){
        this.loginService = loginService;
        this.jsonUtils = jsonUtils;
    }

    @KafkaListener(topics = "addlogin_events", groupId = "login_group")
    public void handleLoginCreated(String loginJson){
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        loginService.save(login);
    }

    @KafkaListener(topics = "editlogin_events", groupId = "login_group")
    public void handleEditLoginCreated(String loginJson){
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        loginService.save(login);
    }

    @KafkaListener(topics = "customer_events", groupId = "login_group")
    public void handleCustomerEvents(String eventJson){

        CustomerEvent event = jsonUtils.fromJson(eventJson, CustomerEvent.class);

        if(!event.getEventType().equals("ADD_CUSTOMER")){
            return;
        }

        CustomerRequest customer =
                jsonUtils.fromJson(event.getData(), CustomerRequest.class);

        Login existing = loginService.findByCustomerId(customer.getDocument());
        if(existing != null){
            return;
        }

        Login login = new Login();
        login.setId(null);
        login.setCustomerid(customer.getDocument());
        login.setPassword(customer.getPassword());

        loginService.save(login);

        System.out.println("Login creado automáticamente para el cliente "
                + customer.getDocument());
    }

    @KafkaListener(topics = "login_events", groupId = "login_group")
    public void handleLogin(String loginJson){
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        boolean auth = loginService.auth(login.getCustomerid(), login.getPassword());
    }
}