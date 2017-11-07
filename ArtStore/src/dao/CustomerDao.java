package dao;

import entity.Customers;

import java.util.List;

public interface CustomerDao {

    Customers add(Customers customers);

    void delete(int customerId);

    Customers update(Customers customers);

    List<Customers> getAll();

    Customers getOne(int customerId);
}
