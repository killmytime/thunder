package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import style.DisplayStyle;

import java.io.IOException;

public class Start extends Application {
    private DisplayStyle displayStyle=new DisplayStyle();
    private Stage start =new Stage();
    public void start(Stage stage){
        Pane pane=new Pane();
        HBox selection=new HBox();
        VBox all=new VBox();
        Text welcome=new Text("雷压1.0欢迎您，颜值不够，背景来凑");
        Button compressBt=new Button("压缩");
        displayStyle.setButtonRegion(compressBt);
        compressBt.setOnAction(event -> {
            try {
                start.hide();
                new Body(0, start);
            }catch (IOException e){
                e.printStackTrace();
            }

        });
        Button deCompressBt=new Button("解压");
        displayStyle.setButtonRegion(deCompressBt);
        deCompressBt.setOnAction(event -> {
            try {
                start.hide();
                new Body(1, start);
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        Button exitBt=new Button("退出");
        displayStyle.setButtonRegion(exitBt);
        exitBt.setOnAction(event -> start.close());
      welcome.setFont(Font.font("微软雅黑", FontWeight.BOLD, FontPosture.REGULAR,20));
      selection.getChildren().addAll(compressBt,deCompressBt,exitBt);
      selection.setSpacing(60);
      selection.setAlignment(Pos.CENTER);
      welcome.setTextAlignment(TextAlignment.CENTER);
      all.getChildren().addAll(welcome,selection);
     all.setPrefSize(600,400);
      all.setSpacing(150);
      pane.getChildren().add(all);
        //这里很奇怪。。../image/sea.jpg就不行，，，
        pane.setStyle("-fx-background-image:url(image/sea.jpg)");
        Scene scene=new Scene(pane,600,400);
        start.setScene(scene);
        start.setTitle("雷压 Version1.0 Just for fun By Heart");
        start.show();
    }
}
