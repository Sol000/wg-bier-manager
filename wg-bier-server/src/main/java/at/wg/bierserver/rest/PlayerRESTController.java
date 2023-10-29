package at.wg.bierserver.rest;

import at.wg.bierserver.data.DataPlayer;
import at.wg.bierserver.dataaccess.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/player")
@EnableMongoRepositories
public class PlayerRESTController {

    @Autowired
    private PlayerRepo playerRepo;

    @GetMapping
    public ResponseEntity<DataPlayer> getplayer(@RequestParam String id){
        try{
            DataPlayer player;
            if (playerRepo.findById(id).isPresent()){
                player = playerRepo.findById(id).get();
            }
            else {
                throw new NoSuchElementException();
            }
            return new ResponseEntity<>(player, HttpStatus.OK);

        } catch (NoSuchElementException ex)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public HttpStatusCode deleteplayer(@RequestParam String id){
        playerRepo.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping("/new")
    public HttpStatusCode createnewplayer(@RequestBody DataPlayer newPlayer){
        DataPlayer temp = new DataPlayer(newPlayer.getPhotoUrl(), newPlayer.getNickname(), false,0,0,0);
        playerRepo.save(temp);
        return HttpStatus.OK;
    }

    @PostMapping("/update")
    public HttpStatusCode updateplayer(@RequestParam String id, @RequestBody DataPlayer updatePlayer){
        try{
            DataPlayer player;
            if (playerRepo.findById(id).isPresent()){
                player = playerRepo.findById(id).get();
            }
            else {
                throw new NoSuchElementException();
            }
            player.setPhotoUrl(updatePlayer.getPhotoUrl());
            player.setNickname(updatePlayer.getNickname());
            player.setRoyal(updatePlayer.isRoyal());
            player.setCrownCount(updatePlayer.getCrownCount());
            player.setSeasonBeerCount(updatePlayer.getSeasonBeerCount());
            player.setOverallBeerCount(updatePlayer.getOverallBeerCount());
            playerRepo.save(player);
            return  HttpStatus.OK;
        }
        catch (NoSuchElementException ex){
            return HttpStatus.NOT_FOUND;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DataPlayer>> getallplayer(){
        try{
            List<DataPlayer> listAllPlayer = playerRepo.findAll();
            if (listAllPlayer.isEmpty())
                throw new NoSuchElementException();
            return new ResponseEntity<>(listAllPlayer, HttpStatus.OK);

        }catch (NoSuchElementException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count-up")
    public HttpStatusCode countOneUp(@RequestParam String id){
        try {
            DataPlayer player;
            if (playerRepo.findById(id).isPresent()) {
                player = playerRepo.findById(id).get();
            }
            else {
                throw new NoSuchElementException();
            }
            player.setSeasonBeerCount(player.getSeasonBeerCount()+1);
            playerRepo.save(player);
            return HttpStatus.OK;
        }catch (NoSuchElementException ex){
            return HttpStatus.NOT_FOUND;
        }
    }


    @GetMapping("/count-down")
    public HttpStatusCode countOneDown(@RequestParam String id){
        try {
            DataPlayer player;
            if (playerRepo.findById(id).isPresent()) {
                player = playerRepo.findById(id).get();
            }
            else {
                throw new NoSuchElementException();
            }
            player.setSeasonBeerCount(player.getSeasonBeerCount()-1);
            playerRepo.save(player);
            return HttpStatus.OK;
        }catch (NoSuchElementException ex){
            return HttpStatus.NOT_FOUND;
        }
    }
}
