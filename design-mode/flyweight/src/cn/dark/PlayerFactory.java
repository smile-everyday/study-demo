package cn.dark;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dark
 * @date 2019-02-12
 */
public class PlayerFactory {

    private static Map<String, AbstractPlayer> pool = new HashMap<>();

    public static AbstractPlayer getPlayer(String type) throws Exception {
        AbstractPlayer player = pool.get(type);
        if (player == null) {
            switch (type) {
                case "P":
                    System.out.println("Create police: ");
                    player = new Police();
                    break;
                case "T":
                    System.out.println("Create terrorist: ");
                    player = new Terrorist();
                    break;
                default:
                    throw new Exception("无此类型的玩家！");
            }
            pool.put(type, player);
        }
        return player;
    }

}
