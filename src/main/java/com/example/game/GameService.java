package com.example.game;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public int startGame(Game game) {

        return em.createQuery("INSERT INTO Game g"
                        + " VALUES(id, name, status, createdAt, updatedAt)"
                        + " WHERE g.id = :id", Game.class)
                .setParameter("id", game.getId())
                .executeUpdate();

    }

    @Transactional
    public Game getGameInfo(Long id) throws Exception {
        try {
            return em.createQuery("SELECT g FROM Game g"
                            + " WHERE g.id = :id", Game.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Transactional
    public int updateGameStatus(Long id, Game.Status status) throws Exception {
        try {
            return em.createQuery("UPDATE Game g"
                            + "SET g.status = status"
                            + "WHERE g.id = :id", Game.class)
                    .setParameter("id", id)
                    .setParameter("status", status)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }


    @Transactional
    public boolean deleteGame(Long id) throws Exception {
            try {
                em.createNativeQuery("DELETE FROM Game"
                                + " WHERE Game.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception();
            }

        return true;//videti kako return false i mozda try catch throw
    }
}
