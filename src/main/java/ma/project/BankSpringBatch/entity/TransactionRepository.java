package ma.project.BankSpringBatch.entity;

import ma.project.BankSpringBatch.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
