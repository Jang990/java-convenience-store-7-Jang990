package store.file;

import java.util.List;
import java.util.Map;

public class HeaderFileReaderStub extends HeaderFileReader {
    private HeaderFileData readValue;

    public void setReadValue(List<Map<String, String>> value) {
        readValue = new HeaderFileData(value);
    }

    @Override
    public HeaderFileData read(String path) {
        return readValue;
    }
}