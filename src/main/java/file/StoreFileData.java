package file;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StoreFileData {
    private final List<Map<String, String>> values;

    protected StoreFileData(List<Map<String, String>> fileData) {
        this.values = fileData;
    }

    public List<Map<String,String>> findElementsContainingEntry(String entryKey, String entryValue) {
        return values.stream()
                .filter(data -> data.get(entryKey).equals(entryValue))
                .map(Map::copyOf)
                .toList();
    }


    public Set<String> findValues(String key) {
        return values.stream()
                .filter(data -> data.containsKey(key))
                .map(data -> data.get(key))
                .collect(Collectors.toUnmodifiableSet());
    }

    public List<Map<String, String>> getElements() {
        return values.stream().map(Map::copyOf).toList();
    }
}
