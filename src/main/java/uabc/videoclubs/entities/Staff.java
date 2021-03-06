package uabc.videoclubs.entities;

import java.util.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "staff")
public class Staff {
    // #region Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(unique = true)
    private String email;

    @Column(name = "store_id")
    private Integer storeId;

    private Boolean active;

    private String username;

    private String password;

    @Column(name = "last_update")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp lastUpdate;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private Byte[] picture;
    // #endregion

    // #region constructor
    public Staff(Integer staffId, String firstName, String lastName, Integer addressId, String email, Integer storeId,
            Boolean active, String username, String password, java.sql.Timestamp lastUpdate, Byte[] picture) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
        this.picture = picture;
    }

    public Staff() {
		super();
	}
    // #endregion

    // #region Getter Setters
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(java.sql.Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }
    // #endregion

    @Override
    public String toString() {
        return "Staff [active=" + active + ", addressId=" + addressId + ", email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", lastUpdate=" + lastUpdate + ", password=" + password + ", picture="
                + Arrays.toString(picture) + ", staffId=" + staffId + ", storeId=" + storeId + ", username=" + username
                + "]";
    }

}
