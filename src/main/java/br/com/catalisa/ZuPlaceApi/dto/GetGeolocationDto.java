package br.com.catalisa.ZuPlaceApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetGeolocationDto {

    private String ip_address;
    private String city;
    private int city_geoname_id;
    private String region;
    private String region_iso_code;
    private int region_geoname_id;
    private String postal_code;
    private String country;
    private String country_code;
    private int country_geoname_id;
    private boolean country_is_eu;
    private String continent;
    private String continent_code;
    private int continent_geoname_id;
    private double longitude;
    private double latitude;
    private Security security;
    private Timezone timezone;
    private Flag flag;
    private Currency currency;
    private Connection connection;

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCity_geoname_id() {
        return city_geoname_id;
    }

    public void setCity_geoname_id(int city_geoname_id) {
        this.city_geoname_id = city_geoname_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_iso_code() {
        return region_iso_code;
    }

    public void setRegion_iso_code(String region_iso_code) {
        this.region_iso_code = region_iso_code;
    }

    public int getRegion_geoname_id() {
        return region_geoname_id;
    }

    public void setRegion_geoname_id(int region_geoname_id) {
        this.region_geoname_id = region_geoname_id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public int getCountry_geoname_id() {
        return country_geoname_id;
    }

    public void setCountry_geoname_id(int country_geoname_id) {
        this.country_geoname_id = country_geoname_id;
    }

    public boolean isCountry_is_eu() {
        return country_is_eu;
    }

    public void setCountry_is_eu(boolean country_is_eu) {
        this.country_is_eu = country_is_eu;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public int getContinent_geoname_id() {
        return continent_geoname_id;
    }

    public void setContinent_geoname_id(int continent_geoname_id) {
        this.continent_geoname_id = continent_geoname_id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static class Security {
        private boolean is_vpn;

        public boolean isIs_vpn() {
            return is_vpn;
        }

        public void setIs_vpn(boolean is_vpn) {
            this.is_vpn = is_vpn;
        }
    }

    public static class Timezone {
        private String name;
        private String abbreviation;
        private int gmt_offset;
        private String current_time;
        private boolean is_dst;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public int getGmt_offset() {
            return gmt_offset;
        }

        public void setGmt_offset(int gmt_offset) {
            this.gmt_offset = gmt_offset;
        }

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public boolean isIs_dst() {
            return is_dst;
        }

        public void setIs_dst(boolean is_dst) {
            this.is_dst = is_dst;
        }
    }

    public static class Flag {
        private String emoji;
        private String unicode;
        private String png;
        private String svg;

        public String getEmoji() {
            return emoji;
        }

        public void setEmoji(String emoji) {
            this.emoji = emoji;
        }

        public String getUnicode() {
            return unicode;
        }

        public void setUnicode(String unicode) {
            this.unicode = unicode;
        }

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }
    }

    public static class Currency {
        private String currency_name;
        private String currency_code;

        public String getCurrency_name() {
            return currency_name;
        }

        public void setCurrency_name(String currency_name) {
            this.currency_name = currency_name;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }
    }

    public static class Connection {
        private int autonomous_system_number;
        private String autonomous_system_organization;
        private String connection_type;
        private String isp_name;
        private String organization_name;

        public int getAutonomous_system_number() {
            return autonomous_system_number;
        }

        public void setAutonomous_system_number(int autonomous_system_number) {
            this.autonomous_system_number = autonomous_system_number;
        }

        public String getAutonomous_system_organization() {
            return autonomous_system_organization;
        }

        public void setAutonomous_system_organization(String autonomous_system_organization) {
            this.autonomous_system_organization = autonomous_system_organization;
        }

        public String getConnection_type() {
            return connection_type;
        }

        public void setConnection_type(String connection_type) {
            this.connection_type = connection_type;
        }

        public String getIsp_name() {
            return isp_name;
        }

        public void setIsp_name(String isp_name) {
            this.isp_name = isp_name;
        }

        public String getOrganization_name() {
            return organization_name;
        }

        public void setOrganization_name(String organization_name) {
            this.organization_name = organization_name;
        }
    }
}
