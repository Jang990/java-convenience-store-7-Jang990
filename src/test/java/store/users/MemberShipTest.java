package store.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.money.Money;

import static org.junit.jupiter.api.Assertions.*;

class MemberShipTest {

    @DisplayName("멤버쉽으로 30% 할인된 금액을 확인할 수 있다.")
    @Test
    void test1() {
        MemberShip memberShip = new MemberShip();
        Money money = new Money(1000);

        assertEquals(memberShip.discount(money), new Money(700));
        assertEquals(memberShip.getRemainingDiscount(), new Money(8_000 - 300));
    }

}