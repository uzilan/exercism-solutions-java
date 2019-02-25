import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ProteinTranslator {

   List<String> translate(String rnaSequence) {
      final int codonCount = rnaSequence.length() / 3;

      final List<String> codons = IntStream.range(0, codonCount)
         .mapToObj(i -> rnaSequence.substring(i * 3, (i + 1) * 3))
         .collect(Collectors.toList());

      final Stream<Integer> stopCodonsIndexes =
         Stream.of(codons.indexOf("UAA"),
                   codons.indexOf("UAG"),
                   codons.indexOf("UGA"));

      final List<String> codonsUntilStop = stopCodonsIndexes
         .filter(i -> i > -1)
         .min(Comparator.comparing(Integer::valueOf))
         .map(s -> codons.subList(0, s))
         .orElse(codons);

      return codonsUntilStop.stream()
         .map(ProteinTranslator::translateCodon)
         .collect(Collectors.toList());
   }

   private static String translateCodon(String codon) {
      switch (codon) {
         case "AUG":
            return "Methionine";
         case "UUU":
         case "UUC":
            return "Phenylalanine";
         case "UUA":
         case "UUG":
            return "Leucine";
         case "UCU":
         case "UCC":
         case "UCA":
         case "UCG":
            return "Serine";
         case "UAU":
         case "UAC":
            return "Tyrosine";
         case "UGU":
         case "UGC":
            return "Cysteine";
         case "UGG":
            return "Tryptophan";
         default:
            return "";
      }
   }
}