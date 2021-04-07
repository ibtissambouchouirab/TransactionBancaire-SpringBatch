package ma.project.BankSpringBatch.reader;

import ma.project.BankSpringBatch.entity.Transaction;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class TransactionItemReader implements ItemReader<Transaction> {



    @Override
    public Transaction read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
