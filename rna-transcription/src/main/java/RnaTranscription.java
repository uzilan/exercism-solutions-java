class RnaTranscription {

    String transcribe(String dnaStrand) {
        return dnaStrand.codePoints()
                .map(c -> {
                    switch (c) {
                        case 'C':
                            return 'G';
                        case 'G':
                            return 'C';
                        case 'T':
                            return 'A';
                        case 'A':
                            return 'U';
                        default:
                            return '?';
                    }

                })
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
