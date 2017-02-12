package net.MyCloud.cloud.dao;

import net.MyCloud.cloud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
