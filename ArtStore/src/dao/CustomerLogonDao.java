package dao;

import entity.CustomerLogon;

public interface CustomerLogonDao {

    /**
     * maybe used for register
     * @param customerLogon
     * @return
     */
    CustomerLogon add(CustomerLogon customerLogon);

    void delete(int customerId);

    CustomerLogon update(CustomerLogon customerLogon);

    boolean updatePassword(int customerId, String newPass);

    /**
     *  maybe used for login
     * @param userName
     * @param pass
     * @return
     */
    CustomerLogon getOne(String userName, String pass);

    boolean exists(String userName);

    CustomerLogon getOne(int customerId);
}
