package com.github.viqbgrg.springbootoverseer.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.viqbgrg.springbootoverseer.entity.*;
import com.github.viqbgrg.springbootoverseer.service.*;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.*;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbApi;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author viqbg
 */
@Service
@Slf4j
public class ZqbServiceImpl implements ZqbService {

    private List<Account> accounts;
    private IAccountService accountService;
    private IAccountDataService accountDataService;
    private IAccountHistoryService accountHistoryService;
    private IAccountIncomeHistoryService accountIncomeHistoryService;


    public ZqbServiceImpl(IAccountService accountService, IAccountDataService accountDataService, IAccountHistoryService accountHistoryService, IAccountIncomeHistoryService accountIncomeHistoryService) {
        this.accountService = accountService;
        this.accountDataService = accountDataService;
        this.accountHistoryService = accountHistoryService;
        this.accountIncomeHistoryService = accountIncomeHistoryService;
    }


    @Override
    public void updateAccount() {
        accounts = accountService.list();
    }

    /**
     * 重新登陆,然后重新保存loginKey等
     *
     * @param account
     * @return
     * @throws WkyUnknownErrorException
     * @throws IOException
     * @throws WkyUsernamePasswordException
     */
    @Override
    public AccountInfo login(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        XunleiAccount xunleiAccount = new XunleiAccount();
        xunleiAccount.setUsername(account.getUserName());
        xunleiAccount.setPassword(account.getPassword());
        AccountInfo login = ZqbLogin.login(xunleiAccount);
        account.setUpdateAt(LocalDateTime.now());
        account.setCreditkey(login.getCreditkey());
        account.setLoginKey(login.getLoginKey());
        account.setSecureKey(login.getSecureKey());
        account.setSessionID(login.getSessionID());
        accountService.updateById(account);
        return login;
    }

    @Override
    public AccountInfo loginKey(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        LoginKeyDto loginKeyDto = new LoginKeyDto(account.getCreditkey(), account.getUserID(), account.getLoginKey(), ZqbLogin.devicesign(SecureUtil.md5(account.getUserName() + "23333")));
        AccountInfo accountInfo = ZqbLogin.loginKey(loginKeyDto);
        account.setUpdateAt(LocalDateTime.now());
        account.setSessionID(accountInfo.getSessionID());
        account.setLoginKey(accountInfo.getLoginKey());
        account.setSecureKey(accountInfo.getSecureKey());
        accountService.updateById(account);
        return accountInfo;
    }

    @Override
    public void getProduceStat(Account account) throws IOException {
        ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserID(), account.getNickName());
        ZqbApi zqbApi = new ZqbApi(apiInfo);
        ProduceStat produceStat = zqbApi.getProduceStat();
    }

    @Override
    public void getUserData(Account account) throws IOException {
        Long userID = account.getUserID();
        log.info("获取账号{}的信息", userID);
        LocalDateTime now = LocalDateTime.now();
        ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserID(), account.getNickName());
        ZqbApi zqbApi = new ZqbApi(apiInfo);
        AccountData accountData = accountDataService.getById(userID);
        if (accountData == null) {
            accountData = new AccountData();
            accountData.setUserID(account.getUserID());
            accountData.setPrivilege(zqbApi.getPrivilege());
        }
        MineInfo mineInfo = zqbApi.getMineInfo();
        accountData.setMineInfo(mineInfo);
        Devices deviceInfo = zqbApi.getDeviceInfo();
        accountData.setDeviceInfo(deviceInfo);
        BalanceInfo balanceInfo = zqbApi.getBalanceInfo();
        accountData.setBalanceInfo(balanceInfo);
        ProduceStat produceStat = zqbApi.getProduceStat();
        accountData.setProduceStat(produceStat);
        accountData.setZqbSpeedStat(new int[24]);
        accountData.setUpdateAt(now);
        accountDataService.saveOrUpdate(accountData);
        if (now.getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            saveHistory(account);
        }
    }

    @Override
    public void saveHistory(Account account) {
        Long userID = account.getUserID();
        LocalDate now = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Map<String, Object> map = new HashMap<>();
        map.put("user_i_d", userID);
        map.put("day", now);
        QueryWrapper<AccountHistory> classQueryWrapper = Wrappers.<AccountHistory>query().allEq(map);
        AccountHistory todayData = accountHistoryService.getOne(classQueryWrapper);
        boolean empty = ObjectUtils.isEmpty(todayData);
        if (empty) {
            todayData = new AccountHistory();
            todayData.setUpdateAt(localDateTime);
            todayData.setUserID(account.getUserID());
            todayData.setDay(now);
            todayData.setPdcDetail(new ArrayList<>());
            todayData.setProduceStat(new ArrayList<>());
            todayData.setSpeedStat(new ArrayList<>());
            todayData.setRefreshes(1);
        }
        AccountData accountData = accountDataService.getById(userID);
        LocalDateTime updateAt = accountData.getUpdateAt();
        if (updateAt.plusMinutes(30).isBefore(localDateTime) || updateAt.getDayOfMonth() != now.getDayOfMonth()){
            return;
        }
        int pdc = accountData.getMineInfo().getDev_m().getPdc();
        todayData.setPdc(todayData.getPdc() + pdc);
        todayData.setBoxPdc(todayData.getBoxPdc() + accountData.getMineInfo().getTd_box_pdc());
        PdcDetail pdcDetail = new PdcDetail();
        pdcDetail.setPdc(pdc);
        Privilege privilege = accountData.getPrivilege();
        pdcDetail.setMid(privilege.getMid());
        List<PdcDetail> pdcDetail1 = todayData.getPdcDetail();
        pdcDetail1.add(pdcDetail);
        todayData.setPdcDetail(pdcDetail1);
        int balance = todayData.getBalance();
        BalanceInfo balanceInfo = accountData.getBalanceInfo();
        Integer rCanUse = balanceInfo.getRCanUse();
        todayData.setBalance(balance + rCanUse);
        todayData.setIncome(balanceInfo.getRHA() + todayData.getIncome());
        List<ProduceStatHistory> produceStat = todayData.getProduceStat();
        ProduceStatHistory produceStatHistory = new ProduceStatHistory();
        produceStatHistory.setMid(accountData.getPrivilege().getMid());
        produceStatHistory.setHourlyList(accountData.getProduceStat().getHourlyList());
        produceStat.add(produceStatHistory);
        todayData.setProduceStat(produceStat);
        Devices deviceInfo = accountData.getDeviceInfo();
        List<DeviceInfo> deviceInfoList = deviceInfo.getDeviceInfoList();
        AccountHistory finalTodayData = todayData;
        int speed = deviceInfoList.stream().mapToInt(device -> (device.getDcdnUploadSpeed() / 1024)).sum();
        deviceInfoList.forEach(device -> {
            finalTodayData.setLastSpeed(finalTodayData.getLastSpeed() + device.getDcdnUploadSpeed()/1024);
            finalTodayData.setDeploySpeed(finalTodayData.getDeploySpeed() + device.getDcdnDownloadSpeed()/1024);
        });
        if (accountData.getZqbSpeedStat() == null) {
            int[] zqbSpeedStat = new int[24];
            accountData.setZqbSpeedStat(zqbSpeedStat);
        }
        int[] zqbSpeedStat = accountData.getZqbSpeedStat();
        if (accountData.getZqbSpeedStatTimes() == localDateTime.getHour()) {
            if (accountData.getZqbSpeedStat()[23] != 0) {
                speed = (speed + accountData.getZqbSpeedStat()[23] / 8) / 2;
            }
            zqbSpeedStat[23] = speed * 8;
        }else {
            List<Integer> intList= Arrays.stream(zqbSpeedStat).boxed().collect(Collectors.toList());
            intList.remove(0);
            intList.add(speed * 8);
            zqbSpeedStat = intList.stream().mapToInt(Integer::valueOf).toArray();
        }
        accountData.setZqbSpeedStat(zqbSpeedStat);
        accountData.setZqbSpeedStatTimes(localDateTime.getHour());
        accountDataService.saveOrUpdate(accountData);
        List<SpeedStat> speedStat = todayData.getSpeedStat();
        SpeedStat speedStat1 = new SpeedStat();
        int[] zqbSpeedStat1 = accountData.getZqbSpeedStat();
        speedStat1.setDevSpeed(zqbSpeedStat1);
        speedStat1.setMid(accountData.getPrivilege().getMid());
        speedStat.add(speedStat1);
        todayData.setSpeedStat(speedStat);
        if (empty) {
            accountHistoryService.save(todayData);
        }else{
            UpdateWrapper<AccountHistory> uw = new UpdateWrapper<>();
            uw.eq("user_i_d", account.getUserID());
            uw.eq("day", now.toString());
            accountHistoryService.update(todayData,uw);
        }

        saveIncomeHistory(account, todayData.getPdcDetail());
    }
    private void saveIncomeHistory(Account account, List<PdcDetail> pdcDetail) {
        log.info("{}: saveIncomeHistory", account.getUserID());
        LocalDate now = LocalDate.now();
        AccountIncomeHistory accountIncomeHistory = new AccountIncomeHistory();
        accountIncomeHistory.setDay(now);
        accountIncomeHistory.setUserID(account.getUserID());
        accountIncomeHistory.setPdcDetail(pdcDetail);
        accountIncomeHistory.setUpdateAt(LocalDateTime.now());
        UpdateWrapper<AccountIncomeHistory> uw = new UpdateWrapper<>();
        uw.eq("user_i_d", account.getUserID());
        uw.eq("day", now.toString());
        accountIncomeHistoryService.saveOrUpdate(accountIncomeHistory, uw);
    }


}
