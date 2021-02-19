package cn.dark;

/**
 * @author dark
 * @date 2019-02-12
 */
public class CS {

    // 角色表示
    private static String[] players = {"T", "P"};
    // 武器类型
    private static String[] weapons = {"AK-47", "Knife", "Sniper"};

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            // 角色类型属于内部状态，是可共享的，武器类型属于外部状态，会变化的
            AbstractPlayer player = PlayerFactory.getPlayer(getPlayer(i));
            player.assignWeapon(getWeapon(i));

            player.execute();
        }
    }

    /**
     * 随机创建土匪或是警察
     */
    private static String getPlayer(int i) {
        return players[i % players.length];
    }

    /**
     * 随机获取武器
     */
    private static String getWeapon(int i) {
        return weapons[i % weapons.length];
    }

}
