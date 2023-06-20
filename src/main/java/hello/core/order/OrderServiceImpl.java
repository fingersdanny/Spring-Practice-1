package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{


    //불변하는 객체를 바꾸는 Method 조차 만들지 말아야한다.
    //처음부터 불변으로 해놔야지 아니면 나중에 수정하기 힘들어진다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository  memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // discountPolicy 가 알아서 할인 해주고 넘겨줌 만약에 할인율을 바꿀거면 할인만 고치면됨
        // 단일 책임 원칙이 잘 지켜진 예시(SRP)
        // OrderService는 주문 생성만 책임진다.
        Member member = memberRepository.findbyId(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
