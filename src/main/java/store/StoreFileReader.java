package store;

import file.HeaderFileData;
import file.HeaderFileReader;
import product.Promotion;
import product.PromotionDuration;
import product.PromotionType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StoreFileReader {
    private static final String FILE_PATH = "src/main/resources/";
    private static final String PROMOTION_FILE_NAME = "promotions.md";
    private static final String PRODUCT_FILE_NAME = "products.md";

    private static final String PROMOTION_NAME_HEADER = "name";
    private static final String PROMOTION_REQUIRED_HEADER = "buy";
    private static final String PROMOTION_FREE_HEADER = "get";
    private static final String PROMOTION_DURATION_START_HEADER = "start_date";
    private static final String PROMOTION_DURATION_END_HEADER = "end_date";
    private static final int DUPLICATED_PROMOTION_SIZE = 2;
    private static final String DUPLICATED_PROMOTION_NAME_ERROR_MESSAGE = "중복된 프로모션 이름이 존재한다.";


    private final HeaderFileReader fileReader;

    public StoreFileReader(HeaderFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public List<Promotion> readPromotions() {
        HeaderFileData fileData = fileReader.read(getPromotionPath());
        Set<String> promotionNames = findPromotionNames(fileData);
        List<Promotion> result = new LinkedList<>();
        for (String promotionName : promotionNames) {
            Map<String,String> promotionData = findUniquePromotionData(fileData, promotionName);
            result.add(create(promotionData));
        }
        return result.stream().toList();
    }

    private Set<String> findPromotionNames(HeaderFileData fileData) {
        return fileData.findValues(PROMOTION_NAME_HEADER);
    }

    private Map<String, String> findUniquePromotionData(HeaderFileData fileData, String promotionName) {
        List<Map<String, String>> result = fileData.findElementsWithHeader(PROMOTION_NAME_HEADER, promotionName);
        if(result.size() >= DUPLICATED_PROMOTION_SIZE)
            throw new IllegalArgumentException(DUPLICATED_PROMOTION_NAME_ERROR_MESSAGE);
        return result.getFirst();
    }

    private Promotion create(Map<String, String> promotionData) {
        return new Promotion(
                toPromotionType(promotionData),
                toPromotionDuration(promotionData),
                promotionData.get(PROMOTION_NAME_HEADER)
        );
    }

    private PromotionDuration toPromotionDuration(Map<String, String> promotionData) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(promotionData.get(PROMOTION_DURATION_START_HEADER), dateFormatter);
        LocalDate end = LocalDate.parse(promotionData.get(PROMOTION_DURATION_END_HEADER), dateFormatter);
        return new PromotionDuration(start, end);
    }

    private PromotionType toPromotionType(Map<String, String> promotionData) {
        try {
            int required = Integer.parseInt(promotionData.get(PROMOTION_REQUIRED_HEADER));
            int free = Integer.parseInt(promotionData.get(PROMOTION_FREE_HEADER));
            return PromotionType.find(required, free);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변한할 수 없는 프모로션 타입 정보입니다", e);
        }
    }


    private String getPromotionPath() {
        return FILE_PATH.concat(PROMOTION_FILE_NAME);
    }

}
