package basic;

public class StringReaderStub extends StringReader {
    private String readLineValue;

    public void setReadLineValue(String readLineValue) {
        this.readLineValue = readLineValue;
    }

    @Override
    public String readLine() {
        return readLineValue;
    }
}
