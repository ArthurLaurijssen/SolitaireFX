package be.kdg.solitaire.model;

import be.kdg.solitaire.model.Cards.Deck;

import java.io.IOException;
import java.nio.file.*;

public class SolitaireModel {
    private int score;
    private Deck deck;
    private Path highScores;
    private String playerName;

    public SolitaireModel() {
        deck = new Deck();
        deck.shuffle();
        this.score = 100;
        this.createFiles();
    }
    private void createFiles() {

        try {
            this.highScores = Paths.get("resources/data/highscores.txt");
            if (!Files.exists(highScores)) {
                Files.createDirectories(highScores.getParent());
                Files.createFile(highScores);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gewonnen(int tijd) {
       try {
           String toFile = this.getScore() + ";" + tijd + ";" +this.playerName ;
           Files.write(this.highScores,toFile.getBytes(), StandardOpenOption.APPEND);
       } catch (IOException e) {
           e.printStackTrace();
       }

    }
    public Deck getDeck() {
        return deck;
    }
    public void newDeck() {
        this.deck = new Deck();
    }

    public void minutePassed() {
        score = score-15;
    }
    public void stappelKlik() {
        score = score-1;
    }
    public void cardAddedToFoundation() {
        score = score + 15;
    }

    public String getScore() {
        return "Score:\t" + this.score +"\t";
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}