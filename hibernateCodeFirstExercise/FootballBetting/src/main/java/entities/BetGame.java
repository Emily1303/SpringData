package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {

    @Id
    @ManyToOne
    private Game game;

    @Id
    @ManyToOne
    private Bet bet;

    @ManyToOne
    private ResultPrediction prediction;

    public BetGame() {}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getPrediction() {
        return prediction;
    }

    public void setPrediction(ResultPrediction prediction) {
        this.prediction = prediction;
    }

}
