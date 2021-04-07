package ma.project.BankSpringBatch.processor;

import lombok.Getter;
import ma.project.BankSpringBatch.entity.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class TransactionProcessor implements ItemProcessor<Transaction,Transaction> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Getter
    private  Double totalDebit;

    @Getter
    private Double totalCredit;

    @Override
    public Transaction process(Transaction transaction) throws Exception {
        if("D".equals(transaction.getTransactionType()))
            totalDebit+= transaction.getAmount();
        else
            if("C".equals(transaction.getTransactionType()))
                totalCredit+= transaction.getAmount();

        transaction.setTransactionDate(LocalDateTime.parse(transaction.getStrTransactionDate(),formatter));

        return transaction;
    }
}
