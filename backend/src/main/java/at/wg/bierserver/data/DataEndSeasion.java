package at.wg.bierserver.data;

import lombok.Data;

import java.util.List;

@Data
public class DataEndSeasion {
    private Double seasionValue;
    private Double tips;
    private List<String> nextSeasonPlayerList;

    public DataEndSeasion(List<String> nextSeasonPlayerList, Double sessionValue, double tips) {
        this.seasionValue = sessionValue;
        this.tips = tips;
        this.nextSeasonPlayerList = nextSeasonPlayerList;
    }
}
