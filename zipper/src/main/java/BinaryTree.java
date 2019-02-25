import java.util.Objects;

public class BinaryTree {

   private final Zipper root;

   public BinaryTree(Zipper zipper) {
      root = zipper;
   }

   public BinaryTree(int i) {
      this(new Zipper(i));
   }

   public Zipper getRoot() {
      return root;
   }

   public String printTree() {
      return root.toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      final BinaryTree that = (BinaryTree) o;
      return Objects.equals(root, that.root);
   }

   @Override
   public int hashCode() {
      return Objects.hash(root);
   }
}
