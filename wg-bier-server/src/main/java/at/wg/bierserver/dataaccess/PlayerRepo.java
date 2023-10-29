package at.wg.bierserver.dataaccess;

import at.wg.bierserver.data.DataPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlayerRepo extends MongoRepository<DataPlayer, String> {

    @Query(value="{'nickname':'?0'}", fields = "{'nickname': }")
    DataPlayer findItembyNickname(String nickname);
}
