package ma.project.BankSpringBatch.write;

import lombok.RequiredArgsConstructor;
import ma.project.BankSpringBatch.entity.Transaction;
import ma.project.BankSpringBatch.entity.TransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class TransactionItemWriter implements ItemWriter<Transaction> {


    private TransactionRepository transactionRepository;

    @Override
    public void write(List<? extends Transaction> list) throws Exception {
      //  transactionRepository.saveAll(list);
    }
}
