package GUI;

import control.CompressTrim;
import control.DeCompressTrim;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import java.io.File;
import java.io.IOException;

import style.DisplayStyle;

class Body {
    private Stage main = new Stage();
    private Text inputFile;
    private Text outputPath;
    private TextField inputField = new TextField();
    private TextField outputField = new TextField();

    Body(int selection, Stage begin) throws IOException {
        inputField.setFont(Font.font("微软雅黑"));
        outputField.setFont(Font.font("微软雅黑"));
        Button outputBt = new Button("...");
        DisplayStyle displayStyle = new DisplayStyle();
        displayStyle.setButtonRegion(outputBt);
        //真的好丑的文件选择器，为了选择文件夹，只能放弃fileChooser了，，，
        outputBt.setOnAction(this::handleOutput);
        Button exitBt = new Button("退出");
        displayStyle.setButtonRegion(exitBt);
        exitBt.setOnAction(event -> {
            main.close();
            begin.close();
        });
        Button backBt = new Button("返回");
        displayStyle.setButtonRegion(backBt);
        backBt.setOnAction(event -> {
            main.close();
            begin.show();
        });
        Button startBt = new Button("开始");
        displayStyle.setButtonRegion(startBt);
        Button inputBt = new Button("...");
        displayStyle.setButtonRegion(inputBt);
        if (selection == 0) {
            this.inputFile = new Text("请选择要压缩的文件");
            this.outputPath = new Text("请选择输出目录");
            startBt.setOnAction(event -> {
                try {
                    CompressTrim compressTrim = new CompressTrim(this.inputField.getText(),
                            this.outputField.getText());
                    if (compressTrim.getState() == 1) {
                        main.close();
                        begin.show();
                        new Message("压缩完成");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            inputBt.setOnAction(this::handleInput);
        } else if (selection == 1) {
            this.inputFile = new Text("请选择要解压的文件");
            this.outputPath = new Text("请选择输出目录");
            startBt.setOnAction(event -> {
                try {
                    DeCompressTrim compressTrim = new DeCompressTrim(this.inputField.getText(),
                            this.outputField.getText());
                    if (compressTrim.getState() == 1) {
                        main.close();
                        begin.show();
                        new Message("解压完成");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            inputBt.setOnAction(this::handleInput);
        }
        HBox first = new HBox();
        HBox second = new HBox();
        HBox third = new HBox();
        inputField.setPrefSize(200, 20);
        outputField.setPrefSize(200, 20);
        first.getChildren().addAll(inputFile, inputField, inputBt);
        first.setAlignment(Pos.CENTER);
        first.setSpacing(30);
        second.getChildren().addAll(outputPath, outputField, outputBt);
        second.setAlignment(Pos.CENTER);
        second.setSpacing(30);
        third.getChildren().addAll(startBt, exitBt, backBt);
        third.setAlignment(Pos.CENTER);
        third.setSpacing(100);

        VBox all = new VBox();
        all.getChildren().addAll(first, second, third);
        all.setSpacing(100);
        all.setAlignment(Pos.CENTER);
        all.setPrefSize(600, 400);
        Pane pane=new Pane(all);
        pane.setStyle("-fx-background-image:url(image/sea.jpg)");
        Scene scene = new Scene(pane, 600, 400);
        main.setScene(scene);
        main.setTitle("雷压 Version1.0 Just for fun By Heart");
        main.show();
    }

    private void handleOutput(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser("C:/Users/leiwe/Desktop/Test Cases", FileSystemView.getFileSystemView());
        fileChooser.setFileView(new FileView() {
            public Icon getIcon(File f) {
                return fileChooser.getFileSystemView().getSystemIcon(f);
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int flag = fileChooser.showOpenDialog(fileChooser);
        if (flag == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            outputField.setText(file.getAbsolutePath());
        }
    }

    private void handleInput(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser("C:/Users/leiwe/Desktop/Test Cases", FileSystemView.getFileSystemView());
        fileChooser.setFileView(new FileView() {
            public Icon getIcon(File f) {
                return fileChooser.getFileSystemView().getSystemIcon(f);
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int flag = fileChooser.showOpenDialog(fileChooser);
        if (flag == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            inputField.setText(file.getAbsolutePath());
        }
    }
}
