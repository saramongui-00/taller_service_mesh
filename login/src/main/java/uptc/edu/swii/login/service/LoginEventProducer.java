package uptc.edu.swii.login.service;

import uptc.edu.swii.login.model.Login;
import uptc.edu.swii.login.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginEventProducer {

    private static final String TOPIC_ADD = "addlogin_events";
    private static final String TOPIC_EDIT = "editlogin_events";
    private static final String TOPIC_FINDBYCUSTOMERID = "findloginbycustomerid_events";
    private static final String TOPIC_FINDALLLOGINS = "findalllogins_events";
    private static final String TOPIC_LOGIN = "login_events";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private JsonUtils jsonUtils;


    public void sendAddLoginEvent(Login login){
        String json = jsonUtils.toJson(login);
        kafkaTemplate.send(TOPIC_ADD, json);
    }

    public void sendEditLoginEvent(Login login){
        String json=jsonUtils.toJson(login);
        kafkaTemplate.send(TOPIC_EDIT, json);
    }

    public void sendLogin(Login login){
        String json=jsonUtils.toJson(login);
        kafkaTemplate.send(TOPIC_LOGIN, json);
    }

    public void sendFindByCustumerIDEvent(String customerid){
        kafkaTemplate.send(TOPIC_FINDBYCUSTOMERID, customerid);
    }

    public void sendFindAllLoginEvent(){
        kafkaTemplate.send(TOPIC_FINDALLLOGINS, "");
    }

}
