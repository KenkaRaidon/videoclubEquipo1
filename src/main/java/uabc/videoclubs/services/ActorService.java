package uabc.videoclubs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Actor;
import uabc.videoclubs.repository.ActorRepository;

@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;

    public Actor obtenerActor(Integer actorId){
        return actorRepository.obtenerActor(actorId);
    }

    public Actor save(Actor actor){
        return actorRepository.save(actor);
    }
}
