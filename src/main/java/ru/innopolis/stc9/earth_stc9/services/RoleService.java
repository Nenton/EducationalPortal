package ru.innopolis.stc9.earth_stc9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.IRoleDao;
import ru.innopolis.stc9.earth_stc9.pojo.Role;

import java.sql.SQLException;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        try {
            return roleDao.getRoles();
        } catch (SQLException e) {
            logger.warn("Ошибка получения всех ролей", e);
            return null;
        }
    }

    @Override
    public boolean createRole(Role role) {
        if (role == null) {
            return false;
        }
        try {
            return roleDao.addRole(role);
        } catch (SQLException e) {
            logger.warn("Ошибка создания роли", e);
            return false;
        }
    }

    @Override
    public boolean deleteRole(int id) {
        if (id == 0) {
            return false;
        }
        try {
            return roleDao.deleteRole(id);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления роли", e);
            return false;
        }
    }
}
