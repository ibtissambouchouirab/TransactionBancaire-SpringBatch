package ma.project.BankSpringBatch.config;

import ma.project.BankSpringBatch.entity.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemReader<Transaction> itemReader;
    @Autowired
    private ItemProcessor<Transaction,Transaction> itemProcessor;
    @Autowired
    private ItemWriter<Transaction> itemWriter;

    @Bean
    public Job bankJob(){
        Step step =stepBuilderFactory.get("file-transaction")
                                      .<Transaction,Transaction>chunk(50)
                                      .reader(itemReader)
                                      .processor(itemProcessor)
                                      .writer(itemWriter).build();

        return jobBuilderFactory.get("transaction")
                                .incrementer(new RunIdIncrementer())
                                .start(step)
                                .build();
    }
    @Bean
    public FlatFileItemReader<Transaction> readFile(@Value("${inputFile}") Resource inputFile){
        FlatFileItemReader<Transaction> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("FILE");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(inputFile);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    private LineMapper<Transaction> lineMapper() {
        DefaultLineMapper<Transaction> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new  DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","accountID","StrTransactionDate","transactionType","amount");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper = new  BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(Transaction.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return  lineMapper;
    }

}
