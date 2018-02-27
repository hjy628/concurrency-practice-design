package chap6;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 18-2-23.
 */
public class InstanceMethodRef {

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i,"billy"+Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);

    }


}
