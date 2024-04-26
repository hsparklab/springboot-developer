import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll //전체 테스트 실행 전 1회
    static void beforeAll(){
        System.out.println("@beforeAll");

    }
    @BeforeEach //테스트 시작하기전마다 실행
    public void beforeEach(){
        System.out.println("@beforeEach");

    }
    @Test
    public void test1(){
        System.out.println("@test1");
    }
    @Test
    public void test2(){
        System.out.println("@test2");
    }
    @Test
    public void test3(){
        System.out.println("@test3");
    }

    @AfterAll//전체 테스트 실행 후 1회
    static void afterAll(){
        System.out.println("@afterAll");
    }
    @AfterEach
    public void afterEach(){

        System.out.println("@afterEach");
    }
}
