import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Flattener {

    public List<?> flatten(List<?> list) {
        return streamFlattener(list).collect(toList());
    }

    private Stream<?> streamFlattener(List<?> list) {
        if (list == null || list.isEmpty()) {
            return Stream.empty();
        }

        return list.stream()
                .flatMap(item -> {
                    if (item == null) {
                        return Stream.empty();
                    }
                    if (item instanceof List<?>) {
                        return streamFlattener((List<?>) item);
                    }
                    return Stream.of(item);
                });
    }
}
