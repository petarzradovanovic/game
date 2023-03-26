import com.example.game.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean home() {
        return true;
    }

    @Transactional
    public boolean startGame(){
        System.out.println("AAA");
        return true;//ako treba registrovati igraca i podesiti parametre
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
    public int updateGameStatus(Long id, Game.Status status){

        return em.createQuery("UPDATE Game g"
                + "SET g.status = status"
                + "WHERE g.id = :id", Game.class)
                .setParameter("id", id)
                .executeUpdate();
    }
    @Transactional
    public boolean deleteGame(Long id){

        em.createNativeQuery("DELETE FROM Game"
                        + " WHERE Game.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        return true;//videti kako return false i mozda try catch throw
    }
}
