package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Ranks;
import be.kdg.solitaire.model.Cards.Stapels;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.HashMap;

public class SolitairePresenter {
    private SolitaireView view;
    private SolitaireModel model;
    private int source;
    private Card cardBeingDragged;
    private HashMap<ImageView,StapelPane> emptyPanes;
    public SolitairePresenter(SolitaireView view, SolitaireModel model) {
        this.view = view;
        this.model = model;
        this.addEventHandlers();
        this.updateView();
        emptyPanes = new HashMap<>();
    }
    private void addEventHandlers() {
        
    }
    private void updateView() {

    }
    void addEventhandlers(ImageView imgv,Card card) {
        imgv.setOnDragDetected(dragDetected(imgv,card));
        imgv.setOnDragOver(dragOver(imgv));
        imgv.setOnDragDropped(dragDropped(imgv,card));
        imgv.setOnDragExited(Event::consume);
    }
    void emptyStapelEventHandlers(ImageView imgv, StapelPane stapelPane) {
        emptyPanes.put(imgv,stapelPane);
        imgv.setOnDragOver(dragOver(imgv));
        imgv.setOnDragDropped(dragDroppedEmpty(imgv));
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
                        source = st.getStapel().ordinal();
                    }
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
    private EventHandler<DragEvent> dragDropped(ImageView imageView,Card card) {
       return new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    for (StapelPane st:view.getStapelPanes()) {
                       if (st.getCardsOnStapel().contains(card)) {
                               view.updateStapels(st.getStapel().ordinal(),source,db.getString());
                       }
                    }
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        };
    }
    private EventHandler<DragEvent> dragDroppedEmpty(ImageView imageView) {
        return new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                System.out.println(true);
                if (db.hasString()) {
                    if (cardBeingDragged.getRank().equals(Ranks.KONING)) {
                        view.updateStapels(emptyPanes.get(imageView).getStapel().ordinal(),source,db.getString());
                        emptyPanes.remove(imageView);
                    }
                    else  {
                        event.consume();
                    }
                }
                event.consume();
            }
        };
    }
}
