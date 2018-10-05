package travel.api.external.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by we on 2017. 2. 27..
 */
public class IdGenerator {
    private static Random random = new Random();

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
