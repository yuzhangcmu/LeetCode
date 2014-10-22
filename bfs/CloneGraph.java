package Algorithms.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = 
                new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        Queue<UndirectedGraphNode> qCopy = new LinkedList<UndirectedGraphNode>();

        q.offer(node);
        UndirectedGraphNode rootCopy = new UndirectedGraphNode(node.label);
        qCopy.offer(rootCopy);

        // BFS the graph.
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            UndirectedGraphNode curCopy = qCopy.poll();

            // bfs all the childern node.
            for (UndirectedGraphNode child : cur.neighbors) {
                // the node has already been copied. Just connect it and don't
                // need to copy.
                if (map.containsKey(child)) {
                    curCopy.neighbors.add(map.get(child));
                    continue;
                }

                // put all the children into the queue.
                q.offer(child);

                // create a new child and add it to the parent.
                UndirectedGraphNode childCopy = new UndirectedGraphNode(
                        child.label);

                curCopy.neighbors.add(childCopy);
                qCopy.offer(childCopy);

                map.put(child, childCopy);
            }
        }

        return rootCopy;
    }
}
