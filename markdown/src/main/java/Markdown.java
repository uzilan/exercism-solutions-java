class Markdown {

    String parse(String markdown) {

        final StringBuilder result = new StringBuilder();

        boolean activeList = false;

        for (final String line : markdown.split("\n")) {

            Line theLine = parseHeader(line);

            if (theLine.lineType == LineType.MISSING) {
                theLine = parseListItem(line);
            }

            if (theLine.lineType == LineType.MISSING) {
                theLine = parseParagraph(line);
            }

            if (theLine.lineType == LineType.LIST_ITEM && !activeList) {
                activeList = true;
                result.append("<ul>");
                result.append(theLine.value);
            } else if (activeList) {
                activeList = false;
                result.append(theLine.value);
                result.append("</ul>");
            } else {
                result.append(theLine.value);
            }

        }
        return result.toString();
    }

    private Line parseHeader(String markdown) {
        int count = (int) markdown.codePoints().takeWhile(cp -> cp == '#').count();

        if (count == 0) {
            return Line.MISSING;
        }

        return new Line(
                "<h" + count + ">" + markdown.substring(count + 1) + "</h" + count + ">",
                LineType.HEADER);
    }

    private Line parseListItem(String markdown) {
        if (!markdown.startsWith("*")) {
            return Line.MISSING;
        }

        final String skipAsterisk = markdown.substring(2);
        final String listItemString = parseSomeSymbols(skipAsterisk);
        return new Line(
                "<li>" + listItemString + "</li>",
                LineType.LIST_ITEM);

    }

    private Line parseParagraph(String markdown) {
        return new Line(
                "<p>" + parseSomeSymbols(markdown) + "</p>",
                LineType.PARAGRAPH);
    }

    private String parseSomeSymbols(String markdown) {
        final String strongRegex = "__(.+)__";
        final String strongReplacement = "<strong>$1</strong>";
        final String workingOn = markdown.replaceAll(strongRegex, strongReplacement);

        final String emRegex = "_(.+)_";
        final String emReplacement = "<em>$1</em>";
        return workingOn.replaceAll(emRegex, emReplacement);
    }

    private static class Line {

        private final String value;
        private final LineType lineType;

        private static Line MISSING = new Line("", LineType.MISSING);

        private Line(final String value, final LineType lineType) {
            this.value = value;
            this.lineType = lineType;
        }
    }

    private enum LineType {HEADER, LIST_ITEM, PARAGRAPH, MISSING}
}