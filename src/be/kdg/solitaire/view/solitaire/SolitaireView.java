package be.kdg.solitaire.view.solitaire;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class SolitaireView extends BorderPane {
    private final SolitaireModel model;
    private MenuBar menuBar;
    private ToolBar toolBar;
    private GameView gameView;
    private Label score,tijd;

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

        //ToolBar
        this.score = new Label("Score:      ");
        this.tijd = new Label("Time:         ");
        this.toolBar = new ToolBar(score,tijd);


    }

    private void layoutNodes() {
        this.setTop(this.menuBar);
        this.setCenter(this.gameView);
        this.setBottom(this.toolBar);
    }

}
