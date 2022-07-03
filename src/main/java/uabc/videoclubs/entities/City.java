package uabc.videoclubs.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "city")
public class City {
    //Declaracion de variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
    private Integer cityId;

    private String city;

    @ManyToOne
	@JoinColumn(name="country_id")
	private Country country;

    @DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp last_update;

    public City(Integer cityId, String city, Country country, Timestamp last_update) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.last_update = last_update;
    }

    public City() {
        super();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public java.sql.Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(java.sql.Timestamp last_update) {
        this.last_update = last_update;
    }

    
    
}
