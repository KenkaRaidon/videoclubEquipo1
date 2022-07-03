package uabc.videoclubs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "category")
public class Category {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer id;
	
	private String name;
	
	@NotNull
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp last_update;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(java.sql.Timestamp last_update) {
		this.last_update = last_update;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + id + ", name=" + name + ", last_update=" + last_update + "]";
	}
}
