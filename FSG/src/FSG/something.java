package FSG;

import javafx.scene.shape.Circle;

import java.util.Stack;

/**
 * Created by leiwe on 2016/12/28.
 */
public interface something {
    Stack stacka = new Stack();//横坐标堆栈
    Stack stackb = new Stack();//纵坐标堆栈
    int VOID = 0;//棋盘空
    int BLACK = 1;//黑子
    int WHITE = 2;//白子
    int Width = 15;//棋盘宽度
    int Height = 15;//棋盘高度
    int Size = 50;//棋子直径
    int Distance = 100;//棋盘界面页边距
    Circle[] circles = new Circle[255];//棋子
    int[][] state = new int[Height][Width];//坐标
}
