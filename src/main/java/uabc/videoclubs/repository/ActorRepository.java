package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uabc.videoclubs.entities.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{
    @Query(value ="select * from actor a where a.actor_id=?1 " , nativeQuery=true)
	Actor obtenerActor(Integer actorId);
}
