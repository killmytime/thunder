package service;

import entity.Customers;

import java.util.List;


public interface CustomerService {

    /**
     * 验证用户登录
     * @param userName
     * @param pass
     * @return 登录成功返回顾客基本信息，否则返回null
     */
    Customers login(String userName, String pass);

    /**
     * 验证用户注册
     * @param userName
     * @param pass
     * @return 注册成功返回顾客基本信息，否则返回null
     */
    Customers register(String userName, String pass,String email);

    // 演示jsp useBean 用
    List<Customers> getAll();

    /**
     * 更改用户信息, 密码除外
     * @param customers
     * @return 更改后的用户
     */
    Customers update(Customers customers);

    /**
     * 用于注销用户，删除用户数据库记录
     * @param userName
     * @param pass
     */
    void delete(String userName, String pass);

    boolean updateCustomerStatus(String userName, String pass, int newStatus);

    boolean updatePassword(String userName, String oldPass, String newPass);
}
