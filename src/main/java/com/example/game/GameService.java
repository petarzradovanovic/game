package com.example.game;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long startGame(Game game) {

        em.persist(game);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = "{\"gameId\":\""+game.getId()+"\"}";
        HttpEntity<String> request = new HttpEntity<>(body,headers);
        try {
            responseEntity = restTemplate.postForEntity("http://localhost:8081/register", request, String.class);
        }catch (Exception e){e.printStackTrace();}
        return game.getId();

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
    public boolean updateGameStatus(Game game) throws Exception {
        try {
            System.out.println(game);
            em.merge(game);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Transactional
    public boolean deleteGame(Long id) throws Exception {
            try {
                Query q = em.createNativeQuery("DELETE FROM Game"
                                + " WHERE Game.id = :id")
                        .setParameter("id", id);

                        if(q.executeUpdate()!=-1) return true;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception();
            }
        return false;
    }

    @Transactional
    public List<Game> search(String name, String status) {
        try {
            String query = "SELECT g" +
                    " FROM Game g" +
                    " WHERE ";
            if(name != null) {
                query = query + " name = '" + name + "'";
            }
            if(status != null) {
                if(name != null) query = query + " AND ";
                query = query + " status = '" + Game.Status.valueOf(status).ordinal() + "'";
            }
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
