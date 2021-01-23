package com.github.viqbgrg.springbootoverseer.quartz;

import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.ZqbService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyExceedTimeException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.List;

@Slf4j
public class SampleJob extends QuartzJobBean {
    @Autowired
    private ZqbService zqbService;
    @Autowired
    private IAccountService accountService;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Account> list = accountService.list();
        list.forEach(account -> {
            try {
                zqbService.getUserData(account);
            } catch (WkyExceedTimeException e) {
                try {
                    zqbService.loginKey(account);
                } catch (WkyUnknownErrorException wkyUnknownErrorException) {
                    wkyUnknownErrorException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (WkyUsernamePasswordException wkyUsernamePasswordException) {
                    wkyUsernamePasswordException.printStackTrace();
                }
                log.info("账号:{}的信息过期了", account.getUserID());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WkyUsernamePasswordException e) {
                e.printStackTrace();
            } catch (WkyUnknownErrorException e) {
                e.printStackTrace();
            }
        });
    }
}
