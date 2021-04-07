package ma.project.BankSpringBatch.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
public class TransactionController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);


    @RequestMapping("/StartJob")
    public BatchStatus load() throws Exception {
        Map<String, JobParameter> map = new HashMap<>();
        map.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(map);
        JobExecution jobExecution = jobLauncher.run(job,jobParameters);
        while (jobExecution.isRunning()){

            logger.info("....");
        }
        logger.info("fin");
        return jobExecution.getStatus();




    }
}
