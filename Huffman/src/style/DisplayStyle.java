package style;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public  class DisplayStyle {
    public   void setButtonRegion(Button button) {//按钮设置
        button.setStyle("-fx-background-color:#66666666");//颜色
        button.setMinHeight(30);
        button.setFont(Font.font("黑体", FontWeight.BOLD, FontPosture.ITALIC, 30));//字体设置
    }
}
