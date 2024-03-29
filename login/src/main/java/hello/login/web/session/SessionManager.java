package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();


    //세션생성
    public void createSession(Object value, HttpServletResponse response){

    }

}
