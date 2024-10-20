package ma.wem.retrivedatafromcsvfilesavetoh2db.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import ma.wem.retrivedatafromcsvfilesavetoh2db.dto.Visitors;
import ma.wem.retrivedatafromcsvfilesavetoh2db.dto.VisitorsItemProcessor;
import ma.wem.retrivedatafromcsvfilesavetoh2db.repositories.VisitorsRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {
    @Autowired
    private VisitorsRepository visitorsRepository;
    @Autowired
    private Visitors visitors;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ItemReader<Visitors> visitorsItemReader;


    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Visitors visitors() {
        return new Visitors();
    }


    @Bean
    public Job importVistorsJob() {
        return new JobBuilder("importVistorsJob", jobRepository)
                .start(importVistorsStep(jobRepository, visitors, transactionManager))
                .build();
    }

    @Bean
    public Step importVistorsStep(JobRepository jobRepository, Visitors visitors, PlatformTransactionManager transactionManager) {
        return new StepBuilder("importVistorsStep", jobRepository)
                .<Visitors, Visitors>chunk(100, transactionManager)
                .reader(visitorsItemReader)
                .processor(itemProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemProcessor<Visitors, Visitors> itemProcessor() {
        return new VisitorsItemProcessor();
    }

    @Bean
    public ItemWriter<Visitors> writer() {
        return visitorsRepository::saveAll;
    }

    @Bean
    public FlatFileItemReader<Visitors> flatFileItemReader(@Value("${inputFile}") Resource inputFile){
        FlatFileItemReader<Visitors> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("DEVAL");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource((org.springframework.core.io.Resource) inputFile);
        flatFileItemReader.setLineMapper(linMappe());
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<Visitors> linMappe() {
        DefaultLineMapper<Visitors> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("id","firstName","lastName","emailAddress","phoneNumber","address","strVisitDate");
        lineTokenizer.setStrict(false); // Set strict property to false
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(Visitors.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;

    }
}
