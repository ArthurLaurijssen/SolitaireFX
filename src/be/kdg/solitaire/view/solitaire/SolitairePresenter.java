package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Ranks;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Optional;

class SolitairePresenter {
    private final GameView view;
    private final SolitaireModel model;
    private int source,tijd;
    private Card cardBeingDragged;
    private HashMap<ImageView,StapelPane> emptyPanes;
    private boolean multipleCards;

    private Timeline timeline;

    SolitairePresenter(GameView view, SolitaireModel model) {
        this.view = view;
        this.model = model;
        this.addEventHandlers();
        this.updateView();
        emptyPanes = new HashMap<>();
        this.startTimer();
    }

    private void addEventHandlers() {
        view.getPot().getPotImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (model.getDeck().getNextPot() != null) {
                    Card c = model.getDeck().getNextPot();
                    view.getPot().switchPot(c);
                }
                else {
                    view.getPot().potEmpty();
                }
            }
        });
        view.getVboxView().getMenuBar().getMenus().get(0).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //New Game
                model.newDeck();
                view.getVboxView().newGame();
                timeline.stop();
            }
        });
        view.getVboxView().getMenuBar().getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               //High scores
                final Stage stage = new Stage();
                PopUpHighscores popUpHighscores = new PopUpHighscores(model);
                Scene scene = new Scene(popUpHighscores);
                stage.setTitle("High scores");
                stage.setScene(scene);
                stage.show();
            }
        });
        view.getVboxView().getMenuBar().getMenus().get(0).getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Exit
                final Alert closeGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
                closeGameAlert.setTitle("Close game");
                closeGameAlert.setHeaderText("You are exiting the game are you sure?");
                Optional<ButtonType> btnKeuze = closeGameAlert.showAndWait();
                if (btnKeuze.get().getText().equals("OK")) {
                    Platform.exit();
                }
                else {
                    event.consume();
                }
            }
        });
        view.getVboxView().getMenuBar().getMenus().get(1).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Game Rules
                final Stage stage = new Stage();
                PopUpGameRules popUpGameRules = new PopUpGameRules();
                Scene scene = new Scene(popUpGameRules);
                stage.setTitle("Game Rules");
                stage.setScene(scene);
                stage.show();
            }
        });
        view.getVboxView().getMenuBar().getMenus().get(1).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //About
                final Stage stage = new Stage();
                PopUpAbout popUpAbout = new PopUpAbout();
                Scene scene = new Scene(popUpAbout);
                stage.setTitle("You won");
                stage.setScene(scene);
                stage.show();
            }
        });
    }
    private void updateView() {

    }
    void addEventhandlers(ImageView imgv,Card card) {
        imgv.setOnDragDetected(dragDetected(imgv,card));
        imgv.setOnDragOver(dragOver(imgv));
        imgv.setOnDragDropped(dragDropped(card));
        imgv.setOnDragExited(Event::consume);
    }
    void emptyStapelEventHandlers(ImageView imgv, StapelPane stapelPane) {
        emptyPanes.put(imgv,stapelPane);
        imgv.setOnDragOver(dragOver(imgv));
        imgv.setOnDragDropped(dragDroppedEmpty(imgv));
    }
    void foundationsAddEventhandlers(ImageView imgv,FoundationPane foundationPane) {
        imgv.setOnDragDropped(dragDroppedFoundation(imgv,foundationPane));
        imgv.setOnDragOver(dragOver(imgv));
    }
    void potAddEventHandlers(ImageView imgv,Card c) {
        imgv.setOnDragDetected(dragDetected(imgv,c));
    }
    private EventHandler<MouseEvent> dragDetected(ImageView img,Card card) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cardBeingDragged = card;
                Dragboard dragboard =  img.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(card.getId());
                dragboard.setContent(content);
                for (StapelPane st:view.getStapelPanes()) {
                    if (st.getCardsOnStapel().contains(card)) {
                        multipleCards= !st.getCardOnTop().equals(card);
                        source = st.getStapel().ordinal();
                        break;
                    }
                }
                if (model.getDeck().getCards().contains(card)) {
                    multipleCards= false;
                    source = -1;
                }
                event.consume();

            }
        };
    }
    private EventHandler<DragEvent> dragOver(ImageView imageView) {
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != imageView && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        };
    }
    private EventHandler<DragEvent> dragDropped(Card cardTarget) {
       return new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                       Card c = model.getDeck().idToCard(db.getString());
                    if (cardTarget.kanErop(c)) {
                        if (multipleCards) {
                            for (StapelPane st:view.getStapelPanes()) {
                                if (st.getCardsOnStapel().contains(cardTarget)) {
                                    view.updateMultipleCards(st.getStapel().ordinal(),source,db.getString());
                                }
                            }
                        }
                        else {
                            for (StapelPane st:view.getStapelPanes()) {
                                if (st.getCardsOnStapel().contains(cardTarget)) {
                                    view.updateStapels(st.getStapel().ordinal(),source,db.getString());
                                }
                            }
                        }

                    }
                }
                event.consume();
            }
        };
    }
    private EventHandler<DragEvent> dragDroppedEmpty(ImageView imageView) {
        return new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                   if (cardBeingDragged.getRank().equals(Ranks.KONING)) {
                        if (multipleCards) {
                            view.updateMultipleCards(emptyPanes.get(imageView).getStapel().ordinal(),source,db.getString());
                            emptyPanes.remove(imageView);
                        }
                        else {
                            view.updateStapels(emptyPanes.get(imageView).getStapel().ordinal(),source,db.getString());
                            emptyPanes.remove(imageView);
                        }
                    }
                    else  {
                        event.consume();
                    }
                }
                event.consume();
            }
        };
    }
    private EventHandler<DragEvent> dragDroppedFoundation(ImageView imageView,FoundationPane pane) {
        return new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                        if (cardBeingDragged.getRank().equals(Ranks.AAS)) {
                            view.updateFoundations(db.getString(),source);
                        }
                        else {
                               if (pane.getHighestRank()!=null) {
                                   if (pane.getHighestRank().hasNext() && pane.getHighestRank().getNext().equals(cardBeingDragged.getRank())) {
                                       view.updateFoundations(db.getString(),source);
                            }
                        }
                        boolean gewonnen =false;
                        for (FoundationPane pane:view.getFoundationPanes()) {
                            if (pane.getHighestRank()==null || !pane.getHighestRank().equals(Ranks.KONING)) {
                                gewonnen = false;
                                break;
                            }
                            gewonnen = true;
                        }
                        if (gewonnen) {
                            final Stage stage = new Stage();
                            PopUpWon popUpWon = new PopUpWon(model,tijd);
                            Scene scene = new Scene(popUpWon);
                            stage.setTitle("Congratulations you won");
                            stage.setScene(scene);
                            stage.show();
                            model.gewonnen(tijd);
                        }
                    }

                }
                event.consume();
            }
        };
    }

    private void startTimer() {
            this.tijd = 0;
            this.timeline = new Timeline();
            this.timeline.setCycleCount(Timeline.INDEFINITE); // Anders maar 1 keer
            this.timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    tijd++;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Time:\t");
                    stringBuilder.append(tijd/60);
                    stringBuilder.append(":");
                    if (tijd%60 <10) {
                        stringBuilder.append("0");
                    }
                    if (tijd%60 == 0) {
                        model.minutePassed();
                        view.getVboxView().getScore().setText(model.getScore());
                    }
                    stringBuilder.append(tijd%60);
                    view.getVboxView().getTijd().setText(stringBuilder.toString());
                }
            }));
        timeline.playFromStart(); // Om de eventhandler aan te spreken
    }
}
