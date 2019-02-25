import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.List;

class KindergartenGarden {

   private final List<List<Plant>> plants;

   KindergartenGarden(String garden) {
      plants = stream(garden.split("\n"))
         .map(this::parseRow)
         .collect(toList());
   }

   private List<Plant> parseRow(String row) {
      return row.codePoints()
         .mapToObj(c -> Plant.getPlant((char) c))
         .collect(toList());
   }

   List<Plant> getPlantsOfStudent(String student) {
      final int studentIndex = student.charAt(0) - 'A';
      return plants.stream()
         .flatMap(row -> row.subList(studentIndex * 2, studentIndex * 2 + 2).stream())
         .collect(toList());
   }
}
