public class PangramChecker {

   public boolean isPangram(String input) {
      final String lowercaseInput = input.toLowerCase();
      return "abcdefghijklmnopqrstuvwxyz".codePoints()
         .allMatch(c -> lowercaseInput.indexOf(c) > -1);
   }
}
