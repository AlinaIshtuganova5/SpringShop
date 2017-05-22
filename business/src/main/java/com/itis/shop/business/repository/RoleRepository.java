package com.itis.shop.business.repository;

import com.itis.shop.model.Role;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 17.05.17.
 */
@Repository
@Lazy
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
