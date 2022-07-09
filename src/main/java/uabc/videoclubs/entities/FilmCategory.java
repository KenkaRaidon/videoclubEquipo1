package uabc.videoclubs.entities;

import java.sql.Timestamp;

public class FilmCategory {
    private Integer filmId;
    private Integer categoryId;
    private Timestamp lastUpdate;
    
    public Integer getFilmId() {
        return filmId;
    }
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public FilmCategory(Integer filmId, Integer categoryId, Timestamp lastUpdate) {
        this.filmId = filmId;
        this.categoryId = categoryId;
        this.lastUpdate = lastUpdate;
    }

    public FilmCategory(){
        super();
    }

    @Override
    public String toString() {
        return "FilmCategory [categoryId=" + categoryId + ", filmId=" + filmId + ", lastUpdate=" + lastUpdate + "]";
    }
}
