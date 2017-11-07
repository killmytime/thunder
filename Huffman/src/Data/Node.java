package Data;

public class Node {
    private Node left;
    private Node right;
    private int code;

    Node(Node left, Node right) {
            this.left = left;
            this.right = right;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getLeft() {
        return left;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public Node getRight() {
        return right;
    }
    public boolean isLeaf(Node left){
        return left==null;
    }
    Node(int code){
        if (code>=0){
            this.code=code;
        }
    }
    public void setCode(int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
}
