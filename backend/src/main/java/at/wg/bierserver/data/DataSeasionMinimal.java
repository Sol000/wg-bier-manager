//this class is stored in db
package at.wg.bierserver.data;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document("seasons")
@Data
public class DataSeasionMinimal {

    @Id
    String id;
    int number;
    Date begin;
    List<String> playerIdList;

    public DataSeasionMinimal(int number, Date begin, List<String> playerIdList) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.begin = begin;
        this.playerIdList = playerIdList;
    }
}
