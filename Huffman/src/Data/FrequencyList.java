package Data;

import java.util.PriorityQueue;
import java.util.Queue;

public class FrequencyList {
    private int[] frequencyTable;

    public FrequencyList(int[] frequencyTable) {
        if (frequencyTable == null) {
            System.out.println("表不存在");
            throw new NullPointerException("表不存在");
        }
        this.frequencyTable = frequencyTable.clone();
    }

    public void increment(int block) {
        if (block <0 || block > frequencyTable.length) {
            throw new IllegalArgumentException();
        }
        frequencyTable[block]++;
    }

    public CodeTree buildCodeTree() {
        Queue<NodeFrequency> codeTree = new PriorityQueue<>();
        for (int i = 0; i < frequencyTable.length; i++) {
            if (frequencyTable[i] > 0) {
                codeTree.add(new NodeFrequency(new Node(i), i, frequencyTable[i]));
            }
        }
        for (int i = 0; i < frequencyTable.length && codeTree.size() < 2; i++) {
            if (i >= frequencyTable.length || frequencyTable[i] == 0) {
                codeTree.add(new NodeFrequency(new Node(i), i, 0));
            }
        }
        while (codeTree.size() > 1) {
            NodeFrequency least1 = codeTree.remove();
            NodeFrequency least2 = codeTree.remove();
            codeTree.add(new NodeFrequency(new Node(least1.getNode(), least2.getNode())
                    , Math.min(least1.getblock(), least2.getblock()),
                    least1.getFrequencies() + least2.getFrequencies()));
        }
        return new CodeTree(codeTree.remove().getNode(), frequencyTable.length);
    }


}
