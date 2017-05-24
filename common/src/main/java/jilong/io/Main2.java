package jilong.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author jilong.qiu
 * @date 2017/5/12.
 */
public class Main2 {

    public static void main(String[] args) throws IOException {
        new Main2().run();
    }

    private void run() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8000));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            for (SelectionKey sk : selector.selectedKeys()) {
                execute((ServerSocketChannel) sk.channel());
            }
        }
    }

    private void execute(ServerSocketChannel channel) throws IOException {
        SocketChannel sc = channel.accept();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder sb = new StringBuilder();
    }

}
