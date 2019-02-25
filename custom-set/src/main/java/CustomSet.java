import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomSet<T> {

   private Set<T> data;

   public CustomSet(Collection<T> collection) {
      data = new HashSet<>(collection);
   }

   public boolean isEmpty() {
      return data.isEmpty();
   }

   public boolean contains(T item) {
      return data.contains(item);
   }

   public boolean isSubset(CustomSet<T> other) {
      return data.containsAll(other.data);
   }

   public boolean isDisjoint(CustomSet<T> other) {
      return data.stream().noneMatch(other::contains) &&
         other.data.stream().noneMatch(this::contains);
   }

   public void add(T element) {
      data.add(element);
   }

   public CustomSet<T> getIntersection(CustomSet<T> other) {
      final Set<T> newSet = new HashSet<>(data);
      newSet.retainAll(other.data);
      return new CustomSet<>(newSet);
   }

   public CustomSet<T> getDifference(CustomSet<T> other) {
      final Set<T> newSet = new HashSet<>(data);
      newSet.removeAll(other.data);
      return new CustomSet<>(newSet);
   }

   public CustomSet<T> getUnion(CustomSet<T> other) {
      final Set<T> newSet = new HashSet<>(data);
      newSet.addAll(other.data);
      return new CustomSet<>(newSet);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      }
      if (other == null || getClass() != other.getClass()) {
         return false;
      }
      Collection<?> otherData = ((CustomSet<?>) other).data;
      return data.containsAll(otherData) && data.size() == otherData.size();
   }

   @Override
   public int hashCode() {
      return Objects.hash(data);
   }
}
