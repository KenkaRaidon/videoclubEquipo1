package uabc.videoclubs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="actor")
public class Actor {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Timestamp last_update;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
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

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Actor(Integer actorId, String firstName, String lastName, Timestamp last_update) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.last_update = last_update;
    }

    public Actor() {
        super();
    }

    @Override
    public String toString() {
        return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", last_update="
                + last_update + "]";
    }
}
