package revision;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskManager {
    int threadcount;
    ExecutorService executorService;
    public TaskManager(int threadcount){
        this.threadcount=threadcount;
        this.executorService= Executors.newFixedThreadPool(threadcount);
    }
    public void addTask(Runnable runnable){
        executorService.submit(runnable);
    }
    private int getQueueSize(){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) (executorService);
        return executor.getQueue().size();
    }
    public void waitTillQueueIsEmpty(Runnable runnable){
        while(getQueueSize()>=threadcount){
            try{
                System.out.println("sleeping");
                Thread.currentThread().sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        addTask(runnable);
    }


}
