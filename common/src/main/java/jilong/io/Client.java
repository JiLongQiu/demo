package jilong.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author jilong.qiu
 * @date 2017/5/12.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress(8000));
        sc.configureBlocking(false);
    }

}
