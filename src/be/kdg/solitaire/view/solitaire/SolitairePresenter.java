package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.SolitaireModel;

public class SolitairePresenter {
    private SolitaireView view;
    private SolitaireModel model;

    public SolitairePresenter(SolitaireView view, SolitaireModel model) {
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
