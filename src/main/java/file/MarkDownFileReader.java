package file;

import java.util.List;
import java.util.Map;

public class MarkDownFileReader {

    public List<Map<String, String>> read(String path) {
        return List.of(
                Map.of(
                        "ID", "1",
                        "이름", "콜라"
                ),
                Map.of(
                        "ID", "2",
                        "이름", "컵라면"
                )
        );
    }
}
