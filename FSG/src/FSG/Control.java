package FSG;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import static FSG.Board.*;
import static FSG.Time.second;

/**
 * Created by leiwe on 2016/12/28.
 */
public class Control implements something {
    public static boolean win(int[][] state,Stage stage) {
        //全局扫描，判断输赢
        for (int a = 0; a < Height; a++) {
            for (int b = 0; b < Width; b++) {
                if (state[a][b] == BLACK || state[a][b] == WHITE) {
                    if (b >= 0 && b <= 10 && state[a][b] == state[a][b + 1] && state[a][b] == state[a][b + 2] && state[a][b] == state[a][b + 3] && state[a][b] == state[a][b + 4]) {
                        output(state[a][b]);//胜负提示界面
                        return true;
                    }
                    if (a >= 0 && a <= 10 && state[a][b] == state[a + 1][b] && state[a][b] == state[a + 2][b] && state[a][b] == state[a + 3][b] && state[a][b] == state[a + 4][b]) {
                        output(state[a][b]);
                        return true;
                    }
                    if (a >= 0 && a <= 10 && b >= 0 && b <= 10 && state[a][b] == state[a + 1][b + 1] && state[a][b] == state[a + 2][b + 2] && state[a][b] == state[a + 3][b + 3] && state[a][b] == state[a + 4][b + 4]) {
                        output(state[a][b]);
                        return true;
                    }
                    if (a >= 0 && a <= 10 && b >= 4 && b <= 14 && state[a][b] == state[a + 1][b - 1] && state[a][b] == state[a + 2][b - 2] && state[a][b] == state[a + 3][b - 3] && state[a][b] == state[a + 4][b - 4]) {
                        output(state[a][b]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void helpevent(Stage stage, Scene scene, Pane pane) {
        //帮助界面
        Button returnBt = new Button("返回");
        returnBt.setOnAction(event -> {
            stage.setScene(scene);
        });
        Text HelpText = new Text("\n关于五子棋\n获胜条件：五子连成一条线即可获胜,\n快捷键：按B快速悔棋，按R快速重新开始，按S快速存档\n其他功能：可以在菜单栏里切换背景图片和音乐或者开启静音模式(默认静音)");
        HelpText.setFont(Font.font("黑体", FontWeight.BOLD, FontPosture.ITALIC, 30));
        returnBt.setAlignment(Pos.CENTER);
        returnBt.setFont(Font.font("黑体", FontWeight.BOLD, FontPosture.ITALIC, 30));
        VBox vBox = new VBox(500, HelpText, returnBt);
        vBox.setAlignment(Pos.CENTER);
        Pane pane1 = new Pane(vBox);
        pane1.setStyle(pane.getStyle());
        stage.setScene(new Scene(pane1));
        stage.show();
    }

    static void initial() {//棋盘初始化
        num = 0;
        second=0;
        Time.timeline.stop();
        for (int i = 0; i < Height * Width; i++) {
            circles[i] = null;
        }
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < Width; j++) {
                state[i][j] = 0;
            }
        }
    }

    static void restartevent(Stage stage) {//重新开始
        initial();
        num = 0;
        new Board().Board(stage);
    }

    static void output(int Color) {//胜利提示
        Stage stage = new Stage();
        switch (Color) {
            case BLACK:
                Pane pane ;

                Text text = new Text("是否开始一场新游戏？");
                text.setFont(Font.font("黑体"));
                Button yesBt = new Button("是");
                yesBt.setStyle("-fx-background-color:#66666666");
                yesBt.setFont(Font.font("黑体"));
                yesBt.setOnAction(event -> {
                    initial();
                    num = 0;
                    second = 0;
                    stage.close();
                });
                Button noBt = new Button("否");
                noBt.setStyle("-fx-background-color:#66666666");
                noBt.setFont(Font.font("黑体"));
                noBt.setOnAction(event -> {
                    stage.close();
                });
                HBox hBox = new HBox(100, yesBt, noBt);
                hBox.setAlignment(Pos.CENTER);
                VBox vBox1 = new VBox(50, text, hBox);
                pane = new Pane(vBox1);
                pane.setStyle("-fx-background-image:url(image/黑.jpg)");
                Scene scene2 = new Scene(pane, 480, 360);
                stage.setScene(scene2);
                stage.show();
                break;
            case WHITE:
                 text = new Text("是否开始一场新游戏？");
                 text.setFont(Font.font("黑体"));
                 yesBt = new Button("是");
                 yesBt.setFont(Font.font("黑体"));
                yesBt.setOnAction(event -> {
                    initial();
                    num = 0;
                    second = 0;
                    stage.close();
                    new Board().Board(stage);
                });
                yesBt.setStyle("-fx-background-color:#66666666");
                 noBt = new Button("否");
                 noBt.setFont(Font.font("黑体"));
                noBt.setStyle("-fx-background-color:#66666666");
                noBt.setOnAction(event -> {
                    stage.close();
                });
                 hBox = new HBox(100, yesBt, noBt);
                hBox.setAlignment(Pos.CENTER);
                 vBox1 = new VBox(50, text, hBox);
                pane = new Pane(vBox1);
                pane.setStyle("-fx-background-image:url(image/白.jpg)");
                scene2 = new Scene(pane, 238, 304);
                stage.setScene(scene2);
                stage.show();
                break;
        }
    }

    void save() {//存档
        File file = new File("D:\\FSG\\src\\save.txt");
        try {
            file.createNewFile();
            PrintWriter output = new PrintWriter(file);
            output.println(num);
            output.println(second);
            for (int i = 0; i < 225; i++) {
                output.println(circles[i]);
            }
            output.close();
            Stage stage3 = new Stage();
            Text text = new Text("\n存档成功");
            text.setFont(Font.font("黑体", FontWeight.BOLD, FontPosture.ITALIC, 30));
            text.setTextAlignment(TextAlignment.CENTER);
            Pane pane3 = new Pane(text);
            pane3.setStyle("-fx-background-color:#66666666");
            Scene scene3 = new Scene(pane3, 100, 100);
            stage3.setScene(scene3);
            stage3.show();
        } catch (Exception e) {
            Stage stage3 = new Stage();
            Text text = new Text("\n存档失败，请重试");
            text.setFont(Font.font("黑体", FontWeight.BOLD, FontPosture.ITALIC, 30));
            Pane pane3 = new Pane(text);
            Scene scene3 = new Scene(pane3, 400, 100);
            stage3.setScene(scene3);
            stage3.show();
            e.printStackTrace();
        }
    }

    void Continue(Stage stage) {//读档有问题
        File file = new File("D:\\FSG\\src\\save.txt");
        Pane pane = new Pane();
        new Board().Board(stage);
        try {
            Scanner input = new Scanner(file);
            num = Integer.parseInt(input.nextLine());
            second = Integer.parseInt(input.nextLine());
            for (int i = 0; i < num; i++) {
                String circleregion = input.nextLine();
                int px = Integer.parseInt(circleregion.substring(15, 18));
                int py = Integer.parseInt(circleregion.substring(30, 33));
                circles[num] = new Circle(px, py, Size * 0.5);
                if (num % 2 == 0) {
                  circles[num].setFill(Color.BLACK);
                 } else if (num % 2 == 1) {
                   circles[num].setFill(Color.WHITE);
            }pane.getChildren().addAll(circles);
             }stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
        }
    }
}

