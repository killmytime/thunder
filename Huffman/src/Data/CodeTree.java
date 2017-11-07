package Data;

import java.util.ArrayList;
import java.util.List;

public class CodeTree {
    private Node root;
    private List<List<Integer>> codes;//动态数组
    CodeTree(Node root,int blockNumber){
                if (root==null){
                    System.out.println("没有根节点");
                    throw new NullPointerException();
                }
                this.root=root;
                codes= new ArrayList<>();
                for (int i=0;i<blockNumber;i++){
                    codes.add(null);
                }
                    buildCodeTree(root, new ArrayList<>());

    }
    public Node getRoot() {
        return root;
    }
    //左0右1
    private void buildCodeTree(Node node, List<Integer> coding){
        //node是InternalNode
          if (!node.isLeaf(node.getLeft())){

              coding.add(0);
              buildCodeTree(node.getLeft(),coding);
              coding.remove(coding.size()-1);

              coding.add(1);
              buildCodeTree(node.getRight(),coding);
              coding.remove(coding.size()-1);
          }
          //node是叶子节点
          else if (node.isLeaf(node.getLeft())){
              codes.set(node.getCode(),new ArrayList<>(coding));
          }
    }
    public List<Integer> getCode(int block){
        return codes.get(block);
    }
}
