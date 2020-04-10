package com.avatarduel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.avatarduel.model.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.avatarduel.model.Mode;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.Deck;
import com.avatarduel.model.Element;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.Skill;
import com.avatarduel.model.SkillAura;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Label draw;
    @FXML
    private Label main1;
    @FXML
    private Label battle;
    @FXML
    private Label end;
    
    FXMLLoader loaderPower1;
    FXMLLoader loaderPower2;


    private static final String HOVERED_CARD_STYLE = "-fx-opacity: 0.5;";
    private static final String IDLE_CARD_STYLE = "-fx-opacity: 1;";
    private static final String REMOVE_CARD_STYLE = "-fx-opacity: 0;";

    public Rectangle changePhase(double position) {
        this.changePhase.setLayoutY(position);
        return this.changePhase;
    }

    public Label getDrawTextLabel() {
        return this.draw;
    }

    public Label getMain1TextLabel() {
        return this.main1;
    }

    public Label getBattleTextLabel() {
        return this.battle;
    }

    public Label getEndTextLabel() {
        return this.end;
    }

    public Button getButtonPhase() {
        return endPhase;
    }

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
        
        setBackground("file:background/arena.JPG");
        renderCountCard();
        
    }

    public void powerBoard() throws IOException{
        Parent power2 = (Parent)loaderPower2.load();
        power2.setLayoutX(548);
        power2.setLayoutY(179);

        Node power1 = (Node)loaderPower1.load();
        power1.setLayoutX(1201);
        power1.setLayoutY(366);
        
        utama.getChildren().addAll(power1,power2);
    }
    
    public void renderPower(){
        Player p1 = GameState.getInstance().getCurrentPlayer();
        Player p2 = GameState.getInstance().getOtherPlayer();
        
        PowerController pc1 = loaderPower1.getController();
        pc1.setPowerPoint(p1);
        
        PowerController pc2 = loaderPower2.getController();
        pc2.setPowerPoint(p2);
    }
    
    public void setBackground(String pict) {
        // Set background pada this scene
        Image image = new Image(pict);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, false, false, false, true));

        Background background = new Background(backgroundImage);
        utama.setBackground(background);
    }

    public void renderCountCard() {
        Image card = new Image(new File("background/flip.PNG").toURI().toString(), 93, 68, false, false);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
    }

    public void render() {
        myHand.getChildren().clear();
        myCharArea.getChildren().clear();
        mySkillArea.getChildren().clear();
        otherHand.getChildren().clear();
        enemyCharArea.getChildren().clear();
        enemySkillArea.getChildren().clear();

        // ME
        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item, Phase.getInstancePhase().getFase());
            myHand.getChildren().add(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item, Phase.getInstancePhase().getFase());
            if (cardUI.getCard().getMode() == Mode.DEFENSE) {
                cardUI.imageView.setRotate(90);
            }
            myCharArea.getChildren().add(cardUI);
            setCharactersDialogInField(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item, Phase.getInstancePhase().getFase());
            mySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });

        // ENEMY
        GameState.getInstance().getOtherPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item, Phase.Fase.END);
            if (cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(90);
            }
            enemyCharArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getOtherPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item, Phase.Fase.END);
            enemySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getOtherPlayer().getDeck().getHandCards().forEach(item -> {
            Image img = new Image(new File("background/flip.PNG").toURI().toString(), 70, 72, false, false);
            ImageView imageView = new ImageView(img);
            otherHand.getChildren().add(imageView);
        });
        
        renderCount();
        renderPower();
        setParamLife(GameState.getInstance().getCurrentPlayer().getHp(), GameState.getInstance().getOtherPlayer().getHp());
    }

    public void setCharactersDialogInField(KartuUI cardUI){
        cardUI.attack.setOnMouseClicked(e -> {
            // implementasikan attack disini Gil
            System.out.println("Aku nyerang"); // ini dummy aja

        });
        cardUI.changePosition.setOnMouseClicked(e -> {
            // implementasi changePosition
            if(cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(0);
                cardUI.getCard().setMode(Mode.ATTACK); // set mode ke attack
            }
            else{ //awalnya attack
                cardUI.imageView.setRotate(90);
                cardUI.getCard().setMode(Mode.DEFENSE); // set mode ke defense
            }
        });


        cardUI.imageView.setOnMouseClicked(el -> {
            if (cardUI.isClicked) {
                cardUI.HandDialog.getChildren().clear();
                cardUI.root.getChildren().clear();
                cardUI.root.getChildren().addAll(cardUI.imageView);
                cardUI.isClicked = false;
            } else {
                //cek mode dari kartu
                if(cardUI.getCard().getMode() == Mode.ATTACK){
                    cardUI.HandDialog.getChildren().addAll(cardUI.attack, cardUI.changePosition);
                }
                else{
                    cardUI.HandDialog.getChildren().addAll(cardUI.changePosition);
                }
                cardUI.root.getChildren().clear();
                cardUI.root.getChildren().addAll(cardUI.HandDialog,cardUI.imageView);
                cardUI.isClicked = true;
            }
        });
    }

    public void setHover(KartuUI cardUI) {
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
            paneHover.setStyle(IDLE_CARD_STYLE);
        });
        cardUI.setOnMouseExited(e -> {
            cardUI.setStyle(IDLE_CARD_STYLE);
            paneHover.setStyle(REMOVE_CARD_STYLE);
        });
    }

    public void renderCount() {
        myCountCard.setText(
                GameState.getInstance().getCurrentPlayer().getDeck().getLeftTakeCards() + "/" + Deck.MAXCARDSTAKKEN);
        enemyCountCard.setText(
                GameState.getInstance().getOtherPlayer().getDeck().getLeftTakeCards() + "/" + Deck.MAXCARDSTAKKEN);
    }

    public void setParamLife(Integer myLife, Integer enemyLife) {
        // Set parameter HPpoints dan HPBar sesuai dengan
        // life points player
        myHP.setText(myLife.toString());
        enemyHP.setText(enemyLife.toString());
        myBar.setProgress(calculateBar(myLife));
        enemyBar.setProgress(calculateBar(enemyLife));
    }

    public double calculateBar(int life) {
        // Melakukan perhitungan lifeBar player
        return 1.25 * life / 100;
    }

    public void setName(String player1, String player2) {
        playerOne.setText(player1);
        playerTwo.setText(player2);
    }
}
