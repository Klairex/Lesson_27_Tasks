import java.util.concurrent.*;


public class Main {
    private static int counter = 0;
    private synchronized static void incrementCounter() {
        counter++;
    }
    public static void main(String[] args) {
         int poolSize = 4;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);


        for (int i = 0; i < poolSize; i++) {
            executor.submit(() -> {
                while (true) {
                    incrementCounter();
                    try {

                        Thread.sleep(1000);
                        long threadId = Thread.currentThread().getId();

                        System.out.println("Thread ID: " + threadId + " | Counter: " + counter);

                    } catch (InterruptedException e) {
                        System.err.println("Firul de executie a fost intrerupt !");
                    }
                    finally {
                        if(executor !=null) executor.shutdown();
                    }
                }
            });
        }


    }


}
