package wang.ismy.oa.listener;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import java.util.LinkedList;
import java.util.List;

@WebListener
@Component
public class SessionListener implements HttpSessionListener {

    private List<HttpSession> sessionList=new LinkedList<>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessionList.add(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        sessionList.remove(httpSessionEvent.getSession());

    }

    public HttpSession getSessionBySessionId(String sessionId){

        for(HttpSession session:sessionList){
            if(session.getId().equalsIgnoreCase(sessionId)){
                return session;
            }
        }
        return null;
    }



}
