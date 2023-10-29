package at.wg.bierserver.dataaccess;

import at.wg.bierserver.data.DataSeasionFull;
import at.wg.bierserver.data.DataSeasionMinimal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SeasonRepo extends MongoRepository<DataSeasionMinimal, String> {
    @Query("{number:'?0'}")
    DataSeasionFull findItembyNumber(int number);
}
