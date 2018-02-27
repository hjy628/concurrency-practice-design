package chap6;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 18-2-23.
 */
public class ConstrMethodRef {

    @FunctionalInterface
    interface UserFactory<U extends User>{
        U create(int id,String name);
    }

    static UserFactory<User> uf = User::new;

    public static void main(String[] args) {

        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            users.add(uf.create(i,"billy"+Integer.toString(i)));
        }

        users.stream().map(User::getName).forEach(System.out::println);


    }



}
