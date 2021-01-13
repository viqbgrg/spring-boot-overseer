package com.github.viqbgrg.springbootoverseer.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

@Slf4j
public class SampleJob implements Job {
//    @Override
//    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//        log.debug("现在的时间是:{}", LocalDateTime.now());
//    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("现在的时间是:{}", LocalDateTime.now());
    }
}
