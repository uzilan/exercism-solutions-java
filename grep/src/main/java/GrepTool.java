import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class GrepTool {

   public String grep(String text, List<String> flagList, List<String> fileNames) {
      final Flags flags = new Flags(fileNames.size() > 1, flagList);

      return fileNames.stream()
         .flatMap(file -> findInFile(text, file, flags))
         .collect(joining("\n"));
   }

   private Stream<String> findInFile(String text, String fileName, Flags flags) {
      try {
         final List<String> lines = readAllLines(getDefault().getPath(fileName));
         return range(0, lines.size())
            .filter(i -> matchLine(text, lines.get(i), flags))
            .mapToObj(i -> getLine(fileName, lines.get(i), i + 1, flags))
            .distinct();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return Stream.empty();
   }

   private boolean matchLine(String text, String line, Flags flags) {
      return flags.invert != lineContainsText(text, line, flags);
   }

   private boolean lineContainsText(String text, String line, Flags flags) {
      return flags.matchEntireLines ? matchWholeLine(text, line, flags) : matchPartOfLine(text, line, flags);
   }

   private boolean matchPartOfLine(String text, String line, Flags flags) {
      return flags.caseInsensitiv ? line.toLowerCase().contains(text.toLowerCase()) : line.contains(text);
   }

   private boolean matchWholeLine(String text, String line, Flags flags) {
      return flags.caseInsensitiv ? line.equalsIgnoreCase(text) : line.equals(text);
   }

   private String getLine(String fileName, String line, int lineNumber, Flags flags) {
      if (flags.containsOneMatchingLine) {
         return fileName;
      }

      StringBuilder sb = new StringBuilder();
      if (flags.addFileName) {
         sb.append(fileName).append(":");
      }
      if (flags.addRowNumber) {
         sb.append(lineNumber).append(":");
      }
      sb.append(line);

      return sb.toString();
   }

   class Flags {

      boolean addFileName;
      boolean addRowNumber;
      boolean containsOneMatchingLine;
      boolean caseInsensitiv;
      boolean invert;
      boolean matchEntireLines;

      private Flags(boolean addFileName, List<String> flagList) {
         this.addFileName = addFileName;
         addRowNumber = flagList.contains("-n");
         containsOneMatchingLine = flagList.contains("-l");
         caseInsensitiv = flagList.contains("-i");
         invert = flagList.contains("-v");
         matchEntireLines = flagList.contains("-x");
      }
   }
}
