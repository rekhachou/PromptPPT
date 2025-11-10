package com.ibm.promptppt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.ibm.promptppt.constants.PromptPptConstants;
import com.ibm.promptppt.model.User;
import com.ibm.promptppt.util.JwtTokenUtil;

@Component
public class AuthFilter extends GenericFilterBean {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserContextBean userContextBean;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            boolean whiteListUriCheck = checkWhiteListUri(httpServletRequest.getRequestURI());
            LOGGER.info("HTTP Method Type : " + httpServletRequest.getMethod());
            String jwt = this.resolveToken(httpServletRequest);
            if(whiteListUriCheck || httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            	chain.doFilter(request, response);
            	return;
            }else if (StringUtils.hasText(jwt)) {
            	LOGGER.info("Token : " + jwt);
            	User user = jwtTokenUtil.validateToken(jwt);
                if (user!=null) {
                	userContextBean.setUser(user);
                } else {
                	throw new Exception("Invalid User Token!");
                }
            } else {
            	throw new Exception("Empty jwt Token!");
            }
            chain.doFilter(request, response);
            return;

        } catch (Exception e) {
        	e.printStackTrace();
        	LOGGER.error(e.getMessage());
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
	}
	
	private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(PromptPptConstants.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(PromptPptConstants.BEARER)) {
            String jwt = bearerToken.replaceAll(PromptPptConstants.BEARER, "");
            return jwt;
        }
        return null;
    }
	
	private boolean checkWhiteListUri(String requestUri) {
		LOGGER.info("Requestion URI : " + requestUri);
		if(requestUri!=null && (requestUri.contains("/auth/") || requestUri.contains("/test/") || requestUri.contains("/createUser"))) {
			return true;
		}
		return false;
	}

}
