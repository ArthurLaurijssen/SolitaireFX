package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.SolitaireModel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

public class SolitairePresenter {
    private SolitaireView view;
    private SolitaireModel model;
    private int source;

    public SolitairePresenter(SolitaireView view, SolitaireModel model) {
        this.view = view;
        this.model = model;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        // 7 stapels
        for (StapelPane stapelPane: view.getStapelPanes()) {
            stapelPane.setOnDragDetected(dragDetected(stapelPane));

            stapelPane.setOnDragOver(new EventHandler<DragEvent>() {
                        @Override
                        public void handle(DragEvent event) {
                            if (event.getGestureSource() != stapelPane &&
                                    event.getDragboard().hasString()) {
                                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                            }
                            event.consume();
                        }
                    });
            stapelPane.setOnDragExited(Event::consume);
            stapelPane.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        view.updateStapels(stapelPane.getStapel().ordinal(),source,db.getString());

                        success = true;
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
        }
        //4 stapels
        for (FoundationPane foundationPane: view.getFoundationPanes()) {
            foundationPane.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {

                }
            });
            foundationPane.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {

                }
            });
            foundationPane.setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {

                }
            });
        }

    }
    private void updateView() {

    }





    private EventHandler<MouseEvent> dragDetected(StapelPane stapelPane) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard dragboard =  stapelPane.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString( stapelPane.getCardOnTop().getId());
                dragboard.setContent(content);
                source = stapelPane.getStapel().ordinal();
                event.consume();
            }
        };
    }

}
