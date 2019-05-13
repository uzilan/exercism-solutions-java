import static java.lang.String.format;

class Record {
    private int recordId;
    private int parentId;

    public Record(int recordId, int parentId) {
        this.recordId = recordId;
        this.parentId = parentId;
    }

    int getParentId() {
        return parentId;
    }

    int getRecordId() {
        return recordId;
    }

    TreeNode toNode() {
        return new TreeNode(recordId);
    }

    boolean isRoot() {
        return recordId == parentId;
    }

    @Override
    public String toString() {
        return format("Record [recordId:%d, parentId:%d]", recordId, parentId);
    }
}
