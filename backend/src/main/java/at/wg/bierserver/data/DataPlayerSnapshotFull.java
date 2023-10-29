// this class gets returned to the frontend

package at.wg.bierserver.data;

import lombok.Data;

@Data
public class DataPlayerSnapshotFull{
    DataPlayer player;
    int seasionBeerCount;
    public DataPlayerSnapshotFull(DataPlayer player , int seasionBeerCount) {
        this.seasionBeerCount = seasionBeerCount;
        this.player = player;
    }
}
