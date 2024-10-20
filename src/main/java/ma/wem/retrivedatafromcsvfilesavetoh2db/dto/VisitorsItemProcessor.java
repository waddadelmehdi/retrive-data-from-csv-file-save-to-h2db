package ma.wem.retrivedatafromcsvfilesavetoh2db.dto;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


@Component
public class VisitorsItemProcessor implements ItemProcessor<Visitors, Visitors> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat();

    @Override
    public Visitors process(Visitors item) throws Exception {
        item.setVisitDate(dateFormat.parse(item.getStrVisitDate()));
        return item;
    }
}
