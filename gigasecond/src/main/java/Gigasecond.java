import java.time.LocalDate;
import java.time.LocalDateTime;

class Gigasecond {

   private final LocalDateTime gigatime;

   Gigasecond(LocalDate birthDate) {
      this(birthDate.atStartOfDay());
   }

   Gigasecond(LocalDateTime birthDateTime) {
      this.gigatime = birthDateTime.plusSeconds((long) 1E9);
   }

   LocalDateTime getDate() {
      return gigatime;
   }
}
