package chap5.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hjy on 18-2-23.
 */
public class NIOEchoServer {

    private Selector selector;
    private ExecutorService pool = Executors.newCachedThreadPool();
    public static Map<Socket,Long> time_stat = new HashMap<Socket,Long>(10240);


    private void startServer() throws Exception{
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(),8000);
        //InetSocketAddress isaa = new InetSocketAddress(8000);

        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector,SelectionKey.OP_ACCEPT);

        for (;;){
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e = 0;
            while (i.hasNext()){
                SelectionKey sk = (SelectionKey)i.next();
                i.remove();

                if (sk.isAcceptable()){
                    doAccept(sk);
                }else if (sk.isValid()&&sk.isReadable()){
                    if (!time_stat.containsKey(((SocketChannel)sk.channel()).socket()))
                        time_stat.put(((SocketChannel)sk.channel()).socket(),System.currentTimeMillis());
                    doRead(sk);
                }else if (sk.isValid()&&sk.isWritable()){
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend: "+(e-b)+"ms");
                }
            }
        }
    }

    private void doAccept(SelectionKey sk){
        ServerSocketChannel server = (ServerSocketChannel)sk.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            SelectionKey clientKey = clientChannel.register(selector,SelectionKey.OP_READ);

            EchoClient echoClient = new EchoClient();

            clientKey.attach(echoClient);

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from "+clientAddress.getHostName()+".");

        }catch (Exception e){
            System.out.println("Failed to accept new client.");
            e.printStackTrace();
        }
    }


    class EchoClient{
        private LinkedList<ByteBuffer> outq;

        public EchoClient() {
            outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue(){
            return outq;
        }

        public void enqueue(ByteBuffer bb){
            outq.addFirst(bb);
        }
    }

    private void doRead(SelectionKey sk){
        SocketChannel channel = (SocketChannel)sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try {

        }catch (Exception e){
            System.out.println("Failed to read from client.");
            e.printStackTrace();
            //disconnect(sk)
            return;
        }
        bb.flip();
        pool.execute(new HandleMsg(sk,bb));
    }


    class HandleMsg implements Runnable{
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient)sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            //强迫selector立即返回
            selector.wakeup();
        }
    }

    private void doWrite(SelectionKey sk){
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient)sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();

        try {
            int len = channel.write(bb);
            if (len==-1){
                //disconnect(sk)
                return;
            }

            if (bb.remaining()==0){
                //the buffer was completely written,remove it.
                outq.removeLast();
            }
        }catch (Exception e){
            System.out.println("Failed to write to client.");
            e.printStackTrace();
            //disconnect(sk)
        }

        if (outq.size()==0){
            sk.interestOps(SelectionKey.OP_READ);
        }

    }



}
