public class Bob {

    String hey(String s) {
        final String trimmed = s.trim();

        if (trimmed.isEmpty()) {
            return "Fine. Be that way!";
        }

        final boolean isQuestion = isQuestion(trimmed);

        if (isUpperCase(trimmed)) {
            if (isQuestion) {
                return "Calm down, I know what I'm doing!";
            } else {
                return "Whoa, chill out!";
            }
        }
        if (isQuestion) {
            return "Sure.";
        }
        return "Whatever.";
    }

    private boolean isQuestion(String s) {
        return s.endsWith("?");
    }

    private boolean isUpperCase(String s) {
        final String onlyLetters = s.replaceAll("[^a-zA-Z]", "");
        return onlyLetters.matches("[A-Z]+");
    }
}
