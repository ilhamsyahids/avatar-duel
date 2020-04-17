package com.avatarduel.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.avatarduel.model.card.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.avatarduel.model.card.Mode;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.GameState;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.SkillAura;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ArenaController implements Initializable, Rendered {

    @FXML
    private Pane utama;
    @FXML
    private Label enemyHP;
    @FXML
    private Label myHP;
    @FXML
    private GridPane enemyField;
    @FXML
    private GridPane myField;
    @FXML
    private HBox otherHand;
    @FXML
    private HBox enemySkillArea;
    @FXML
    private HBox enemyCharArea;
    @FXML
    private HBox mySkillArea;
    @FXML
    private HBox myCharArea;
    @FXML
    private HBox myHand;
    @FXML
    private ProgressBar enemyBar;
    @FXML
    private ProgressBar myBar;
    @FXML
    private Label playerTwo;
    @FXML
    private Label playerOne;
    @FXML
    private Pane fillMyCard;
    @FXML
    private Pane fillEnemyCard;
    @FXML
    private Pane paneHover;
    @FXML
    private Pane imageHover;
    @FXML
    private Label nameHover;
    @FXML
    private Label elementHover;
    @FXML
    private Label attackHover;
    @FXML
    private Label defenceHover;
    @FXML
    private Label powerHover;
    @FXML
    private Label myCountCard;
    @FXML
    private Label enemyCountCard;
    @FXML
    private Label descriptionHover;
    @FXML
    private Label typeClass;
    @FXML
    private Button endPhase;
    @FXML
    private Rectangle rectElemen;
    @FXML
    private Rectangle colorCard;
    @FXML
    private Rectangle changePhase;
    @FXML
    private Label drawTextLabel;
    @FXML
    private Label mainTextLabel;
    @FXML
    private Label battleTextLabel;
    @FXML
    private Label endTextLabel;
    @FXML
    private Label gameMessage;
    @FXML
    private Button directAttack;
    @FXML
    private Label skillAttach;
    
    FXMLLoader loaderPower1;
    FXMLLoader loaderPower2;

    private static final String HOVERED_CARD_STYLE = "-fx-opacity: 0.5;";
    private static final String IDLE_CARD_STYLE = "-fx-opacity: 1;";
    private static final String REMOVE_CARD_STYLE = "-fx-opacity: 0;";

    /**
     * @param positon positon in Y-axis
     */
    public void changePhasePosition(double position) {
        this.changePhase.setLayoutY(position);
    }

    /**
     * @param msg the message to gameMessage
     */
    public void setGameMessage(String msg) {
        this.gameMessage.setFont(Font.loadFont("file:src/main/resources/com/avatarduel/card/data/fonts/RAVIE.ttf", 38));
        this.gameMessage.setText(msg);
        Timeline blinker = createBlinker(gameMessage);
        blinker.setOnFinished(event -> gameMessage.setText(""));
        FadeTransition fader = createFader(gameMessage);
        SequentialTransition blinkThenFade = new SequentialTransition(gameMessage, blinker, fader);
        blinkThenFade.play();
    }

    public Timeline createBlinker(Node node) {
        Timeline blink = new Timeline(
                new KeyFrame(Duration.seconds(1.5), new KeyValue(node.opacityProperty(), 1, Interpolator.DISCRETE)));
        blink.setCycleCount(1);

        return blink;
    }

    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.25), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }

    /**
     * @return the drawTextLabel
     */
    public Label getDrawTextLabel() {
        return drawTextLabel;
    }

    /**
     * @return the directAttack
     */
    public Button getDirectAttack() {
        return directAttack;
    }

    /**
     * @return the mainTextLabel
     */
    public Label getMainTextLabel() {
        return mainTextLabel;
    }

    /**
     * @return the battleTextLabel
     */
    public Label getBattleTextLabel() {
        return battleTextLabel;
    }

    /**
     * @return the endTextLabel
     */
    public Label getEndTextLabel() {
        return endTextLabel;
    }

    /**
     * @return the endPhase
     */
    public Button getEndPhase() {
        return endPhase;
    }

    /**
     * @return the myCharArea
     */
    public HBox getMyCharArea() {
        return myCharArea;
    }

    /**
     * @return the mySkillArea
     */
    public HBox getMySkillArea() {
        return mySkillArea;
    }

    /**
     * Init scene
     */
    public void initialize(URL url, ResourceBundle rb) {
        loaderPower1 = new FXMLLoader();
        loaderPower1.setLocation(getClass().getResource("PowerUI.fxml"));

        loaderPower2 = new FXMLLoader();
        loaderPower2.setLocation(getClass().getResource("PowerUI.fxml"));

        try {
            powerBoard();
        } catch (IOException ex) {
            Logger.getLogger(ArenaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground("file:src/main/resources/com/avatarduel/card/image/background/arena.JPG");
        renderStackOfCards();
    }

    /**
     * Specify reference to power
     */
    public void powerBoard() throws IOException {
        Parent power2 = (Parent) loaderPower2.load();
        power2.setLayoutX(548);
        power2.setLayoutY(179);

        Node power1 = (Node) loaderPower1.load();
        power1.setLayoutX(1201);
        power1.setLayoutY(366);

        utama.getChildren().addAll(power1, power2);
    }

    /**
     * Render power of each player
     */
    public void renderPower() {
        Player p1 = GameState.getInstance().getCurrentPlayer();
        Player p2 = GameState.getInstance().getOtherPlayer();

        PowerController pc1 = loaderPower1.getController();
        pc1.setPowerPoint(p1);

        PowerController pc2 = loaderPower2.getController();
        pc2.setPowerPoint(p2);
    }

    /**
     * Set background of this scene
     * 
     * @param pictPath path of background
     */
    public void setBackground(String pictPath) {
        Image image = new Image(pictPath);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, false, false, false, true));

        Background background = new Background(backgroundImage);
        utama.setBackground(background);
    }

    /**
     * Render stack of cards (static)
     */
    public void renderStackOfCards() {
        Image card = new Image(
                new File("src/main/resources/com/avatarduel/card/image/background/flip.PNG").toURI().toString(), 93, 68,
                false, false);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
    }

    /**
     * Render GUI
     */
    public void render() {
        myHand.getChildren().clear();
        myCharArea.getChildren().clear();
        mySkillArea.getChildren().clear();
        otherHand.getChildren().clear();
        enemyCharArea.getChildren().clear();
        enemySkillArea.getChildren().clear();

        Player myPlayer = GameState.getInstance().getCurrentPlayer();
        Player enemyPlayer = GameState.getInstance().getOtherPlayer();

        // ME
        myPlayer.getDeck().getHandCards().forEach(item -> {
            CardUI cardUI = new CardUI(item);
            myHand.getChildren().add(cardUI);
            if (Phase.getInstancePhase().getFase() == Phase.Fase.MAIN) {
                cardUI.setHandDialog();
            } else {
                cardUI.emptyCardDialog();
            }
            setHover(cardUI);
        });
        myPlayer.getDeck().getCharacters().forEach(item -> {
            CardUI cardUI = new CardUI(item);
            if (((Character) cardUI.getCard()).getMode() == Mode.DEFENSE) {
                cardUI.imageView.setRotate(90);
            }
            myCharArea.getChildren().add(cardUI);
            // Jika fasenya Main tampilin dialog destroy
            if(Phase.getInstancePhase().getFase() == Phase.Fase.MAIN){
                cardUI.setCharacterDialogInFieldMainPhase(true);
            }
            else if (Phase.getInstancePhase().getFase() == Phase.Fase.BATTLE) {
                cardUI.setCharacterDialogInFieldBattlePhase();
            } else {
                cardUI.emptyCardDialog();
            }
            setHover(cardUI);
        });
        myPlayer.getDeck().getSkills().forEach(item -> {
            CardUI cardUI = new CardUI(item);
            mySkillArea.getChildren().add(cardUI);
            if(Phase.getInstancePhase().getFase() == Phase.Fase.MAIN){
                cardUI.setCharacterDialogInFieldMainPhase(true);
            }
            setHover(cardUI);
        });

        // ENEMY
        enemyPlayer.getDeck().getCharacters().forEach(item -> {
            CardUI cardUI = new CardUI(item);
            if(Phase.getInstancePhase().getFase() == Phase.Fase.MAIN){
                cardUI.setCharacterDialogInFieldMainPhase(false);
            }
            if (((Character) cardUI.getCard()).getMode() == Mode.DEFENSE) {
                cardUI.imageView.setRotate(90);
            }
            enemyCharArea.getChildren().add(cardUI);
            if (CardUI.getPowerAttack() != 9999) {
                cardUI.setCharacterDialogAttackedInField();
            } else {
                cardUI.emptyCardDialog();
            }
            setHover(cardUI);
        });
        enemyPlayer.getDeck().getSkills().forEach(item -> {
            CardUI cardUI = new CardUI(item);
            if(Phase.getInstancePhase().getFase() == Phase.Fase.MAIN){
                cardUI.setCharacterDialogInFieldMainPhase(false);
            }
            enemySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        enemyPlayer.getDeck().getHandCards().forEach(item -> {
            Image img = new Image(
                    new File("src/main/resources/com/avatarduel/card/image/background/flip.PNG").toURI().toString(), 70,
                    72, false, false);
            ImageView imageView = new ImageView(img);
            otherHand.getChildren().add(imageView);
        });

        renderCountRestTakeCards();
        renderPower();
        setParamLife(myPlayer.getHp(), enemyPlayer.getHp());

        // Change color of life bar base on player HP
        if (GameState.getInstance().getCurrentPlayer().getHp() <= 80) {
            myBar.setStyle("-fx-accent: green");
            if (GameState.getInstance().getCurrentPlayer().getHp() <= 40) {
                myBar.setStyle("-fx-accent: yellow");
                if (GameState.getInstance().getCurrentPlayer().getHp() <= 20) {
                    myBar.setStyle("-fx-accent: red");
                }
            }
        }

        if (GameState.getInstance().getOtherPlayer().getHp() <= 80) {
            enemyBar.setStyle("-fx-accent: green");
            if (GameState.getInstance().getOtherPlayer().getHp() <= 40) {
                enemyBar.setStyle("-fx-accent: yellow");
                if (GameState.getInstance().getOtherPlayer().getHp() <= 20) {
                    enemyBar.setStyle("-fx-accent: red");
                }
            }
        }

        checkWinner();
    }

    /**
     * Display details card when hover
     * 
     * @param cardUI CardUI for trigger the hover
     */
    public void setHover(CardUI cardUI) {
        cardUI.imageView.setOnMouseEntered(e -> {
            cardUI.setStyle(HOVERED_CARD_STYLE);
            Image img = new Image(new File(cardUI.getCard().getImage()).toURI().toString(), 200, 200, false, false);
            imageHover.getChildren().add(new ImageView(img));
            nameHover.setText(cardUI.getCard().getName());
            elementHover.setText(cardUI.getCard().getElement().toString());
            if (elementHover.getText().equalsIgnoreCase("FIRE")) {
                this.rectElemen.setFill(Color.web("#ff3333"));
                this.colorCard.setFill(Color.web("#ff8080"));
            } else if (elementHover.getText().equalsIgnoreCase("WATER")) {
                this.rectElemen.setFill(Color.web("#33ccff"));
                this.colorCard.setFill(Color.web("#80dfff"));
            } else if (elementHover.getText().equalsIgnoreCase("EARTH")) {
                this.rectElemen.setFill(Color.web("#b33c00"));
                this.colorCard.setFill(Color.web("#e64d00"));
            } else {
                this.rectElemen.setFill(Color.web("#e6ffff"));
                this.colorCard.setFill(Color.web("#ffffff"));
            }
            descriptionHover.setText(cardUI.getCard().getDescription());
            typeClass.setText(cardUI.getCard().getClass().getSimpleName());
            attackHover.setText("");
            defenceHover.setText("");
            powerHover.setText("");
            if (cardUI.getCard() instanceof Character) {
                Character cardChar = (Character) cardUI.getCard();
                attackHover.setText("ATK " + cardChar.getAttack());
                defenceHover.setText("DEF " + cardChar.getDefense());
                powerHover.setText("POW " + cardChar.getPower());
            } else if (Skill.class.isAssignableFrom(cardUI.getCard().getClass())) {
                Skill cardChar = (Skill) cardUI.getCard();
                powerHover.setText("POW " + cardChar.getPower());
                if (cardUI.getCard() instanceof SkillAura) {
                    SkillAura cardAura = (SkillAura) cardUI.getCard();
                    attackHover.setText("ATK " + cardAura.getAttack());
                    defenceHover.setText("DEF " + cardAura.getDefense());
                }
            }
            if (typeClass.getText().equalsIgnoreCase("Character")){
                Character cardChar = (Character) cardUI.getCard();
                cardChar.getCharSkills().forEach(skill -> skillAttach.setText("Skill attached: " + skill.getName()));
                cardChar.getCharSkills().forEach(skillAura -> {
                    if (skillAura instanceof SkillAura){
                        attackHover.setText("ATK " + (cardChar.getAttack() + ((SkillAura) skillAura).getAttack()));
                        defenceHover.setText("DEF " + (cardChar.getDefense() + ((SkillAura) skillAura).getDefense()));
                    }
                });
                
            }
            else{ skillAttach.setText(""); }
            paneHover.setStyle(IDLE_CARD_STYLE);
        });
        cardUI.setOnMouseExited(e -> {
            cardUI.setStyle(IDLE_CARD_STYLE);
            paneHover.setStyle(REMOVE_CARD_STYLE);
        });
    }

    /**
     * Render rest of player can take cards
     */
    public void renderCountRestTakeCards() {
        myCountCard.setText(GameState.getInstance().getCurrentPlayer().getDeck().getRestOfCanTakeCards());
        enemyCountCard.setText(GameState.getInstance().getOtherPlayer().getDeck().getRestOfCanTakeCards());
    }

    /**
     * Set life bar of the players
     * 
     * @param myLife    current player HP to set
     * @param enemyLife enemy player HP to set
     */
    public void setParamLife(Integer myLife, Integer enemyLife) {
        myHP.setText(myLife.toString());
        enemyHP.setText(enemyLife.toString());
        myBar.setProgress(calculateBar(myLife));
        enemyBar.setProgress(calculateBar(enemyLife));
    }

    /**
     * @return calculate life bar player
     */
    public double calculateBar(int life) {
        return 1.25 * life / 100;
    }

    /**
     * @return set name of two player
     */
    public void setName(String player1, String player2) {
        playerOne.setText(player1);
        playerTwo.setText(player2);
    }

    /**
     * Switch player name
     */
    public void switchPlayerName() {
        String temp = playerOne.getText();
        setName(playerTwo.getText(), temp);
    }

    public void directAttack() {
        CardUI.getCardAttack().attackOnPlayer(GameState.getInstance().getOtherPlayer());
        // si attacker udh nyerang karena udh menetukan target (suatu kartu dianggap udh
        // nyerang kalo dia udh nentuin targetnya jg)
        CardUI.getCardAttack().setIsAttackThisTurn(true);
        CardUI.setPowerAttack(9999); // kembalikan ke default value
        CardUI.setPowerAttacked(9999); // kembalikan ke default value
        directAttack.setVisible(false);
        setGameMessage("Direct Attack Berhasil");
        Phase.arenaController.render();
    }

    /**
     * Check winner game
     * 
     * @return player name, empty string for no player win
     */
    public String checkWinner() {
        Player myPlayer = GameState.getInstance().getCurrentPlayer();
        Player enemyPlayer = GameState.getInstance().getOtherPlayer();
        String win = "";
        if (myPlayer.getHp() <= 0 || myPlayer.getDeck().getLeftTakeCards() <= 0) {
            win = playerTwo.getText();
            this.utama.getChildren().clear(); // Kalo menang clear semuanya
            this.loaderPower1 = new FXMLLoader();
            this.loaderPower2 = new FXMLLoader();
        } else if (enemyPlayer.getHp() <= 0 || enemyPlayer.getDeck().getLeftTakeCards() <= 0) {
            win = playerOne.getText();
            this.utama.getChildren().clear();
            this.loaderPower1 = new FXMLLoader();
            this.loaderPower2 = new FXMLLoader();
        }

        return win;
    }

}
