package com.gleb.ratingmovies.controller.command.request;

import com.gleb.ratingmovies.util.Attribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RequestContext {

    private static final String REFERER_HEADER = "Referer";
    private final Map<String, Object> requestAttributes;
    private final Map<String, Object> sessionAttributes;
    private final Map<String, String[]> requestParameters;
    private final String requestHeader;

    public RequestContext(HttpServletRequest req) {
        this.requestAttributes = retrieveRequestAttributes(req);
        this.requestParameters = req.getParameterMap();
        this.sessionAttributes = retrieveSessionAttributes(req);
        this.requestHeader = req.getHeader(REFERER_HEADER);
    }

    private Map<String, Object> retrieveSessionAttributes(HttpServletRequest req) {
        Map<String, Object> sessionAttributes = new HashMap<>();
        Enumeration<String> attributeNames = req.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String sessionName = attributeNames.nextElement();
            sessionAttributes.put(sessionName, req.getSession().getAttribute(sessionName));
        }
        return sessionAttributes;
    }

    private Map<String, Object> retrieveRequestAttributes(HttpServletRequest request) {
        Map<String, Object> attributes = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributes.put(attributeName, request.getAttribute(attributeName));
        }
        return attributes;
    }

    public void fillData(HttpServletRequest request, HttpServletResponse response) {
        Set<String> requestAttributeNames = this.requestAttributes.keySet();
        for (String attributeName : requestAttributeNames) {
            Object attributeValue = this.requestAttributes.get(attributeName);
            request.setAttribute(attributeName, attributeValue);
        }

        HttpSession session = request.getSession();
        Set<String> sessionAttributeNames = this.sessionAttributes.keySet();
        if (sessionAttributeNames.contains(Attribute.INVALIDATE_ATTRIBUTE)) {
            session.invalidate();
        } else {
            for (String attributeName : sessionAttributeNames) {
                Object attributeValue = this.sessionAttributes.get(attributeName);
                session.setAttribute(attributeName, attributeValue);
            }
        }
    }

    public String getRequestParameter(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        if (parameters == null) {
            return null;
        }
        return parameters[0];
    }


    public void addAttribute(String attributeName, Object attributeContent) {
        requestAttributes.put(attributeName, attributeContent);
    }

    public Set<String> getRequestAttributeNames() {
        return requestAttributes.keySet();
    }

    public Object getRequestAttribute(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public void addSession(String attributeName, Object attributeContent) {
        sessionAttributes.put(attributeName, attributeContent);
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public Set<String> getSessionAttributeNames() {
        return sessionAttributes.keySet();
    }

    public Object getSessionAttribute(String attributeName) {
        return sessionAttributes.get(attributeName);
    }


    public String getHeader() {
        return requestHeader;
    }
}
