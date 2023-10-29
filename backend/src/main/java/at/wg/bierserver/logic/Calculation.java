// should be a static class to calculate things like invoices etc...
package at.wg.bierserver.logic;

import at.wg.bierserver.data.DataPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculation {

    public static Map<String, Double> claculateCostsPerPlayer(List<DataPlayer> result, double tips, double seasonValue) {
        Map<String, Double> map = new HashMap<>();

        double seasonValueMinusTips = seasonValue - tips;
        double beerInSeasonCount = 0;

        // count all beers that are be drunken in this season
        for (DataPlayer snapshot : result)
            beerInSeasonCount += snapshot.getSeasonBeerCount();

        // calculate costs for every Player that was in this season
        for (DataPlayer snapshot : result)
            map.put(snapshot.getNickname(), seasonValueMinusTips * (snapshot.getSeasonBeerCount() / beerInSeasonCount));

        return map;
    }


    public static String getWinnerNickname(List<DataPlayer> playerList) {
        DataPlayer winner = null;
        int max = 0;
        for (DataPlayer player : playerList) {
            if (player.getSeasonBeerCount() >= max)
            {
                max = player.getSeasonBeerCount();
                winner = player;
            }
        }
        return winner.getNickname();
    }
}
