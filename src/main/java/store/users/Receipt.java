package store.users;

import store.money.Money;
import store.product.OrderLine;
import store.product.Quantity;

import java.util.List;

public class Receipt {
    private final String storeId;
    private final List<OrderLine> orderLines;
    private final Money membershipDiscounted;

    public Receipt(String storeId, List<OrderLine> orderLines) {
        this.storeId = storeId;
        this.orderLines = orderLines;
        membershipDiscounted = Money.EMPTY;
    }

    public Receipt(String storeId, List<OrderLine> orderLines, MemberShip memberShip) {
        this.storeId = storeId;
        this.orderLines = orderLines;
        this.membershipDiscounted = memberShip.discount(getTotalPrice());
    }

    public Money getTotalPrice() {
        return sumMoney(orderLines.stream().map(OrderLine::getTotalPrice).toList());
    }

    public Money getPromotionDiscountedPrice() {
        return sumMoney(orderLines.stream().map(OrderLine::getFreePrice).toList());
    }

    public Money getActualTotalPrice() {
        return sumMoney(orderLines.stream().map(OrderLine::getActualPrice).toList()).minus(membershipDiscounted);
    }

    public Quantity getProductAmount() {
        return sumQuantity(orderLines.stream().map(OrderLine::getProductAmount).toList());
    }

    private Quantity sumQuantity(List<Quantity> list) {
        Quantity total = Quantity.EMPTY;
        for (Quantity quantity : list) {
            total = total.plus(quantity);
        }
        return total;
    }

    private Money sumMoney(List<Money> moneyList) {
        Money total = Money.EMPTY;
        for (Money money : moneyList) {
            total = total.plus(money);
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("============%s============".formatted(storeId)).append("\n");
        result.append("상품명").append("\t").append("수량").append("\t").append("금액").append("\n");
        for (OrderLine orderLine : orderLines) {
            result.append(orderLine.getProductName()).append("\t")
                    .append(orderLine.getProductAmount()).append("\t")
                    .append(orderLine.getTotalPrice()).append("\n");
        }


        result.append("============증정============").append("\n");
        for (OrderLine promotionOrderLine : getPromotionOrderLines()) {
            result.append(promotionOrderLine.getProductName()).append("\t")
                    .append(promotionOrderLine.getFreeProduct()).append("\n");
        }

        result.append("=============================").append("\n");
        result.append("총구매액\t").append(getProductAmount()).append("\t").append(getTotalPrice()).append("\n");
        result.append("행사할인\t").append(getPromotionDiscountedPrice()).append("\n");
        result.append("멤버쉽할인\t").append(membershipDiscounted).append("\n");
        result.append("내실돈\t").append(getActualTotalPrice()).append("\n");

        return result.toString();
    }

    private List<OrderLine> getPromotionOrderLines() {
        return orderLines.stream()
                .filter(orderLine -> !Quantity.isEmpty(orderLine.getFreeProduct()))
                .toList();
    }
}
