package test;

import dao.DaoFactory.DaoFactory;
import entity.Customers;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

public class updateTest {
    public static void main(String[] args) {
        CustomerService customerService=new CustomerServiceImpl();
        Customers customer=DaoFactory.getCustomerDaoInstance().getOne(60);
        customer.setFirstName("雷");
        customerService.update(customer);
        System.out.println(customer.getFirstName());
    }
}
