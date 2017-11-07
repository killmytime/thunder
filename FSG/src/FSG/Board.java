package FSG;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

import static FSG.Control.*;

/**
 * Created by leiwe on 2016/12/28.
 */
public class Board implements something {
    Stage stage=new Stage();
    Pane board = new Pane();//棋盘面板
    int a, b;//记坐标
    public static int num = 0;
    File[] fileMusic = {new File("src/music/Departures.mp3"), new File("src/music/Tuyo.mp3"), new File("src/music/不再见.mp3")};//音乐文件
    Media[] media = {new Media(fileMusic[0].toURI().toString()), new Media(fileMusic[1].toURI().toString()), new Media(fileMusic[2].toURI().toString())};//文件路径变换一下（虽然感觉有点奇怪）
    MediaPlayer[] mediaPlayer = {new MediaPlayer(media[0]), new MediaPlayer(media[1]), new MediaPlayer(media[2])};//可执行文件

    public void Board(Stage stage) {
        board.setPadding(new Insets(Distance, Distance, Distance, Distance));//页边距
        //菜单栏
        Menu choosemenu = new Menu("选项");
        MenuItem restartmenu = new MenuItem("重新开始(R)");
        MenuItem regretmenu = new MenuItem("悔棋(B)");
        MenuItem savemenu = new MenuItem("存档(S)");
        MenuItem homemenu = new MenuItem("返回主菜单");
        choosemenu.getItems().addAll(restartmenu, regretmenu, savemenu, homemenu);
        Menu helpmenu = new Menu("帮助");
        MenuItem help = new MenuItem("关于五子棋");
        helpmenu.getItems().add(help);
        Menu musicmenu = new Menu("设置音乐");
        MenuItem music1 = new Menu("Departures");
        music1.setOnAction(event -> {
            mediaPlayer[1].stop();//停止播放
            mediaPlayer[2].stop();
            mediaPlayer[0].play();//播放
            mediaPlayer[0].setCycleCount(100);//循环，100次估计达不到吧
        });
        MenuItem music2 = new Menu("Tuyo");
        music2.setOnAction(event -> {
            mediaPlayer[0].stop();
            mediaPlayer[2].stop();
            mediaPlayer[1].play();
            mediaPlayer[1].setCycleCount(100);
        });
        MenuItem music3 = new Menu("不再见");
        music3.setOnAction(event -> {
            mediaPlayer[0].stop();
            mediaPlayer[1].stop();
            mediaPlayer[2].play();
            mediaPlayer[2].setCycleCount(100);
        });
        MenuItem music4 = new Menu("静音");
        music4.setOnAction(event -> {
            mediaPlayer[0].stop();
            mediaPlayer[1].stop();
            mediaPlayer[1].stop();
        });
        musicmenu.getItems().addAll(music1, music2, music3, music4);
        Menu stylemenu = new Menu("风格");
        MenuItem style1 = new MenuItem("海");
        style1.setOnAction(event -> {
            board.setStyle("-fx-background-image:url(image/海.jpg)");//背景图片
        });
        MenuItem style2 = new MenuItem("思");
        style2.setOnAction(event -> {
            board.setStyle("-fx-background-image:url(image/思.jpg)");
        });
        MenuItem style3 = new MenuItem("伞");
        style3.setOnAction(event -> {
            board.setStyle("-fx-background-image:url(image/伞.jpg)");
        });
        stylemenu.getItems().addAll(style1, style2, style3);
        MenuBar menuBar = new MenuBar(choosemenu, helpmenu, musicmenu, stylemenu);
        menuBar.setPrefWidth(1000);
        menuBar.setStyle("-fx-background-color:#66666666");
        VBox vBox = new VBox(25, menuBar, new Time().TimePane());
        board.getChildren().addAll(vBox);
        //画棋盘
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < Width; j++) {
                //画横线和竖线
                Line line = new Line(Distance + i * Size, Distance + j * Size, Distance + 14 * Size, Distance + j * Size);
                line.setStroke(Color.ORANGE);
                Line line1 = new Line(Distance + i * Size, Distance + j * Size, Distance + i * Size, Distance + 14 * Size);
                line1.setStroke(Color.ORANGE);
                board.getChildren().addAll(line, line1);
            }
        }
        DownChess(board);
        board.setStyle("-fx-background-image:url(image/海.jpg)");
        Scene scene = new Scene(board, 900, 900);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Scene finalScene = scene;
        help.setOnAction(event -> {//帮助
            new Control().helpevent(stage, finalScene, board);
        });
        restartmenu.setOnAction(event -> {
            new Control().restartevent(stage);
        });
        regretmenu.setOnAction(event -> {//悔棋
            if (num >= 1) {
                board.getChildren().remove(circles[num - 1]);
                circles[num - 1] = null;
                a = (int) stacka.pop();
                b = (int) stackb.pop();
                state[a][b] = 0;
                num--;
            }
        });
        scene.setOnKeyPressed(event -> {//快捷键
            if (event.getCode() == KeyCode.R) {
                new Control().restartevent(stage);
            } else if (event.getCode() == KeyCode.B) {
                if (num >= 1) {
                    board.getChildren().remove(circles[num - 1]);
                    circles[num - 1] = null;
                    a = (int) stacka.pop();
                    b = (int) stackb.pop();
                    state[a][b] = 0;
                    num--;
                }
            } else if (event.getCode() == KeyCode.S) {
                new Control().save();
            }
        });
        homemenu.setOnAction(event -> {//返回主菜单
            Stage stage1 = new Stage();
            Text text = new Text("确认返回主菜单？");
            text.setFont(Font.font("黑体"));
            Button yesBt = new Button("是");
            yesBt.setStyle("-fx-background-color:#66666666");

            Button noBt = new Button("否");
            noBt.setStyle("-fx-background-color:#66666666");
            HBox hBox = new HBox(100, yesBt, noBt);
            hBox.setAlignment(Pos.CENTER);
            VBox vBox1 = new VBox(50, text, hBox);
            Pane pane=new Pane(vBox1);
            pane.setStyle("-fx-background-color:#66666666");
            Scene scene1 = new Scene(pane);
            stage1.setScene(scene1);
            stage1.show();
            yesBt.setOnAction(event1 -> {
                new Start().start(stage);
                stage1.close();
                new Control().initial();
            });
            noBt.setOnAction(event1 -> {
                stage1.close();
            });
        });
        savemenu.setOnAction(event -> {
            new Control().save();
        });
    }

    void DownChess(Pane pane) {//下棋
        try {
            pane.setOnMouseClicked(event -> {
                if (!win(state,stage)) {//如果没有胜负
                    if (event.getX() >= Distance && event.getX() <= Distance + 14 * Size && event.getY() >= Distance && event.getY() <= Distance + 14 * Size) {//如果点在棋盘内
                        for (int px = 0; px < 16; px++) {
                            if (event.getX() >= px * Size + Distance - Size / 2 && event.getX() <= px * Size + Distance + Size / 2)
                                a = px;
                        }
                        for (int py = 0; py < 16; py++) {
                            if (event.getY() >= py * Size + Distance - Size / 2 && event.getY() <= py * Size + Distance + Size / 2)
                                b = py;
                        }
                        if (state[a][b] == VOID) {
                            if (num % 2 == 0) {
                                circles[num] = new Circle(a * Size + Distance, b * Size + Distance, Size * 0.5, Color.BLACK);
                                pane.getChildren().add(circles[num]);
                                num++;
                                state[a][b] = BLACK;
                            } else {
                                circles[num] = new Circle(a * Size + Distance, b * Size + Distance, Size * 0.5, Color.WHITE);
                                pane.getChildren().add(circles[num]);
                                num++;
                                state[a][b] = WHITE;
                            }
                            stacka.push(a);
                            stackb.push(b);
                            win(state,stage);
                        }
                    }
                }
            });
        } catch (Exception e) {
            //不处理
        }
    }
}
