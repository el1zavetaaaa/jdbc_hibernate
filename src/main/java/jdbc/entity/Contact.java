package jdbc.entity;

public class Contact {

    private int id;
    private String name;
    private int customer_id;

    public Contact() {
    }

    public Contact(int id) {
        this.id = id;
    }

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contact(int id, String name, int customer) {
        this.id = id;
        this.name = name;
        this.customer_id = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
