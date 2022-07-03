package uabc.videoclubs.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "country")
public class Country {
    //Declaracion de variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
    private Integer countryId;

    private String country;

    @DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp last_update;

    //Getters and Setters

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.sql.Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(java.sql.Timestamp last_update) {
        this.last_update = last_update;
    }

    //Constructores
    public Country(Integer countryId, String country, Timestamp last_update) {
        this.countryId = countryId;
        this.country = country;
        this.last_update = last_update;
    }

    public Country() {
        super();
    }

    //To string
    @Override
    public String toString() {
        return "Country [country=" + country + ", countryId=" + countryId + ", last_update=" + last_update + "]";
    }
    
}
