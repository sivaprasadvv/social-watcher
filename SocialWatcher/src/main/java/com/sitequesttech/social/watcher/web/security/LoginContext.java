package com.sitequesttech.social.watcher.web.security;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Get Spring security context to access user data security infos.
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
public class LoginContext {
    /**
     * Get the current username. Note that it may not correspond to a username
     * that
     * currently exists in your users' repository; it could be a spring
     * security
     * 'anonymous user'.
     * 
     * @return the current user's username, or null if none.
     */
    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }

            return principal.toString();
        }

        return null;
    }

    /**
     * return the current locale
     */
    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    /**
     * Retrieve the current UserDetails bound to the current thread by Spring
     * Security, if any.
     */
    public static UserDetails getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if ((auth != null) && (auth.getPrincipal() instanceof UserDetails)) {
            return ((UserDetails) auth.getPrincipal());
        }

        return null;
    }

    /**
     * Return current roles bound to the current thread by Spring Security
     * @return roles list
     */
    public static List<String> getRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            List<String> result = newArrayList();

            for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                result.add(grantedAuthority.getAuthority());
            }
            return result;
        }
        return Collections.emptyList();
    }
    
    public static Map<String, String> getRolesAsMap() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Map<String, String> result = new HashMap<String, String>();

            for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                result.put(grantedAuthority.getAuthority(), grantedAuthority.getAuthority());
            }
            return result;
        }
        return Collections.emptyMap();
    }
    
    public static String getRole() {
    	
    	String role = null;
        List<String> roles = getRoles();
        if (null != roles && roles.size() > 0) {
        	for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				
				if (string.equals("admin")){
					role = "admin";
					break;
				}
				
				if (string.equals("user")){
					role = "user";
					break;
				}
				
			}
        }
        return role;
    }
    
    public static String getUserRole() {
    	
    	String role = null;
        Map<String, String> roles = getRolesAsMap();
        if (null != roles && roles.size() > 0) {
        	
        	if (roles.containsKey("admin"))
        		return "admin";
        	
        	if (roles.containsKey("partner"))
        		return "partner";
        	
        	if (roles.containsKey("client"))
        		return "client";
        	
        	if (roles.containsKey("user"))
        		return "user";
        }
        return role;
    }

}
