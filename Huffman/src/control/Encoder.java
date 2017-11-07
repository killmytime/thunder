package control;

import Data.CodeTree;
import utils.BitsOutputStream;

import java.io.IOException;
import java.util.List;

class Encoder {
    private BitsOutputStream out;
    private CodeTree codeTree;
    Encoder(BitsOutputStream out){
        this.out=out;
    }

    void setCodeTree(CodeTree codeTree) {
        this.codeTree = codeTree;
    }
    //01,输出编码表
    void write(int block) throws IOException{
        List<Integer> bits=codeTree.getCode(block);
        for (int i:bits){
            out.write(i);
        }
    }
}
