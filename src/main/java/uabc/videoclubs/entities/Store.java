package uabc.videoclubs.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;

import org.springframework.format.annotation.DateTimeFormat;

@NamedNativeQuery(name = "Store.findStoreAndAddress", query = "select s.store_id, a.address from store s, address a where s.address_id = a.address_id", resultSetMapping = "Mapping.StoreIndex")
@SqlResultSetMapping(name = "Mapping.StoreIndex",
classes = @ConstructorResult(targetClass = StoreIndex.class,
                              columns = {@ColumnResult(name = "store_id", type = Integer.class),
                                           @ColumnResult(name = "address", type = String.class)}))
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "address_id")
    private EAddress address;

    @DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp last_update;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public EAddress getAddress() {
        return address;
    }

    public void setAddress(EAddress address) {
        this.address = address;
    }

    public java.sql.Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(java.sql.Timestamp last_update) {
        this.last_update = last_update;
    }

    public Store() {
        super();
    }

    public Store(Integer storeId, Staff staff, EAddress address, Timestamp last_update) {
        this.storeId = storeId;
        this.staff = staff;
        this.address = address;
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Store [address=" + address + ", last_update=" + last_update + ", staff=" + staff + ", storeId="
                + storeId + "]";
    }

    
}
