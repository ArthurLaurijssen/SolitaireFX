package be.kdg.solitaire.view.start;


import be.kdg.solitaire.model.SolitaireModel;
import be.kdg.solitaire.view.solitaire.SolitairePresenter;
import be.kdg.solitaire.view.solitaire.SolitaireView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartPresenter {
    private SolitaireModel model;
    private StartView view;

    public StartPresenter(SolitaireModel model,StartView view) {
        this.view = view;
        this.model = model;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        view.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SolitaireView solitaireView = new SolitaireView(model);
                new SolitairePresenter(solitaireView,model);
                view.getScene().setRoot(solitaireView);
            }
        });

    }
    private void updateView() {

    }
}
