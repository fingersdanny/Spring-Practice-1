package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 원래 ConcurrentHashMap 사용해야함.. 동시성 이슈가 발생할 수도 있음

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findbyId(Long memberId) {
        return store.get(memberId);
    }
}
