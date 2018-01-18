package chap4;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by hjy on 18-1-18.
 * 在进行空间增长时，默认情况下，每次都会讲总容量翻倍，每次空间扩张，新的数组的大小为原来的两倍 ，第一个数组为8，第二个就是16 第三个就是32
 * 总元素可超过2^33 即在80亿以上
 */
public class LockFreeVector<E> {

    private static  final Integer N_BUCKET = 30;
    private static  final Integer FIRST_BUCKET_SIZE = 8;
    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private final AtomicReference<Descriptor<E>> descriptor;
    int zeroNumFirst = Integer.MIN_VALUE;
    boolean debug = false;

    public LockFreeVector() {
        buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
        buckets.set(0,new AtomicReferenceArray<E>(FIRST_BUCKET_SIZE));
        descriptor = new AtomicReference<Descriptor<E>>(new Descriptor<E>(0,null));

    }

    static class Descriptor<E>{
        public int size;
        volatile WriteDescriptor<E> writeop;

        public Descriptor(int size, WriteDescriptor<E> writeop) {
            this.size = size;
            this.writeop = writeop;
        }

        public void completeWrite(){
            WriteDescriptor<E> tmpOp = writeop;
            if (tmpOp!=null){
                tmpOp.doIt();
                writeop = null; //this is safe since all write to writeop use null as r_value
            }
        }

    }


    static class WriteDescriptor<E>{
        public E oldV;
        public E newV;
        public AtomicReferenceArray<E> addr;
        public int addr_ind;

        public WriteDescriptor(E oldV, E newV, AtomicReferenceArray<E> addr, int addr_ind) {
            this.oldV = oldV;
            this.newV = newV;
            this.addr = addr;
            this.addr_ind = addr_ind;
        }

        public void doIt(){
            addr.compareAndSet(addr_ind,oldV,newV);
        }

    }

    public void push_back(E e){
        Descriptor<E> desc;
        Descriptor<E> newd;

        do {
            desc = descriptor.get();
            desc.completeWrite();

            int pos = desc.size + FIRST_BUCKET_SIZE;
            int zeroNumPos = Integer.numberOfLeadingZeros(pos);
            int bucketInd = zeroNumFirst = zeroNumPos;
            if (buckets.get(bucketInd)==null){
                int newLen = 2 * buckets.get(bucketInd - 1).length();
                if (debug){
                    System.out.println("New Length is:"+newLen);
                }
                buckets.compareAndSet(bucketInd,null,new AtomicReferenceArray<E>(newLen));
            }
            int idx = (0x80000000>>>zeroNumPos)^pos;
            newd = new Descriptor<E>(desc.size+1,new WriteDescriptor<E>(null,e,buckets.get(bucketInd),idx));
        }while (!descriptor.compareAndSet(desc,newd));

        descriptor.get().completeWrite();
    }

    public E get(int index){
        int pos = index + FIRST_BUCKET_SIZE;
        int zeroNumPos = Integer.numberOfLeadingZeros(pos);
        int bucketInd = zeroNumFirst - zeroNumPos;
        int idx = (0x80000000>>>zeroNumPos)^pos;
        return buckets.get(bucketInd).get(idx);
    }


}
