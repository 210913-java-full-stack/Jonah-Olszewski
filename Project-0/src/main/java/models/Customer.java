package models;

public class Customer {
    private int customerId;
   // private static final AtomicInteger count = new AtomicInteger(0); ;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;

    public Customer() {
        super();
    }

    public Customer(int customerId, String firstname, String lastname, String username, String password, String email){
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

//    public void printCustomerId(){
//        System.out.println(this.customerId);
//    }

    public Customer(String firstname, String lastname, String username, String password, String email) {
        //this.customerId = count.incrementAndGet();
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    } // this runs in Registration Menu

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer: " + getCustomerId() + " - " + getFirstname() + ", " + getLastname(); // add userName, PassWord, email
    }
}
