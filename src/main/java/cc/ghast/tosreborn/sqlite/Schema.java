package cc.ghast.tosreborn.sqlite;

import cc.ghast.tosreborn.utils.BUtil;
import org.bukkit.entity.Player;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author Ghast
 * @since 05-Apr-20
 */
public class Schema {

    private static final String PLAYER_INSERT = "INSERT INTO %table% (`uuid`, `has_read`, `has_agreed`, `date`) VALUES (?,?,?,?)";

    public static ExecutableStatement insertPlayer(String table, UUID player){
        ExecutableStatement query = Query.prepare(PLAYER_INSERT.replace("%table%", table));
        query.append(player.toString())
                .append(false)
                .append(false)
                .append(BUtil.fromDate(Calendar.getInstance().getTime()));
        return query;
    }

    private static final String READ_PLAYER = "UPDATE %table% SET `has_read`=? WHERE `uuid`=?";

    public static ExecutableStatement updateRead(String table, UUID player, boolean value){
        ExecutableStatement query = Query.prepare(READ_PLAYER.replace("%table%", table));
        query.append(value).append(player.toString());
        return query;
    }

    private static final String AGREED_PLAYER = "UPDATE %table% SET `has_agreed`=? WHERE `uuid`=?";

    public static ExecutableStatement updateAgreed(String table, UUID player, boolean value){
        ExecutableStatement query = Query.prepare(AGREED_PLAYER.replace("%table%", table));
        query.append(value).append(player.toString());
        return query;
    }

    private static final String RESET_DATA = "DELETE FROM %table%";

    public static ExecutableStatement resetData(String table){
        return Query.prepare(RESET_DATA.replace("%table%", table));
    }

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS %table% (id INTEGER PRIMARY KEY AUTOINCREMENT, uuid VARCHAR(64), has_read BIT, has_agreed BIT, date VARCHAR(128))";


    public static ExecutableStatement createTable(String table){
        return Query.prepare(CREATE_TABLE.replace("%table%", table));
    }


    private static final String LIST_UUID = "SELECT * FROM %table% WHERE `uuid`=?";

    public static ExecutableStatement checkExist(String table, String uuid){
        return Query.prepare(LIST_UUID.replace("%table%", table)).append(uuid);
    }





}
