package com.avatarduel;

import com.avatarduel.model.Element;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PowerController implements Initializable {

    @FXML
    private Pane powerContainer;
        
    @FXML
    private Label fireElmt;

    @FXML
    private Label waterElmt;

    @FXML
    private Label airElmt;

    @FXML
    private Label earthElmt;

    @FXML
    private Label energyElmt;
    
    public void initialize(URL url, ResourceBundle rb) {
        // Do nothing
    }
    
    public void setPowerPoint(Player p){
        this.fireElmt.setText(p.getSpecificPower(Element.FIRE));
        this.waterElmt.setText(p.getSpecificPower(Element.WATER));
        this.airElmt.setText(p.getSpecificPower(Element.AIR));
        this.earthElmt.setText(p.getSpecificPower(Element.EARTH));
//        this.energyElmt.setText(p.getSpecificPower(Element.ENERGY));
    }
}
