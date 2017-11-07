package FSG;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Created by leiwe on 2016/12/28.
 */
public class Start extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(50);
        vBox.setStyle("-fx-background:transparent;");
        vBox.setAlignment(Pos.CENTER);
        primaryStage.setTitle("五子棋");
        Button startBt = new Button("新的历程");
        Button continueBt = new Button("重回江湖");
        Button leaveBt = new Button("封剑归隐");
        getFirstRegion(startBt);//按钮设置
        getFirstRegion(continueBt);
        getFirstRegion(leaveBt);
        vBox.getChildren().addAll(startBt, continueBt, leaveBt);
        Pane pane0 = new BorderPane(vBox);
        pane0.setStyle("-fx-background-image:url(image/海.jpg)");
        Scene scene = new Scene(pane0, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        startBt.setOnAction(event -> {
            new Board().Board(primaryStage);
        });
        continueBt.setOnAction(event -> {
            new Control().Continue(primaryStage);
        });
        leaveBt.setOnAction(event -> {
            primaryStage.close();
        });
    }

    private void getFirstRegion(Button button) {//按钮设置
       button.setStyle("-fx-background-color:#66666666");//颜色
        button.setFont(Font.font("黑体", FontWeight.BOLD, FontPosture.ITALIC, 30));//字体设置
    }
}
