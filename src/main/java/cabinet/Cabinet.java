package cabinet;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Cabinet {

    private static final List<String> CACHE = IntStream.rangeClosed(0, 108)
            .mapToObj(number -> "X")
            .collect(toList());

    private static final Set<Integer> UNRESERVED_NUMBERS = Set.of( 27, 54, 81, 108, 82, 83, 84, 85, 86, 87, 88, 89, 90,
            91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107);

    private final String name;

    private Cabinet(String name) {
        this.name = name;
    }

    public static List<String> allocate(String crews) throws IOException {
        CACHE.set(0, "조커");
        String[] numberAndNames = crews.split(",", -1);

        Path path = Paths.get("src/main/resources/crews_nickname.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        LinkedList<String> crewNames = new LinkedList<>(reader.lines().collect(toList()));

        for (String numberAndName : numberAndNames) {
            String[] split = numberAndName.split(":", -1);

            String number = split[0];
            String name = split[1];

            CACHE.set(Integer.parseInt(number), name);
            crewNames.remove(name);
        }

        Collections.shuffle(crewNames);

        for (int i = 0; i < CACHE.size(); i++) {
            if (UNRESERVED_NUMBERS.contains(i)) {
                continue;
            }

            if (!CACHE.get(i).equals("X")) {
                continue;
            }

            String crewName = crewNames.removeLast();
            CACHE.set(i, crewName);
        }

        return CACHE;
    }
}
