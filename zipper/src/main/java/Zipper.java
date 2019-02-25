import java.util.Objects;

public class Zipper {

   public Zipper left;
   public Zipper right;
   public Zipper up;
   private int value;

   @Override
   public String toString() {
      return new StringBuilder("value: ").append(value).append(", ")
         .append("left: ").append(left == null ? "null, " : "{ " + left.toString() + " }, ")
         .append("right: ").append(right == null ? "null" : "{ " + right.toString() + " }")
         .toString();

   }

   public Zipper(int value) {
      this.value = value;
   }

   public int getValue() {
      return value;
   }

   public void setValue(int value) {
      this.value = value;
   }

   public void setLeft(Zipper zipper) {
      left = zipper;
      if (zipper != null) {
         left.up = this;
      }

   }

   public void setRight(Zipper zipper) {
      right = zipper;
      if (zipper != null) {
         right.up = this;
      }
   }

   public BinaryTree toTree() {
      if (up == null) {
         return new BinaryTree(this);
      }
      return up.toTree();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      final Zipper zipper = (Zipper) o;
      return value == zipper.value &&
         Objects.equals(left, zipper.left) &&
         Objects.equals(right, zipper.right) &&
         Objects.equals(up, zipper.up);
   }

   @Override
   public int hashCode() {
      return Objects.hash(left, right, up, value);
   }
}
