package chap5.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * Created by hjy on 18-2-23.
 */
public class NIOClient {

    private Selector selector;

    public void init(String ip,int port)throws Exception{
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = SelectorProvider.provider().openSelector();
        channel.connect(new InetSocketAddress(ip,port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void working() throws IOException{
        while (true){
            if (!selector.isOpen())
                break;
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
                //连接事件发生
                if (key.isConnectable()){
                    connect(key);
                }else if (key.isReadable()){
                    //read(key);
                }
            }
        }
    }


    public void connect(SelectionKey key)throws IOException{
        SocketChannel channel = (SocketChannel)key.channel();
        //如果正在连接，则完成连接
        if (channel.isConnectionPending()){
            channel.finishConnect();
        }
        channel.configureBlocking(false);
        channel.write(ByteBuffer.wrap(new String("hello server!\r\n").getBytes()));
        channel.register(selector,SelectionKey.OP_READ);
    }

    public void read(SelectionKey key)throws IOException{
        SocketChannel channel = (SocketChannel) key.channel();
        //创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到消息:"+msg);
        channel.close();
        key.selector().close();
    }





}
