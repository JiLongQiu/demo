package jilong;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

/**
 * @author jilong.qiu
 * @date 2017/4/7.
 */
public class RecordTime {

    public static <T> void execute(Supplier<T> func) {
        long start = System.currentTimeMillis();
        func.get();
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Files.readAllLines(Paths.get("D:/1223.txt")));
    }

}
