package uabc.videoclubs.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "address", uniqueConstraints = { @UniqueConstraint(columnNames = { "address"}) })
public class EAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    private String address;

    private String address2;

    private String district;

    @OneToOne
	@JoinColumn(name="city_id")
	private City city;

    @Column(name = "postal_code")
    private String postalCode;

    private String phone;

    @DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp last_update;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public java.sql.Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(java.sql.Timestamp last_update) {
        this.last_update = last_update;
    }

    public EAddress(Integer addressId, String address, String address2, String district, City city, String postalCode,
            String phone, Timestamp last_update) {
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.last_update = new Timestamp(System.currentTimeMillis());
    }

    public EAddress(){
        super();
    }

    @Override
    public String toString() {
        return "EAddress [address=" + address + ", address2=" + address2 + ", addressId=" + addressId + ", city=" + city
                + ", district=" + district + ", last_update=" + last_update + ", phone=" + phone + ", postalCode="
                + postalCode + "]";
    }

}

