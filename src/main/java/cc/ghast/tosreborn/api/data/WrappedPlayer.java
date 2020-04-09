package cc.ghast.tosreborn.api.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Ghast
 * @since 07-Apr-20
 */

@Getter
public class WrappedPlayer {
    private final String uuid;
    private boolean agreed, read;
    private final Date date;


    public WrappedPlayer(String uuid, boolean agreed, boolean read, Date date) {
        this.uuid = uuid;
        this.agreed = agreed;
        this.read = read;
        this.date = date;
    }

    public WrappedPlayer setAgreed(boolean agreed) {
        this.agreed = agreed;
        return this;
    }

    public WrappedPlayer setRead(boolean read) {
        this.read = read;
        return this;
    }
}
