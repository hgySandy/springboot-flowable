package com.home.demo.flowable.spring_security.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.GroupQuery;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.GroupQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityManagerImpl;
import org.flowable.idm.engine.impl.persistence.entity.data.GroupDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.home.demo.service.impl.UserDetailsServiceImpl;

public class SpringSecurityGroupManager extends GroupEntityManagerImpl {

	//private JdbcUserDetailsManager userManager;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public SpringSecurityGroupManager(IdmEngineConfiguration idmEngineConfiguration, GroupDataManager groupDataManager) {
        super(idmEngineConfiguration, groupDataManager);
    }
    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query) {

        if (query.getUserId() != null) {
            return findGroupsByUser(query.getUserId());
        }
        return null;
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        return findGroupByQueryCriteria(query).size();
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
    		//下面这行代码中loadUserByUsername是加载默认的spring security的表结构中的数据
    		//UserDetails userDetails = userManager.loadUserByUsername(userId);  
        //实际应该取我们自定义表格中的数据
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId); 
        System.out.println("group manager");
        if (userDetails != null) {
            List<Group> groups = userDetails.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .map(a -> {
                    Group g = new GroupEntityImpl();
                    g.setId(a);
                    return g;
                })
                .collect(Collectors.toList());
            return groups;
        }
        return null;
    }

//    public void setUserManager(JdbcUserDetailsManager userManager) {
//        this.userManager = userManager;
//    }
    
    @Override
    public Group createNewGroup(String groupId) {
        throw new UnsupportedOperationException("This operation is not supported!");

    }

    @Override
    public void delete(String groupId) {
        throw new UnsupportedOperationException("This operation is not supported!");

    }
    @Override
    public GroupQuery createNewGroupQuery() {
        throw new UnsupportedOperationException("This operation is not supported!");
    }

    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException("This operation is not supported!");
    }
    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException("This operation is not supported!");
    }

}
