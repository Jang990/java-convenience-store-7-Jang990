package file;

import java.util.List;
import java.util.Map;

public class HeaderFileData {
    private final List<Map<String, String>> values;

    protected HeaderFileData(List<Map<String, String>> fileData) {
        this.values = fileData;
    }

    public List<Map<String,String>> findElementsWithHeader(String header, String value) {
        return values.stream()
                .filter(data -> data.get(header).equals(value))
                .map(Map::copyOf)
                .toList();
    }


    public List<String> findValues(String header) {
        return values.stream()
                .filter(data -> data.containsKey(header))
                .map(data -> data.get(header))
                .distinct()
                .toList();
    }

    public List<Map<String, String>> getElements() {
        return values.stream().map(Map::copyOf).toList();
    }
}
