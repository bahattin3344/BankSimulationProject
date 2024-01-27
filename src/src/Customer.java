package src;

public class Customer {


    private static int nextId =1000;
    private int customerId;
    private String firstName;
    private String lastName;
    private String city;

    public Customer() {
    }

    public Customer( String firstName, String lastName, String city) {
        this.customerId = ++nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}
