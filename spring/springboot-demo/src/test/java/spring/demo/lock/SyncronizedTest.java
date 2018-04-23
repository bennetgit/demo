package spring.demo.lock;

/**
 * Created by wangfacheng on 2018-02-08.
 */
public class SyncronizedTest {

    public static void main(String[] args) {
//            synchronized ("1") {
//                "1".wait();
//            }

            Version st = new Version();
            st.ttt();
    }

    public static class Version{

        public static void ttt(){
            System.out.println("xxfsdf");
        }
    }
}
