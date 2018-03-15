//package com.home.demo.flowable.spring_security.config;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.flowable.idm.api.Group;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Service;
//
//import com.home.demo.bean.Permission;
//import com.home.demo.dao.PermissionDao;
//
//@Service
//public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//
//    @Resource(name = "permissionDao")
//    private PermissionDao permissionDao;
//
//    private static String separator = ";;;";
//
//    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
//
//    private static Map<String, String> requestMap = new HashMap<>();
//
//    public void initRequestMap() {
//        requestMap.put("GET", "read");
//        requestMap.put("POST", "write");
//        requestMap.put("PUT", "update");
//        requestMap.put("PATCH", "update");
//        requestMap.put("DELETE", "delete");
//    }
//
//    public void init() {
//        if (resourceMap != null) {
//            return;
//        }
//        List<Permission> resources = permissionDao.findPermissionsByType("url");
//
//        resourceMap = new HashMap(resources.size());
//        for (Permission resource : resources) {
//            resourceMap.put(resource.getApi() + separator + resource.getCodename(),
//                    (Collection) resource.getGroups().stream().map(Group::getName).map(SecurityConfig::new).collect(Collectors.toList()));
//        }
//        initRequestMap();
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        FilterInvocation filterInvocation = ((FilterInvocation) object);
//        Iterator<String> ite = resourceMap.keySet().iterator();
//        while (ite.hasNext()) {
//            String resURLSeparator = ite.next();
//            String resURL = resURLSeparator.split(separator)[0];
//            String attr = resURLSeparator.split(separator)[1];
//            if (resURL == null) {
//                continue;
//            }
//          
//            
//            RequestMatcher urlMatcher = new AntPathRequestMatcher(resURL);
//            
//            String res_url = resURL.substring(0, resURL.indexOf("/", 1));
//            HttpServletRequest request = filterInvocation.getHttpRequest();
//            String url = request.getServletPath();
//            if(url != null && url.endsWith(res_url)) {
//            		url += "/";
//	        }
//            if (urlMatcher.matches(filterInvocation.getHttpRequest()) ||  (url.matches(resURL))) {
//                if (attr.startsWith(requestMap.get(filterInvocation.getHttpRequest().getMethod()))) {
//                    System.out.println("map:" + resURL + ";need:" + resourceMap.get(resURLSeparator));
//                    return resourceMap.get(resURLSeparator);
//                }
//            }
//            
//            
//            
//        }
//        return null;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        init();
//        Collection attrs = new ArrayList();
//        for (Collection attr : resourceMap.values()) {
//            attrs.addAll(attr);
//        }
//        return attrs;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
////        return FilterInvocation.class.isAssignableFrom(clazz);
//    }
//}
