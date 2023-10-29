package at.wg.bierserver.dataaccess;

import at.wg.bierserver.data.DataSeasionFinMinimal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SeasonsFinRepo extends MongoRepository<DataSeasionFinMinimal, String> {
    @Query("{number:'?0'}")
    DataSeasionFinMinimal findItembyNumber(int number);
}
