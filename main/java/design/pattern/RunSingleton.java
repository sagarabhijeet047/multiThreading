package design.pattern;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class EagerSingleton {

    public static EagerSingleton obj = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return obj;
    }
}

class LazySingleton {
    public static volatile LazySingleton obj;

    private LazySingleton() {
        System.out.println("Lazy Instance Created");
    }
/*    public static synchronized LazySingleton getInstance(){ // Not Appreciated locking is happening in complete block
        if(obj == null){
            obj = new LazySingleton();
        }
        return obj;
    }*/

    public static LazySingleton getInstance() { //Double-Checked Locking
        if (obj == null) {
            synchronized (LazySingleton.class) {
                if (obj == null) {
                    obj = new LazySingleton();
                }
            }
        }
        return obj;
    }
}

class BillPughSingleton{
    private BillPughSingleton(){
        System.out.println("From Billy Irish");
    }
    private static class Holder{
        private static final BillPughSingleton bill = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance(){
        return Holder.bill;
    }
}



public class RunSingleton {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EagerSingleton eSing = EagerSingleton.getInstance();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<LazySingleton> oneTask = service.submit(LazySingleton::getInstance);
        Future<LazySingleton> secondTask = service.submit(LazySingleton::getInstance);

        LazySingleton obj1 = oneTask.get();
        LazySingleton obj2 = secondTask.get();

        BillPughSingleton obj = BillPughSingleton.getInstance();
    }
}
