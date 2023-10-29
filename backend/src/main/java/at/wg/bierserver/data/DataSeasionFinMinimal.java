// this class will be stored in db
package at.wg.bierserver.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("seasons-finished")
@Data
public class DataSeasionFinMinimal {
    @Id
    String id;
    int number;
    Date begin;
    Date end;
    List<DataPlayerSnapshotMinimal> result;
    String winner;
    double seasonValue;
    DataInvoice invoice;

    public DataSeasionFinMinimal(String id, int number, Date begin, Date end, List<DataPlayerSnapshotMinimal> result, String winner, double seasonValue, DataInvoice invoice) {
        this.id = id;
        this.number = number;
        this.begin = begin;
        this.end = end;
        this.result = result;
        this.winner = winner;
        this.seasonValue = seasonValue;
        this.invoice = invoice;
    }
}
