package store.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.money.Money;
import store.product.OrderLine;
import store.product.Quantity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    @DisplayName("최종 결과 출력")
    @Test
    void test1() {
        MemberShip memberShip = new MemberShip();
        List<OrderLine> orderLines = List.of(
                new OrderLine("콜라", new Money(1000), Quantity.of(2), Quantity.of(1)),
                new OrderLine("에너지바", new Money(2000), Quantity.of(5), Quantity.of(0))
        );
        Receipt receipt = new Receipt("W편의점", orderLines, memberShip);
        System.out.println(receipt);
    }

}