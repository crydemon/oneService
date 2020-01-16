import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringC {

    public static void main(String[] args) {
        String s = "device:10,user:5,xx:5";
        String[] rows = s.split(",");
        String[] str = new String[3];
        for (int i = 0; i < rows.length; i++) {
            String[] tmp = rows[i].split(":");
            str[i] = tmp[1] + " " + tmp[0];
        }
        for (String s1 : str) {
            System.out.println(s1);
        }
        List<String> a = new ArrayList();
        a.addAll(Arrays.asList(str));
    }
}
