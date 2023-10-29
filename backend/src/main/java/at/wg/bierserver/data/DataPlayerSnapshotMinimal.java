// this class will be stored in db
package at.wg.bierserver.data;

import lombok.Data;

@Data
public class DataPlayerSnapshotMinimal{
    String playerId;
    int seasonBeerCount;

    public DataPlayerSnapshotMinimal(String playerId , int seasonBeerCount) {
        this.seasonBeerCount = seasonBeerCount;
        this.playerId = playerId;
    }
}
