package cabinet;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/crews_nickname.txt");

        BufferedReader reader = Files.newBufferedReader(path);

        List<String> crewNames = reader.lines()
                .collect(toList());


    }
}
