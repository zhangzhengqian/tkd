package com.lc.zy.common.web;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lc.zy.common.exception.InvalidTokenException;
import com.lc.zy.common.util.Globals;
import com.lc.zy.common.web.Token.Type;

public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                Type type = annotation.value();
                switch(type) {
                case NEW:
                	newToken(request);
                	break;
                case REMOVE:
                	if (isRepeatSubmit(request)) {
                        //return false;
                		throw new InvalidTokenException();
                    }
                    removeToken(request);
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

	public static void removeToken(HttpServletRequest request) {
		request.getSession(true).removeAttribute(Globals.TOKEN_SESSION_KEY);
	}

	public static void newToken(HttpServletRequest request) {
		request.getSession(true).setAttribute(Globals.TOKEN_SESSION_KEY, UUID.randomUUID().toString());
	}
    
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(Globals.TOKEN_SESSION_KEY);
        log.debug("server token ==>> {}", serverToken);
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter(Globals.TOKEN_KEY);
        log.debug("client token ==>> {}", clinetToken);
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
