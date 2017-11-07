package test;

import entity.Customers;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

public class registerTest {
    public static void main(String[] args) {
        CustomerService customerService=new CustomerServiceImpl();
        Customers customers=customerService.register("admin9","998326","123@food.com");
        System.out.println(customers.getEmail());
        System.out.println("lalala");
    }
}
