package entity;

import javax.persistence.*;


@Entity
public class Customers {

    private int customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postal;
    private String phone;
    private String email;
    private String privacy;

    @OneToOne
    private CustomerLogon customerLogon;


    public Customers(){}

    public Customers(int customerId) {
        this.customerId = customerId;
    }

    public Customers(int customerId, String firstName, String lastName, String address,
                     String city, String region, String country, String postal,
                     String phone, String email, String privacy) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.postal = postal;
        this.phone = phone;
        this.email = email;
        this.privacy = privacy;
    }

    @Id
    @Column(name = "CustomerID")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        if (firstName!=null)
        return firstName;
        return "...";
    }

    public void setFirstName(String firstName) {
        if(!firstName.isEmpty())
        this.firstName = firstName; }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        if (lastName!=null)
        return lastName;
        return "...";
    }

    public void setLastName(String lastName) {
        if (!lastName.isEmpty())
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        if (address!=null)
        return address;
        return "...";
    }

    public void setAddress(String address) {
        if (!address.isEmpty())
        this.address = address;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        if (city!=null)
        return city;
        return "...";
    }

    public void setCity(String city) {
        if (!city.isEmpty())
        this.city = city;
    }

    @Basic
    @Column(name = "Region")
    public String getRegion() {
        if (region!=null)
        return region;
        return "...";
    }

    public void setRegion(String region) {
        if (!region.isEmpty())
        this.region = region;
    }

    @Basic
    @Column(name = "Country")
    public String getCountry() {
        if (country!=null)
        return country;
        return "...";
    }

    public void setCountry(String country) {
        if (!country.isEmpty())
        this.country = country;
    }

    @Basic
    @Column(name = "Postal")
    public String getPostal() {
        if (postal!=null)
        return postal;
        return "...";
    }

    public void setPostal(String postal) {
        if (!postal.isEmpty())
        this.postal = postal;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        if (phone!=null)
        return phone;
        return "...";
    }

    public void setPhone(String phone) {
        if (!phone.isEmpty())
        this.phone = phone;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        if (email!=null)
        return email;
        return "...";
    }

    public void setEmail(String email) {
        if (!email.isEmpty())
        this.email = email;
    }

    @Basic
    @Column(name = "Privacy")
    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        if (!privacy.isEmpty())
        this.privacy = privacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customers customers = (Customers) o;

        if (customerId != customers.customerId) return false;
        if (firstName != null ? !firstName.equals(customers.firstName) : customers.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customers.lastName) : customers.lastName != null) return false;
        if (address != null ? !address.equals(customers.address) : customers.address != null) return false;
        if (city != null ? !city.equals(customers.city) : customers.city != null) return false;
        if (region != null ? !region.equals(customers.region) : customers.region != null) return false;
        if (country != null ? !country.equals(customers.country) : customers.country != null) return false;
        if (postal != null ? !postal.equals(customers.postal) : customers.postal != null) return false;
        if (phone != null ? !phone.equals(customers.phone) : customers.phone != null) return false;
        if (email != null ? !email.equals(customers.email) : customers.email != null) return false;
        if (privacy != null ? !privacy.equals(customers.privacy) : customers.privacy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postal != null ? postal.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (privacy != null ? privacy.hashCode() : 0);
        return result;
    }

    public CustomerLogon getCustomerLogon() {
        return customerLogon;
    }

    public void setCustomerLogon(CustomerLogon customerLogon) {
        this.customerLogon = customerLogon;
    }
}
