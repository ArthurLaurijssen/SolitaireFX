package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

class Pot extends HBox {
    private ImageView pot,potCardShown;
    private final SolitaireModel model;
    private final GameView view;


    Pot(SolitaireModel model,GameView view) {
        this.model = model;
        this.view = view;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.pot = new ImageView(model.getDeck().getImages().getBack());
        this.potCardShown = new ImageView(new Image("/images/square.png"));
    }

    private void layoutNodes() {
        this.setSpacing(40);
        this.setSizes(this.pot);
        this.setSizes(this.potCardShown);
        /*this.pot.setFitHeight(150);
        this.pot.setFitWidth(100);
        this.potCardShown.setFitWidth(100);
        this.potCardShown.setFitHeight(150);*/
        this.getChildren().add(this.pot);
        this.getChildren().add(this.potCardShown);
    }

    ImageView getPotImageView() {
        return pot;
    }

    private void setSizes(ImageView imageView) {
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
    }

    void switchPot(Card c) {
        //this.getChildren().remove(1);
        //this.potCardShown = new ImageView(model.getDeck().getImages().getimage(c));
        //this.potCardShown.setFitHeight(150);
        //this.potCardShown.setFitWidth(100);
        //this.getChildren().add(1,this.potCardShown);
        this.potCardShown.setImage(model.getDeck().getImages().getimage(c));
        this.view.getPresenter().potAddEventHandlers(this.potCardShown,c);
    }

}
