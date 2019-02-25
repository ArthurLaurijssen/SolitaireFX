package be.kdg.solitaire.view.start;


import be.kdg.solitaire.model.SolitaireModel;

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

    }
    private void updateView() {

    }
}
