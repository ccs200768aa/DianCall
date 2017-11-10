package com.diancall.platf.biz.service.merch.impl;

import com.diancall.platf.biz.dao.merch.*;
import com.diancall.platf.biz.entity.merch.*;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import com.diancall.platf.config.datasource.DataSource;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-10-31
 * Time: 17:56
 */
@Service(value = "merchUserService")
@Transactional
public class MerchUserServiceImpl implements MerchUserServiceI {

    @Autowired
    private MerchUserMapper merchUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private MerchUserRoleMapper merchUserRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @DataSource(name = "merchDataSource")
    public List<Merchuser> queryList() {
        return merchUserMapper.selectByMap(null);
    }

    @Override
    @DataSource(name = "merchDataSource")
    public Merchuser findMerchuserByName(String userName) {
        Merchuser mu = new Merchuser();
        mu.setAccount(userName);
        return merchUserMapper.selectOne(mu);
    }

    @Override
    @DataSource(name = "merchDataSource")
    public List<Role> queryRoleListWithId(int merchuserid) {
        List<Role> rList = new LinkedList<>();
        Merchuser mu = merchUserMapper.selectById(merchuserid);
        if (null == mu) return null;
        HashMap<String, Object> mrMap = new HashMap<>();
        mrMap.put("merchuserid", mu.getMerchuserid());
        List<MerchuserRole> mrList = merchUserRoleMapper.selectByMap(mrMap);
        mrList.forEach(mr -> {
            Role role = roleMapper.selectById(mr.getRoleid());
            rList.add(role);
        });
        return rList;
    }

    @Override
    @DataSource(name = "merchDataSource")
    public List<Role> queryRoleListWithUserName(String username) {
        List<Role> rList = new LinkedList<>();
        Merchuser mu = findMerchuserByName(username);
        if (null == mu) return null;
        HashMap<String, Object> mrMap = new HashMap<>();
        mrMap.put("merchuserid", mu.getMerchuserid());
        List<MerchuserRole> mrList = merchUserRoleMapper.selectByMap(mrMap);
        mrList.forEach(mr -> {
            Role role = roleMapper.selectById(mr.getRoleid());
            rList.add(role);
        });
        return rList;
    }

    @Override
    @DataSource(name = "merchDataSource")
    public List<Permission> queryPermissionListWithRole(int roleid) {
        List<Permission> pList = new LinkedList<>();
        Role role = roleMapper.selectById(roleid);
        if (null == role) return null;
        HashMap<String, Object> rpMap = new HashMap<>();
        rpMap.put("roleid", role.getRoleid());
        List<RolePermission> rpList = rolePermissionMapper.selectByMap(rpMap);
        rpList.forEach(rp -> {
            Permission permission = permissionMapper.selectById(rp.getPermissionid());
            pList.add(permission);
        });
        return pList;
    }

    @Override
    @DataSource(name = "merchDataSource")
    public List<Permission> queryPermissionListWithUserName(String userName) {
        List<Permission> pList = new LinkedList<>();
        List<Role> rList = queryRoleListWithUserName(userName);
        rList.forEach(role -> {
            pList.addAll(queryPermissionListWithRole(role.getRoleid()));
        });
        return pList;
    }

    @Override
    @DataSource(name = "merchDataSource")
    public List<Permission> queryPermissionListWithId(int merchuserid) {
        List<Permission> pList = new LinkedList<>();
        List<Role> rList = queryRoleListWithId(merchuserid);
        rList.forEach(role -> {
            pList.addAll(queryPermissionListWithRole(role.getRoleid()));
        });
        return pList;
    }

    @Override
    @DataSource(name = "merchDataSource")
    public Set<String> findPermissionWithUserName(String userName) {
        List<Permission> pList = queryPermissionListWithUserName(userName);
        Set<String> set = Sets.newHashSet();
        pList.forEach(permission ->
                set.add(permission.getName());
        );
        return set;
    }

    @Override
    @DataSource(name = "merchDataSource")
    public Set<String> findPermissionWithId(int merchuserid) {
        List<Permission> pList = queryPermissionListWithId(merchuserid);
        Set<String> set = Sets.newHashSet();
        pList.forEach(permission ->
                set.add(permission.getName());
        );
        return set;
    }
}
