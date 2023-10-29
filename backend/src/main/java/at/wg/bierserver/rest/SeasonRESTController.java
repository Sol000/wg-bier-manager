// here we handle the request mappings
package at.wg.bierserver.rest;
import at.wg.bierserver.data.*;
import at.wg.bierserver.dataaccess.PlayerRepo;
import at.wg.bierserver.dataaccess.SeasonRepo;
import at.wg.bierserver.dataaccess.SeasonsFinRepo;
import at.wg.bierserver.logic.Calculation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/season")
public class SeasonRESTController {
    // db acces vars

    private final SeasonRepo seasonRepo;

    private final SeasonsFinRepo seasonFinRepo;

    private final PlayerRepo playerRepo;

    public SeasonRESTController(SeasonRepo seasonRepo, SeasonsFinRepo seasonFinRepo, PlayerRepo playerRepo) {
        this.seasonRepo = seasonRepo;
        this.seasonFinRepo = seasonFinRepo;
        this.playerRepo = playerRepo;
    }

    @GetMapping
    public ResponseEntity<DataSeasionFull> getgurrentseason(){
        try{
            DataSeasionMinimal seasonMin = seasonRepo.findAll().getFirst();

            // get player list
            List<DataPlayer> playerList = new ArrayList<>();
            seasonMin.getPlayerIdList().forEach(item->{
                if(playerRepo.findById(item).isPresent()){
                    playerList.add(playerRepo.findById(item).get());
                }
                else{
                    throw new NoSuchElementException();
                }
            });

            // create new Item
            DataSeasionFull currentSeason = new DataSeasionFull( seasonMin.getId(), seasonMin.getNumber(), seasonMin.getBegin(), playerList);
            return new ResponseEntity<>(currentSeason, HttpStatus.OK);
        }
        catch (NoSuchElementException ex)
        {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); // happens if no running season was found!!
        }
    }


    // after ending a season the next season begins immediately !!!
    @PostMapping("/start")
    public HttpStatusCode newseason(@RequestBody DataEndSeasion data) {
        List<DataSeasionMinimal> list = seasonRepo.findAll();
        try {
            // ---------- prepare new season -----------------
            // calculate new number
            DataSeasionMinimal current = list.getFirst();
            DataSeasionMinimal newSeason = new DataSeasionMinimal(current.getNumber() + 1, new Date(), data.getNextSeasonPlayerList());

            // get current playerlist
            List<DataPlayer> playerList = new ArrayList<>();
            current.getPlayerIdList().forEach(item -> {
                DataPlayer player;
                if (playerRepo.findById(item).isPresent()){
                    player = playerRepo.findById(item).get();
                }
                else{
                    throw new NoSuchElementException();
                }
                playerList.add(player);
            });

            // ------------ prepare finished season -----------------------
            // calculate Winner
            String winner = Calculation.getWinnerNickname(playerList);

            // store current state of season in a snapshot
            List<DataPlayerSnapshotMinimal> result = new ArrayList<>();
            current.getPlayerIdList().forEach(item -> {
                DataPlayer player;
                if (playerRepo.findById(item).isPresent()){
                    player = playerRepo.findById(item).get();
                }
                else{
                    throw new NoSuchElementException();
                }
                String id = player.getId();
                result.add(new DataPlayerSnapshotMinimal(id, player.getSeasonBeerCount()));
            });

            // calculate invoice
            Map<String, Double> calcPerPlayer = Calculation.claculateCostsPerPlayer(playerList, data.getTips(), data.getSeasionValue());
            DataInvoice invoice = new DataInvoice(data.getTips(), calcPerPlayer);
            DataSeasionFinMinimal seasonFin = new DataSeasionFinMinimal(current.getId(), current.getNumber(), current.getBegin(), new Date(), result, winner, data.getSeasionValue(), invoice);

            // -------- update player ------------
            playerList.forEach(item -> {
                item.setPayedOverall(item.getPayedOverall() + calcPerPlayer.get(item.getNickname()));
                item.setOverallBeerCount(item.getOverallBeerCount() + item.getSeasonBeerCount());
                item.setSeasonBeerCount(0);
            });
            playerRepo.saveAll(playerList);

            // update crowncount
            playerList.forEach(item -> {
                if (item.getNickname().equals(winner)) {
                    item.setCrownCount(item.getCrownCount() + 1);
                }
            });

            // --------------- store new AND finished season --------------------
            seasonRepo.deleteAll();
            seasonRepo.insert(newSeason);
            seasonFinRepo.insert(seasonFin);


            return HttpStatus.OK;
        } catch (NoSuchElementException ex) {
            // prepare Initial Season
            DataSeasionMinimal newSeason = new DataSeasionMinimal(1, new Date(), data.getNextSeasonPlayerList());

            // save initial season
            seasonRepo.insert(newSeason);
            return HttpStatus.OK;
        }
    }
    @GetMapping("/finished/all")
    public ResponseEntity<List<DataSeasionFinFull>> getallfinsessions(){
        try{
            // get all seasons as Minimal
            List<DataSeasionFinMinimal> listMinimal = seasonFinRepo.findAll();

            // create object to store the data in
            List<DataSeasionFinFull> listFull = new ArrayList<>();

            // for each item in list create the DataPlayerSnapshotFull Object
            listMinimal.forEach(item->{
                // create DataSeasonFullObject to store it in list
                DataSeasionFinFull temp = new DataSeasionFinFull(item.getId(), item.getNumber(), item.getBegin(), item.getEnd(), createListOfPlayerSnapshotFull(item), item.getWinner(), item.getSeasonValue(), item.getInvoice());
                listFull.add(temp);
            });
            return new ResponseEntity<>(listFull, HttpStatus.OK);
        }
        catch ( NoSuchElementException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // If seasion-finished is empty then this should return
        }
    }

    @GetMapping("/finished")
    public ResponseEntity<DataSeasionFinFull> getseasionbyid(@RequestParam String id){
        // same as above only looking for one item
        try{
            DataSeasionFinMinimal seasonMinimal;
            if(seasonFinRepo.findById(id).isPresent()){
                seasonMinimal = seasonFinRepo.findById(id).get();
            }
            else{
                throw new NoSuchElementException();
            }
            DataSeasionFinFull temp = new DataSeasionFinFull(seasonMinimal.getId(), seasonMinimal.getNumber(), seasonMinimal.getBegin(), seasonMinimal.getEnd(), createListOfPlayerSnapshotFull(seasonMinimal), seasonMinimal.getWinner(), seasonMinimal.getSeasonValue(), seasonMinimal.getInvoice());
            return new ResponseEntity<>(temp, HttpStatus.OK);

        }catch (NoSuchElementException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    private List<DataPlayerSnapshotFull> createListOfPlayerSnapshotFull(DataSeasionFinMinimal seasonMin){
        List<DataPlayerSnapshotFull> listOfPlayerSnapshotFull = new ArrayList<>();
        seasonMin.getResult().forEach(it->{
            DataPlayer player;
            if (playerRepo.findById(it.getPlayerId()).isPresent()) {
                player = playerRepo.findById(it.getPlayerId()).get();
            }
            else {
                throw new NoSuchElementException();
            }
            listOfPlayerSnapshotFull.add(new DataPlayerSnapshotFull(player, it.getSeasonBeerCount()));
        });
        return listOfPlayerSnapshotFull;
    }
    
}
