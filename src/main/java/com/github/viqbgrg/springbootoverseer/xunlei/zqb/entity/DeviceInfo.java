package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "device_sn",
        "device_type",
        "device_name",
        "system_name",
        "system_version",
        "hardware_model",
        "mac_address",
        "lan_ip",
        "account_id",
        "account_name",
        "product_id",
        "upgradeable",
        "exception_name",
        "exception_message",
        "status",
        "ip",
        "ip_info",
        "broker_id",
        "imported",
        "bind_time",
        "connect_time",
        "disconnect_time",
        "device_id",
        "paused",
        "hibernated",
        "disk_quota",
        "schedule_hours",
        "dcdn_upnp_status",
        "auth",
        "dcdn_id",
        "dcdn_download_speed",
        "dcdn_upload_speed",
        "dcdn_clients",
        "dcdn_upnp_message"
})
@Data
public class DeviceInfo {

    @JsonProperty("device_sn")
    private String deviceSn;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonProperty("device_name")
    private String deviceName;
    @JsonProperty("system_name")
    private String systemName;
    @JsonProperty("system_version")
    private String systemVersion;
    @JsonProperty("hardware_model")
    private String hardwareModel;
    @JsonProperty("mac_address")
    private String macAddress;
    @JsonProperty("lan_ip")
    private String lanIp;
    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("upgradeable")
    private Boolean upgradeable;
    @JsonProperty("exception_name")
    private String exceptionName;
    @JsonProperty("exception_message")
    private String exceptionMessage;
    @JsonProperty("status")
    private String status;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("ip_info")
    private IpInfo ipInfo;
    @JsonProperty("broker_id")
    private Integer brokerId;
    @JsonProperty("imported")
    private Integer imported;
    @JsonProperty("bind_time")
    private Integer bindTime;
    @JsonProperty("connect_time")
    private Integer connectTime;
    @JsonProperty("disconnect_time")
    private Integer disconnectTime;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("paused")
    private Boolean paused;
    @JsonProperty("hibernated")
    private Boolean hibernated;
    @JsonProperty("disk_quota")
    private Integer diskQuota;
    @JsonProperty("schedule_hours")
    private List<ScheduleHour> scheduleHours = null;
    @JsonProperty("dcdn_upnp_status")
    private String dcdnUpnpStatus;
    @JsonProperty("auth")
    private List<Object> auth = null;
    @JsonProperty("dcdn_id")
    private String dcdnId;
    @JsonProperty("dcdn_download_speed")
    private Integer dcdnDownloadSpeed;
    @JsonProperty("dcdn_upload_speed")
    private Integer dcdnUploadSpeed;
    @JsonProperty("dcdn_clients")
    private List<DcdnClient> dcdnClients = null;
    @JsonProperty("dcdn_upnp_message")
    private String dcdnUpnpMessage;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "device_sn",
            "dcdn_id",
            "status",
            "upload_speed",
            "download_speed",
            "upload_speed_max",
            "download_speed_max",
            "space_used",
            "space_quota",
            "data_path",
            "login_status",
            "login_error",
            "device_id"
    })
    @Data
    static class DcdnClient {

        @JsonProperty("device_sn")
        private String deviceSn;
        @JsonProperty("dcdn_id")
        private String dcdnId;
        @JsonProperty("status")
        private String status;
        @JsonProperty("upload_speed")
        private Integer uploadSpeed;
        @JsonProperty("download_speed")
        private Integer downloadSpeed;
        @JsonProperty("upload_speed_max")
        private Integer uploadSpeedMax;
        @JsonProperty("download_speed_max")
        private Integer downloadSpeedMax;
        @JsonProperty("space_used")
        private String spaceUsed;
        @JsonProperty("space_quota")
        private String spaceQuota;
        @JsonProperty("data_path")
        private String dataPath;
        @JsonProperty("login_status")
        private String loginStatus;
        @JsonProperty("login_error")
        private String loginError;
        @JsonProperty("device_id")
        private String deviceId;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "isp",
            "country",
            "province",
            "city"
    })
    @Data
    static class IpInfo {

        @JsonProperty("isp")
        private String isp;
        @JsonProperty("country")
        private String country;
        @JsonProperty("province")
        private String province;
        @JsonProperty("city")
        private String city;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "from",
            "to",
            "type",
            "params"
    })
    @Data
    static class ScheduleHour {

        @JsonProperty("from")
        private Integer from;
        @JsonProperty("to")
        private Integer to;
        @JsonProperty("type")
        private String type;
        @JsonProperty("params")
        private Params params;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({

    })
    @Data
    private class Params {


    }


}
