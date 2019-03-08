package be.kdg.solitaire.view.solitaire;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;


public class SolitaireView extends VBox {
    private final SolitaireModel model;
    private MenuBar menuBar;
    private GameView gameView;

    public SolitaireView(SolitaireModel model) {
        this.model = model;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        //GameView
        gameView = new GameView(this.model,this);
        //MenuBar
        final MenuItem newGame = new MenuItem("New Game");
        final MenuItem highScore = new MenuItem("HighScores");
        final MenuItem exit = new MenuItem("Exit");
        final Menu game = new Menu("Game",null,newGame,highScore,exit);

        final MenuItem gameRules = new MenuItem("Game rules");
        final MenuItem about = new MenuItem("About");
        final Menu help = new Menu("Help",null,gameRules,about);
        this.menuBar = new MenuBar(game,help);

    }

    private void layoutNodes() {
        this.setSpacing(0);
        this.getChildren().add(this.menuBar);
        this.getChildren().add(this.gameView);
    }

}
