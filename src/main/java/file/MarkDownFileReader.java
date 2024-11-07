package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class MarkDownFileReader {
    private static final String HEADER_ELEMENT_SIZE_MISMATCH_MESSAGE = "헤더의 수와 요소의 수가 맞지 않습니다.";
    private static final String IOEXCEPTION_ERROR_MESSAGE = "IOException 발생";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "파일을 찾을 수 없음";
    private final String ELEMENT_DELIMITER_REGEX = Pattern.quote(",");

    public List<Map<String, String>> read(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> headers = readHeaders(br);
            return readElements(br, headers);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR_MESSAGE, e);
        } catch (IOException e) {
            throw new IllegalStateException(IOEXCEPTION_ERROR_MESSAGE,e);
        }
    }

    private List<Map<String, String>> readElements(BufferedReader br, List<String> headers) throws IOException {
        List<Map<String, String>> result = new LinkedList<>();
        while (true) {
            String elementsLine = br.readLine();
            if(isEndOfFile(elementsLine))
                break;
            result.add(toMap(elementsLine, headers));
        }
        return result;
    }

    private boolean isEndOfFile(String fileLine) {
        return fileLine == null;
    }

    private Map<String, String> toMap(String elementsLine, List<String> headers) {
        List<String> elements = Arrays.stream(elementsLine.split(ELEMENT_DELIMITER_REGEX)).toList();
        if(elements.size() != headers.size())
            throw new IllegalArgumentException(HEADER_ELEMENT_SIZE_MISMATCH_MESSAGE);

        return combineListToMap(headers.iterator(), elements.iterator());
    }

    private Map<String, String> combineListToMap(Iterator<String> keys, Iterator<String> values) {
        Map<String, String> result = new HashMap<>();
        while (keys.hasNext() && values.hasNext()) {
            result.put(keys.next(), values.next());
        }
        return result;
    }

    private List<String> readHeaders(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(ELEMENT_DELIMITER_REGEX))
                .toList();
    }
}
