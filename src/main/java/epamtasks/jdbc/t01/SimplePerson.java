package epamtasks.jdbc.t01;

public class SimplePerson {
    private  String firstName;
    private  String lastName;
    private boolean permission;
    private String dob ;
    private String email;
    private String pass;
    private String address;
    private String telephone;

    public SimplePerson(){
        firstName = "default";
        lastName = firstName;
        permission = false;
        pass = "qwerty";
        email = firstName +"_"+ lastName +"@gmail.com";
        address = lastName;
        telephone = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public SimplePerson setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SimplePerson setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isPermission() {
        return permission;
    }

    public SimplePerson setPermission(boolean permission) {
        this.permission = permission;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public SimplePerson setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SimplePerson setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public SimplePerson setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SimplePerson setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public SimplePerson setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
