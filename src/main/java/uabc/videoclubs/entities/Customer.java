package uabc.videoclubs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;

import java.sql.Date;
import java.sql.Timestamp;

@NamedNativeQuery(name = "Customer.getCustomers", query = "select c.customer_id, concat(c.first_name, ' ', c.last_name) as name, c.email from customer c", resultSetMapping = "Mapping.CustomerIndex")
@SqlResultSetMapping(name = "Mapping.CustomerIndex",
classes = @ConstructorResult(targetClass = CustomerIndex.class,
                              columns = {@ColumnResult(name = "customer_id", type = Integer.class),
                                           @ColumnResult(name = "name", type = String.class),
                                           @ColumnResult(name = "email", type = String.class)}))
@Entity
@Table(name = "customer")
public class Customer {
    // #region variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private EAddress addressObject;
    /*@Column(name = "address_id")
    private Integer addressId;*/

    @Column(name = "activebool")
    private Boolean activeBool;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date create_date;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Timestamp last_update;
    // #endregion

    // #region constructor
    public Customer() {
        super();
    }

    public Customer(Integer customerId, Integer storeId, String firstName, String lastName, String email, EAddress addressObject,
            Boolean activeBool, Date create_date, Timestamp last_update) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressObject = addressObject;
        this.activeBool = activeBool;
        this.create_date = create_date;
        this.last_update = last_update;
    }
    // #endregion

    // #region getter setter
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EAddress getAddressObject() {
        return addressObject;
    }

    public void setAddressObject(EAddress addressObject) {
        this.addressObject = addressObject;
    }

    public Boolean getActiveBool() {
        return activeBool;
    }

    public void setActiveBool(Boolean activeBool) {
        this.activeBool = activeBool;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    // #endregion
    @Override
    public String toString() {
        return "Customer [activeBool=" + activeBool + ", address=" + addressObject + ", create_date=" + create_date
                + ", customerId=" + customerId + ", email=" + email + ", firstName=" + firstName + ", lastName="
                + lastName + ", last_update=" + last_update + ", storeId=" + storeId + "]";
    }

}
