package hello.core.singleton;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 자기자신을 정적인 영역에 객체 인스턴스를 하나 만들어서 가지고 있게 된다. private이라 다른곳에서 접근 불가능하게 함
    // 주로 데이터베이스 연결에 쓰인다.

    // public에서 열어서 객체 인스턴스가 필요함면 이 정적인 메소드를 통해서 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 해서 새로운 객체 생성을 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
