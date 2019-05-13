import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Records {

    private List<Record> records;

    private Records(final List<Record> records) throws InvalidRecordsException {
        this.records = records.stream()
                .sorted(comparing(Record::getRecordId))
                .collect(toList());

        checkForNonContinuousRecords();
    }

    private void checkForNonContinuousRecords() throws InvalidRecordsException {
        final var nonContinuous = range(0, records.size() - 1)
                .anyMatch(i -> {
                    final var currentRecordId = records.get(i).getRecordId() + 1;
                    final var nextRecordId = records.get(i + 1).getRecordId();
                    return currentRecordId != nextRecordId;
                });

        if (nonContinuous) {
            throw new InvalidRecordsException();
        }
    }

    static Records from(final List<Record> records) throws InvalidRecordsException {
        return new Records(records);
    }

    Record head() {
        return records.get(0);
    }

    Records tail() throws InvalidRecordsException {
        return Records.from(records.subList(1, records.size()));
    }

    Record get(final int i) {
        return records.get(i);
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }
}
