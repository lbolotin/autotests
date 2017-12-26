package entities;

import ru.alfabank.thrift.auth.entity.UserData;
import ru.alfabank.thrift.entities.Customer;

public class User {
    private final UserData userData;
    private final Customer customer;

    public User(UserData userData, Customer customer) {
        this.userData = userData;
        this.customer = customer;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}