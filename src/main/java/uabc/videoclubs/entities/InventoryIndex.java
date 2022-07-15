package uabc.videoclubs.entities;

public class InventoryIndex {
    private Integer inventory_id;
    private Integer film_id;
    private String title;
    private Short rental_duration;
    private Float rental_rate;
    private Float replacement_cost;
    
    public Integer getInventory_id() {
        return inventory_id;
    }
    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }
    public Integer getFilm_id() {
        return film_id;
    }
    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Short getRental_duration() {
        return rental_duration;
    }
    public void setRental_duration(Short rental_duration) {
        this.rental_duration = rental_duration;
    }
    public Float getRental_rate() {
        return rental_rate;
    }
    public void setRental_rate(Float rental_rate) {
        this.rental_rate = rental_rate;
    }
    public Float getReplacement_cost() {
        return replacement_cost;
    }
    public void setReplacement_cost(Float replacement_cost) {
        this.replacement_cost = replacement_cost;
    }
    public InventoryIndex(Integer inventory_id, Integer film_id, String title, Short rental_duration, Float rental_rate,
            Float replacement_cost) {
        super();
        this.inventory_id = inventory_id;
        this.film_id = film_id;
        this.title = title;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
    }
    @Override
    public String toString() {
        return "InventoryIndex [film_id=" + film_id + ", inventory_id=" + inventory_id + ", rental_duration="
                + rental_duration + ", rental_rate=" + rental_rate + ", replacement_cost=" + replacement_cost
                + ", title=" + title + "]";
    }

    
}
