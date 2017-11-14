package com.diancall.platf.biz.service.merch;

import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.entity.merch.Permission;
import com.diancall.platf.biz.entity.merch.Role;

import java.util.List;
import java.util.Set;

public interface MerchUserServiceI {
    List<Merchuser> queryList();

    Merchuser findMerchuserByName(String userName);


    List<Role> queryRoleListWithId(int merchuserid);

    List<Role> queryRoleListWithUserName(String username);

    List<Permission> queryPermissionListWithRole(int roleid);

    List<Permission> queryPermissionListWithUserName(String userName);

    List<Permission> queryPermissionListWithId(int merchuserid);

    Set<String> findRoleWithUserName(String userName);

    Set<String> findRoleWithUserId(int merchuserid);

    Set<String> findPermissionWithUserName(String userName);

    Set<String> findPermissionWithId(int merchuserid);


}
