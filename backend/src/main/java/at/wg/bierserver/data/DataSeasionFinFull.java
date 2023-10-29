// this class will be returned to the frontend
package at.wg.bierserver.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class DataSeasionFinFull {
    @Id
    String id;
    int number;
    Date begin;
    Date end;
    List<DataPlayerSnapshotFull> result;
    String winner;
    double seasonValue;
    DataInvoice invoice;

    public DataSeasionFinFull(String id, int number, Date begin, Date end, List<DataPlayerSnapshotFull> result, String winner, double seasonValue, DataInvoice invoice) {
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
