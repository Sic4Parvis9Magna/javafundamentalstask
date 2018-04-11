package epamtasks.jdbc.t01;

import java.util.Objects;

public class SimplePerson {
    private  String firstName;
    private  String lastName;
    private boolean permission;
    private String dob ;
    private String email;
    private String pass;
    private String address;
    private String telephone;

    private SimplePerson(){
        String  s = "default";
        firstName = s;
        lastName = s;
        pass = s;
        email = s;
        address = s;
        telephone = s;

    }

    public static SimplePerson getDefaultPerson(){
        return new SimplePerson();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimplePerson)) return false;
        SimplePerson person = (SimplePerson) o;
        return permission == person.permission &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(dob, person.dob) &&
                Objects.equals(email, person.email) &&
                Objects.equals(pass, person.pass) &&
                Objects.equals(address, person.address) &&
                Objects.equals(telephone, person.telephone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, permission, dob, email, pass, address, telephone);
    }

    @Override
    public String toString() {
        return "SimplePerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", permission=" + permission +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
