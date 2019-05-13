import java.util.List;
import java.util.Optional;

class BuildTree {

    TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
        return helper(Records.from(records), null, null);
    }

    private TreeNode helper(final Records records, final TreeNode parent, final TreeNode root) throws InvalidRecordsException {
        // if there are no more records, return the root
        if (records.isEmpty()) {
            return root;
        }

        // current record to deal with
        final var current = records.head();

        // if this is the root, then call recursively with this as root
        if (current.isRoot()) {
            final var newRoot = current.toNode();
            return helper(records.tail(), newRoot, newRoot);
        }

        // if no root exists by now, then something is really wrong. Throw an exception
        if (root == null) {
            throw new InvalidRecordsException();
        }

        // if current is a child of parent, add it to the parent then call recursively with parent as parent
        if (current.getParentId() == parent.getNodeId()) {
            parent.addChild(current.toNode());
            return helper(records.tail(), parent, root);
        }

        // if current is a child of another parent, find that parent then call recursively with that parent as parent
        final var newParent = findParent(root, current);
        if (newParent.isEmpty()) {
            throw new InvalidRecordsException();
        }

        final var existingParent = newParent.get();
        existingParent.addChild(current.toNode());
        return helper(records.tail(), existingParent, root);
    }

    private Optional<TreeNode> findParent(final TreeNode parent, final Record child) {
        if (parent.getNodeId() == child.getParentId()) {
            return Optional.of(parent);
        }

        return parent.getChildren().stream()
                .filter(node -> findParent(node, child).isPresent())
                .findFirst();
    }
}
