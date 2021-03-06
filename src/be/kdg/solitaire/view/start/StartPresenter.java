package be.kdg.solitaire.view.start;


import be.kdg.solitaire.model.SolitaireModel;
import be.kdg.solitaire.view.solitaire.SolitaireView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartPresenter {
    private final SolitaireModel model;
    private final StartView view;

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
                if (!view.getTextField().getText().isEmpty()) {
                    SolitaireView solitaireView = new SolitaireView(model);
                    model.setPlayerName(view.getTextField().getText());
                    view.getScene().getWindow().setHeight(800);
                    view.getScene().getWindow().setWidth(1300);
                    view.getScene().getWindow().centerOnScreen();
                    view.getScene().setRoot(solitaireView);
                }
                else {
                    view.getLblError().setText("Naam mag niet leeg zijn!!");
                }

            }
        });

    }

    private void updateView() {

    }
}
