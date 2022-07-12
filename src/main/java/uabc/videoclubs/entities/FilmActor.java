package uabc.videoclubs.entities;

import java.sql.Timestamp;

public class FilmActor {
    private Integer filmId;
    private Integer actorId;
    private Timestamp lastUpdate;
    
    public Integer getFilmId() {
        return filmId;
    }
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    public Integer getActorId() {
        return actorId;
    }
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public FilmActor() {
        super();
    }
    public FilmActor(Integer filmId, Integer actorId, Timestamp lastUpdate) {
        this.filmId = filmId;
        this.actorId = actorId;
        this.lastUpdate=lastUpdate;
    }
    @Override
    public String toString() {
        return "FilmActor [actorId=" + actorId + ", filmId=" + filmId + ", lastUpdate=" + lastUpdate + "]";
    }
    
}
