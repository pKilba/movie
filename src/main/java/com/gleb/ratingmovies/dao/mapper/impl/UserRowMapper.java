package com.gleb.ratingmovies.dao.mapper.impl;

import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.entity.UserRole;
import com.gleb.ratingmovies.dao.entity.UserStatus;
import com.gleb.ratingmovies.dao.mapper.api.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gleb.ratingmovies.dao.entity.ColumnName.*;

public class UserRowMapper implements RowMapper<User> {
    private static UserRowMapper instance;

    public static UserRowMapper getInstance() {
        if (instance == null) {
            instance = new UserRowMapper();
        }
        return instance;
    }

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        return  User.builder()
                .setUserId(resultSet.getInt(USER_ID))
                .setLogin(resultSet.getString(USER_LOGIN))
                .setPassword(resultSet.getString(USER_PASSWORD))
                .setUserRole(UserRole.values()[(resultSet.getInt(USER_ROLE))])
                .setName(resultSet.getString(USER_NAME))
                .setEmail(resultSet.getString(USER_EMAIL))
                .setTelegram (resultSet.getString(USER_TELEGRAM))
                .setUserStatus(UserStatus.getById(resultSet.getInt(USER_STATUS)))
                .setCreateTime(resultSet.getTimestamp(USER_CREATE_TIME))
                .setProfilePicture(resultSet.getString(USER_PROFILE_PICTURE))
                .build();
    }
}
