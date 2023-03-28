package com.example.game;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


//INSERT INTO GAME VALUES(1,'2022-2-2','AA',2,'2022-2-2');
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
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("gameId", String.valueOf(game.getId()));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,headers);
        try {
            String body = "{\"gameId\":"+game.getId()+" }";
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
    public int updateGameStatus(Game game) throws Exception {
        try {
            System.out.println(game);
            return em.createQuery("UPDATE Game g"
                            + " SET status = :status"
                            + " WHERE g.id = :id", Game.class)
                    .setParameter("id", game.getId())
                    .setParameter("status", game.getStatus())
                    .executeUpdate();
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
}
