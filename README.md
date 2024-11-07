# java-convenience-store-precourse
구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

## 기능 구현 목록

스토어 파일 리더
- 스토어 파일 읽어오기
  - 🚩 헤더 정보와 데이터 요소의 길이가 같지 않을 경우 예외가 발생한다.
  - 🚩 빈 파일은 헤더 정보가 없기 떄문에 예외가 발생한다.

프로모션 기간
- 현재 시간이 프로모션 기간인지 확인할 수 있다.
  - 🚩 종료 시간이 시작 시간보다 느리다면 예외가 발생한다.
  
프로모션
- 프로모션 적용 여부 확인하기
- 프로모션 적용하기
- 프로모션 이름 확인하기

상품
- 상품 구매
  - 🚩 재고 부족 시 예외가 발생한다.
  - 프로모션 재고를 우선적으로 차감
  - 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다.
  - 프로모션 적용
    - 🚩 프로모션 적용이 가능하나 적게 구매할 시 예외가 발생한다.
    - 🚩 프로모션이 적용이 가능하나 프로모션 재고가 소진돼 있다면 예외가 발생한다.

상점
- 상점에서 판매중인 상품들을 알려준다.

멤버쉽 할인
  - 프로모션 미적용 금액의 30% 할인
  - 최대 한도(8,000원) 안에서 할인하는지

## 잠재적 이슈

스토어 파일 리더
- [ ] 파일을 읽을 때 파일에 헤더가 필요하다는 규칙을 명시적으로 알려줄 수 없을까?

## 개인적 목표
* 너무 많은 것을 한 번에 처리하지 말고 작은 단위로 처리하기.
* 시스템이 안정적이려면 어떤 테스트가 있어야 하는지 생각하고 작성한 뒤 구현하기.
* test-feat-refactor 커밋으로 TDD 사이클을 유지하기.
* 무분별한 Getter 사용 자제하기.
* 다른 개발자가 나의 코드를 사용할 때 오용 가능성을 염두하기.