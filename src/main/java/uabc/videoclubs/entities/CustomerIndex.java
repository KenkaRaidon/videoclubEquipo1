package uabc.videoclubs.entities;

public class CustomerIndex {
    private Integer customer_id;
    private String name;
    private String email;

    public CustomerIndex(Integer customer_id, String name, String email) {
        super();
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerIndex [customer_id=" + customer_id + ", email=" + email + ", name=" + name + "]";
    }
}
