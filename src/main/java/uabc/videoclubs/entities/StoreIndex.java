package uabc.videoclubs.entities;

public class StoreIndex {
    private Integer store_id;
    private String address;
    
    public Integer getStore_id() {
        return store_id;
    }
    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public StoreIndex(Integer store_id, String address) {
        super();
        this.store_id = store_id;
        this.address = address;
    }

    @Override
    public String toString() {
        return "StoreIndex [address=" + address + ", store_id=" + store_id + "]";
    }
}
