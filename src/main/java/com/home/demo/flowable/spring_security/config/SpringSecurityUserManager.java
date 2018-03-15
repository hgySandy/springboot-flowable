package com.home.demo.flowable.spring_security.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityManagerImpl;
import org.flowable.idm.engine.impl.persistence.entity.data.UserDataManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class SpringSecurityUserManager extends UserEntityManagerImpl {

    private JdbcUserDetailsManager userManager;

    public SpringSecurityUserManager(IdmEngineConfiguration idmEngineConfiguration, UserDataManager userDataManager, JdbcUserDetailsManager userManager) {
        super(idmEngineConfiguration, userDataManager);
        this.userManager = userManager;
    }

    @Override
    public UserEntity findById(String userId) {
        UserDetails userDetails = userManager.loadUserByUsername(userId);
        if (userDetails != null) {
            UserEntityImpl user = new UserEntityImpl();
            user.setId(userId);
            return user;
        }
        return null;

    }
    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query) {
        List<User> users = null;
        if (query.getGroupId() != null) {
            users = userManager.findUsersInGroup(query.getGroupId())
                .stream()
                .map(username -> {
                    User user = new UserEntityImpl();
                    user.setId(username);
                    return user;
                })
                .collect(Collectors.toList());
            return users;
        }

        if (query.getId() != null) {
            UserDetails userDetails = userManager.loadUserByUsername(query.getId());
            if (userDetails != null) {
                UserEntityImpl user = new UserEntityImpl();
                user.setId(query.getId());
                return Collections.singletonList(user);
            }
        }
        return null;
    }
    
    public Boolean checkPassword(String userId, String password) {
        return true;
    }

    public void setUserManager(JdbcUserDetailsManager userManager) {
        this.userManager = userManager;
    }
    @Override
    public User createNewUser(String userId) {
        throw new UnsupportedOperationException("This operation is not supported!");
    }
    @Override
    public void updateUser(User updatedUser) {
        throw new UnsupportedOperationException("This operation is not supported!");

    }
    @Override
    public void delete(UserEntity userEntity) {
        throw new UnsupportedOperationException("This operation is not supported!");

    }

    @Override
    public void deletePicture(User user) {
        UserEntity userEntity = (UserEntity) user;
        if (userEntity.getPictureByteArrayRef() != null) {
            userEntity.getPictureByteArrayRef()
                .delete();
        }
    }
    @Override
    public void delete(String userId) {
        throw new UnsupportedOperationException("This operation is not supported!");

    }
    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        return findUserByQueryCriteria(query).size();
    }

    public List<Group> findGroupsByUser(String userId) {
        UserDetails userDetails = userManager.loadUserByUsername(userId);
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
    @Override
    public UserQuery createNewUserQuery() {
        throw new UnsupportedOperationException("This operation is not supported!");
    }

    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException("This operation is not supported!");
    }
    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException("This operation is not supported!");

    }

}
