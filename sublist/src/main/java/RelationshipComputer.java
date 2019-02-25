import static java.util.stream.IntStream.rangeClosed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RelationshipComputer<T> {

   public Relationship computeRelationship(List<T> firstList, List<T> secondList) {
      if (Objects.equals(firstList, secondList)) {
         return Relationship.EQUAL;
      }
      if (sublist(firstList, secondList)) {
         return Relationship.SUBLIST;
      }
      if (superlist(firstList, secondList)) {
         return Relationship.SUPERLIST;
      }
      return Relationship.UNEQUAL;
   }

   private static <T> boolean sublist(List<T> firstList, List<T> secondList) {
      final int firstListSize = firstList.size();
      final int secondListSize = secondList.size();

      if (firstListSize > secondListSize) {
         return false;
      }

      return rangeClosed(0, secondListSize).anyMatch(i -> {
         if (i + firstListSize > secondListSize) {
            return false;
         }
         final List<T> subList = secondList.subList(i, i + firstListSize);
         return Objects.equals(firstList, subList);
      });
   }

   private static <T> boolean superlist(List<T> firstList, List<T> secondList) {
      return sublist(reverse(secondList), reverse(firstList));
   }

   private static <T> List<T> reverse(List<T> list) {
      final List<T> reversed = new ArrayList<>(list);
      Collections.reverse(reversed);
      return reversed;
   }

}
