package com.gleb.ratingmovies.dao.api;

import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends DAO<User, Long> {
    /**
     * Gets list users in range described as offset and amount of users.
     *
     * @param offset an amount of users to get.
     * @param amount the amount
     * @return a received list of users.
     * @throws DaoException if database errors occurs.
     */
    List<User> findUsersRange(int offset, int amount) throws DaoException;

    /**
     * Gets users amount in database.
     *
     * @return an amount of all users.
     * @throws DaoException if database errors occurs.
     */
    int findUsersAmount() throws DaoException;

    /**
     * Finds user in database by login and password and returns container of account
     * or empty container if not found.
     *
     * @param login    a login(username) of user.
     * @param password a password of user.
     * @return an optional container of account.
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByLoginPassword(String login, String password) throws DaoException;

    /**
     * Finds user in database by login and returns container of account
     * or empty container if not found.
     *
     * @param login a login(username) of account.
     * @return an optional container of account.
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByLogin(String login) throws DaoException;


    /**
     * @param user user
     * @return is there a user with this login
     * @throws DaoException if database errors occurs.
     */
    boolean findUserByLogin(User user) throws DaoException;

    /**
     * @param user user
     * @return is there a user with this telegram
     * @throws DaoException if database errors occurs
     */
    boolean findUserByTelegram(User user) throws DaoException;

    /**
     * Finds user in database by login and returns container of account
     * or empty container if not found.
     *
     * @param email a email of user.
     * @return an optional container of user.
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByEmail(String email) throws DaoException;


    /**
     * @param user user
     * @return is there a user with this email
     * @throws DaoException if database errors occurs.
     */
    boolean findUserByEmail(User user) throws DaoException;


    /**
     * @param login login user
     * @return an id by user login
     * @throws DaoException if database errors occurs.
     */
    long findIdByLogin(String login) throws DaoException;


    /**
     * @param id id user
     * @throws DaoException if database errors occurs.
     */

    void delete(Long id) throws DaoException;


    /**
     * @param id id user
     * @return user optional by id
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserById(long id) throws DaoException;

    /**
     * @param id id user
     * @return true or false about block by id user
     * @throws DaoException if database errors occurs.
     */
    boolean blockById(Long id) throws DaoException;

    /**
     * @param id user
     * @return true or false about unblock by id user
     * @throws DaoException if database errors occurs.
     */
    boolean isUnblockedById(long id) throws DaoException;

    /**
     * @param id user
     * @return true or false about block by id user
     * @throws DaoException if database errors occurs.
     */

    boolean isBlockedById(long id) throws DaoException;

    /**
     * @param id user id
     * @return true or false about unblock by id user
     * @throws DaoException if database errors occurs.
     */
    boolean unblockById(Long id) throws DaoException;

    /**
     * @param telegram user telegram
     * @return find optional user by telegram
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByTelegram(String telegram) throws DaoException;

    /**
     * @param id    user id
     * @param photo
     * @throws DaoException if database errors occurs
     */

    void updatePhotoByUserId(long id, String photo) throws DaoException;

    /**
     * @param id       id user
     * @param password password user
     * @return true or false , result operation
     * @throws DaoException if database errors occurs
     */
    boolean updatePasswordByUserId(long id, String password) throws DaoException;

    /**
     * @param user user
     * @return update user by user
     * @throws DaoException if database errors occurs
     */
    User update(User user) throws DaoException;
}
