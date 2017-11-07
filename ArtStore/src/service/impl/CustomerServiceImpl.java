package service.impl;

import dao.CustomerDao;
import dao.CustomerLogonDao;
import dao.DaoFactory.DaoFactory;
import entity.Customers;
import entity.CustomerLogon;
import service.CustomerService;

import java.util.List;


public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private CustomerLogonDao customerLogonDao;

    public CustomerServiceImpl() {
            customerLogonDao = DaoFactory.getCustomerLogonDaoInstance();
            customerDao = DaoFactory.getCustomerDaoInstance();
    }

    /**
     * 验证用户登录
     *
     * @param userName
     * @param pass
     * @return 登录成功返回顾客基本信息，否则返回null
     */
    @Override
    public Customers login(String userName, String pass) {
        CustomerLogon customerLogon = customerLogonDao.getOne(userName, pass);
        if (customerLogon != null) {
            customerLogon=customerLogonDao.update(customerLogon);
            Customers customers = customerDao.getOne(customerLogon.getCustomerId());
            customers.setCustomerLogon(customerLogon);
            return customers;
        }
        return null;
    }

    public void delete(String userName, String pass) {
        CustomerLogon customerLogon = customerLogonDao.getOne(userName, pass);
        if (customerLogon != null) {
            customerLogonDao.delete(customerLogon.getCustomerId());
            customerDao.delete(customerLogon.getCustomerId());
        }
    }

    /**
     * 验证用户注册
     *
     * @param userName
     * @param pass
     * @return 注册成功返回顾客基本信息，否则返回null
     */
    @Override
    public Customers register(String userName, String pass,String email) {
        CustomerLogon customerLogon = customerLogonDao.add(new CustomerLogon(userName, pass));
        if (customerLogon != null){
            Customers customers = customerDao.add(new Customers(customerLogon.getCustomerId()));
            customers.setEmail(email);
            customers.setCustomerLogon(customerLogon);
            return customers;
        }
        return null;
    }

    @Override
    public List<Customers> getAll() {
        List<Customers> customers = customerDao.getAll();
        // lambda expression
        customers.forEach(customer ->
            customer.setCustomerLogon(customerLogonDao.getOne(customer.getCustomerId()))
        );
        return customers;
    }

    /**
     * 更改用户信息, 密码除外
     *
     * @param customers
     * @return 更改后的用户
     */
    @Override
    public Customers update(Customers customers) {
        return customerDao.update(customers);
    }




    @Override
    public boolean updateCustomerStatus(String userName, String pass, int newStatus) {
        CustomerLogon customerLogon = customerLogonDao.getOne(userName, pass);
        if (customerLogon != null) {
            customerLogon.setState(newStatus);
            return customerLogonDao.update(customerLogon) != null;
        }
        return  false;
    }

    @Override
    public boolean updatePassword(String userName, String oldPass, String newPass) {
        CustomerLogon customerLogon = customerLogonDao.getOne(userName, oldPass);
        if (customerLogon != null) {
            return customerLogonDao.updatePassword(customerLogon.getCustomerId(), newPass);
        }
        return false;
    }
}
