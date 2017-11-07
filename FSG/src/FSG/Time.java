package FSG;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by leiwe on 2016/12/28.
 */
public class Time {
    static int second = 0;//初始时间
    static Timeline timeline;//时间轴

    public Pane TimePane() {
        Pane pane = new Pane();
        Text time = new Text();
        pane.getChildren().add(time);
        EventHandler<ActionEvent> eventEventHandler = e -> {
            time.setText("       用时 : " + time() + "                " + "黑棋子数 : " + (Board.num + 1) / 2 + "                " + "白棋子数 : " + Board.num / 2);};
        time.setFont(Font.font("黑体" , FontWeight.BOLD, FontPosture.ITALIC,30));
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), eventEventHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return pane;
    }

    String time() {
            second++;
        int minute = second / 60;//换算
        int Second = second % 60;
        int hour = second / 3600;
        return hour + ":" + minute + ":" + Second;
    }
}
