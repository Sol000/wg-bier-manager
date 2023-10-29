package at.wg.bierserver.data;


import lombok.Data;

import java.util.Map;

@Data
public class DataInvoice {
        double tips;
        Map<String, Double> calcPerPlayer;

    public DataInvoice(double tips, Map<String, Double> calcPerPlayer) {
        this.tips = tips;
        this.calcPerPlayer = calcPerPlayer;
    }
}
