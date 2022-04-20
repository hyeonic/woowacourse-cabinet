package cabinet;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CabinetTest {

    @Test
    void allocate() throws IOException {
        List<String> result = Cabinet.allocate("15:매트,17:릭,20:엘리");

        assertThat(result).hasSize(109);
        assertThat(result.get(15)).isEqualTo("매트");
        assertThat(result.get(17)).isEqualTo("릭");
        assertThat(result.get(20)).isEqualTo("엘리");
        assertThat(result.get(27)).isEqualTo("X");
        assertThat(result.get(54)).isEqualTo("X");
        assertThat(result.get(81)).isEqualTo("X");
        for (int i = 82; i < 109; i++) {
            assertThat(result.get(i)).isEqualTo("X");
        }
        assertThat(result.get(0)).contains("조커");
        showResult(result);
    }

    private void showResult(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + " " + result.get(i));
        }
    }
}
