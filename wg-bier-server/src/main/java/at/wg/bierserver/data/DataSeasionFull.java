// this class will be forwarded to the frontend over api-request

package at.wg.bierserver.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("seasons")
@Data
public class DataSeasionFull {
    @Id
    String id;
    int number;
    Date begin;
    List<DataPlayer> playerList;

    public DataSeasionFull(String id, int number, Date begin, List<DataPlayer> playerList) {
        this.id = id;
        this.number = number;
        this.begin = begin;
        this.playerList = playerList;
    }
}

