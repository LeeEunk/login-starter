package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String SessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null){
            return "세션이 없습니다.";
        }

        // 우리가 보관한 세션 데이터들 모두 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId());
        log.info("MaxInactiveInterval={}", session.getMaxInactiveInterval()); //세션의 유효 시간, 예) 1800초, (30분)
        log.info("CreationTime={}", new Date(session.getCreationTime())); // 세션 시작 접근 시간 
        log.info("LastAccessedTime={}",new Date(session.getLastAccessedTime())); // 세션 마지막 접근 시간
        log.info("isNew={}", session.isNew()); // 새로운 세션이냐 아니냐

        return "세션 출력";
    }
}
