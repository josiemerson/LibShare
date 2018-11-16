package br.com.libshare.userpermission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionKey> {

}
