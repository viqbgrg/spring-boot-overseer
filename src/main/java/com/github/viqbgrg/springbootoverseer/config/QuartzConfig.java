package com.github.viqbgrg.springbootoverseer.config;

import com.github.viqbgrg.springbootoverseer.quartz.SampleJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(SampleJob.class)
                .storeDurably()
                .withIdentity("job1", "group1")
                .withDescription("Invoke Sample Job service...")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("trigger1", "group1")
                .withDescription("Sample trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(1))
                .build();
    }
}
