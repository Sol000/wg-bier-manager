package at.wg.bierserver.data;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("player")
@Data
public class DataPlayer {
    @Id
    String id;
    String photoUrl;
    String nickname;
    boolean isRoyal;
    int crownCount;
    int seasonBeerCount;
    int overallBeerCount;
    double payedOverall;

    public DataPlayer(String photoUrl, String nickname, boolean isRoyal, int crownCount, int seasonBeerCount, int overallBeerCount) {
        this.id = UUID.randomUUID().toString();
        this.photoUrl = photoUrl;
        this.nickname = nickname;
        this.isRoyal = isRoyal;
        this.crownCount = crownCount;
        this.seasonBeerCount = seasonBeerCount;
        this.overallBeerCount = overallBeerCount;
        this.payedOverall = 0;
    }
}
