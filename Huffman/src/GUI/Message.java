package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Message {
 public Message(String Type){
     Stage stage=new Stage();
     Text content;
     Button backBt=new Button("确定");
     Pane pane=new Pane();
     VBox vBox=new VBox();
     content=new Text(Type);
     vBox.getChildren().addAll(content,backBt);
        backBt.setOnAction(event -> stage.close());
        pane.getChildren().add(vBox);
        vBox.setPrefSize(200,100);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
       Scene scene=new Scene(pane,200,100);
       stage.setScene(scene);
       stage.setTitle("雷压 Version1.0 Just for fun By Heart");
       stage.show();
    }

}
