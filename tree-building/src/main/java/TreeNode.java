import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

class TreeNode {
    private int nodeId;
    private List<TreeNode> children;

    TreeNode(int nodeId) {
        this.nodeId = nodeId;
        this.children = new ArrayList<>();
    }

    int getNodeId() {
        return nodeId;
    }

    List<TreeNode> getChildren() {
        return children;
    }

    void addChild(TreeNode node) {
        children.add(node);
    }

    @Override
    public String toString() {
        final var childrenAsString = children.stream()
                .map(c -> Integer.toString(c.nodeId))
                .collect(joining(", "));
        return format("TreeNode [nodeId:%d, children:%s]", nodeId, childrenAsString);
    }
}
