package CONTACT_BOOK;

public class Contact {

    public String name;
    public String phone;
    public String email;

    public Contact(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Phone: " + phone + " Email: " + email;

    }
}
