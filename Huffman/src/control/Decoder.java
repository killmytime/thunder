package control;

import Data.CodeTree;
import Data.Node;
import utils.BitsInputStream;

import java.io.IOException;

class Decoder {
    private BitsInputStream bitsInputStream;
    private CodeTree codeTree;

    //初始化
    Decoder(BitsInputStream bitsInputStream) throws IOException {
        if (bitsInputStream == null) {
            System.out.println("输入流为空");
            throw new IOException("输入流为空");
        }
        this.bitsInputStream = bitsInputStream;
    }

    void setCodeTree(CodeTree codeTree) {
        this.codeTree = codeTree;
    }

    //解码
    int read() throws IOException {
        if (codeTree == null) {
            System.out.println("编码表为空");
            throw new NullPointerException("编码表为空");
        }
        Node currentNode = codeTree.getRoot();
        while (true) {
            int current = bitsInputStream.ended();//如果输入流没有结束，便是当前read
            Node nextNode;
            if (current == 0) {
                nextNode = currentNode.getLeft();
            } else if (current == 1) {
                nextNode = currentNode.getRight();
            } else {
                return -1;//即current=-1的情况
            }
            if (nextNode.isLeaf(nextNode.getLeft())) {
                return nextNode.getCode();
            } else if (!nextNode.isLeaf(nextNode.getLeft())) {
                currentNode = nextNode;//写递归出现了问题干脆就循环写吧
            }
        }
    }
}
