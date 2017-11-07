package Data;

public class NodeFrequency implements Comparable<NodeFrequency> {
    private Node node;
    private int block;
    private long frequencies;

    NodeFrequency(Node node, int block, long frequencies) {
        this.node = node;
        this.block = block;
        this.frequencies = frequencies;
    }

    long getFrequencies() {
        return frequencies;
    }

    Node getNode() {
        return node;
    }

    int getblock() {
        return block;
    }

    public int compareTo(NodeFrequency another) {
        if (this.frequencies - another.getFrequencies() < 0) {
            return -1;
        } else if (this.frequencies - another.getFrequencies() > 0) {
            return 1;
        } else return 0;
    }
}