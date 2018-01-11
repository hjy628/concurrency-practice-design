package chap2;

/**
 * Created by hjy on 18-1-11.
 * 线程的所有状态  Thread.State
 */
public enum ThreadState {
    NEW,
    RUNNABLE,
    BLOCKED,
    WAITING,
    TIMED_WAITING,
    TERMINATED
}
