package studentrentalwedsite.webtest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalDataJsonStruct {

    @JsonProperty("_id")
    private IdWrapper id;

    @JsonProperty("坪數")
    private List<Float> area;

    @JsonProperty("是否可養寵物")
    private Integer allowPet;

    @JsonProperty("是否可開伙")
    private Integer allowCook;

    @JsonProperty("是否可養魚")
    private Integer allowFish;

    @JsonProperty("是否有電梯")
    private Integer hasElevator;

    @JsonProperty("是否有汽車停車位")
    private Integer hasCarParking;

    @JsonProperty("是否有機車停車位")
    private Integer hasScooterParking;

    @JsonProperty("是否可租屋補助")
    private Integer rentalSubsidy;

    @JsonProperty("是否有頂樓加蓋")
    private Integer hasRooftopAddOn;

    @JsonProperty("格局")
    private Layout layout;

    @JsonProperty("性別限制")
    private GenderLimit genderLimit;

    @JsonProperty("地址")
    private String address;

    @JsonProperty("租金")
    private RentalRange rentalRange;

    @JsonProperty("聯絡方式")
    private List<ContactInfo> contactInfos;

    @JsonProperty("照片")
    private List<String> photos;

    // --- Getters & Setters ---

    public IdWrapper getId() {
        return id;
    }

    public void setId(IdWrapper id) {
        this.id = id;
    }

    public List<Float> getArea() {
        return area;
    }

    public void setArea(List<Float> area) {
        this.area = area;
    }

    public Integer getAllowPet() {
        return allowPet;
    }

    public void setAllowPet(Integer allowPet) {
        this.allowPet = allowPet;
    }

    public Integer getAllowCook() {
        return allowCook;
    }

    public void setAllowCook(Integer allowCook) {
        this.allowCook = allowCook;
    }

    public Integer getAllowFish() {
        return allowFish;
    }

    public void setAllowFish(Integer allowFish) {
        this.allowFish = allowFish;
    }

    public Integer getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Integer hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Integer getHasCarParking() {
        return hasCarParking;
    }

    public void setHasCarParking(Integer hasCarParking) {
        this.hasCarParking = hasCarParking;
    }

    public Integer getHasScooterParking() {
        return hasScooterParking;
    }

    public void setHasScooterParking(Integer hasScooterParking) {
        this.hasScooterParking = hasScooterParking;
    }

    public Integer getRentalSubsidy() {
        return rentalSubsidy;
    }

    public void setRentalSubsidy(Integer rentalSubsidy) {
        this.rentalSubsidy = rentalSubsidy;
    }

    public Integer getHasRooftopAddOn() {
        return hasRooftopAddOn;
    }

    public void setHasRooftopAddOn(Integer hasRooftopAddOn) {
        this.hasRooftopAddOn = hasRooftopAddOn;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public GenderLimit getGenderLimit() {
        return genderLimit;
    }

    public void setGenderLimit(GenderLimit genderLimit) {
        this.genderLimit = genderLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RentalRange getRentalRange() {
        return rentalRange;
    }

    public void setRentalRange(RentalRange rentalRange) {
        this.rentalRange = rentalRange;
    }

    public List<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    // --- Inner Classes ---

    public static class IdWrapper {
        @JsonProperty("$oid")
        private String oid;

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }
    }

    public static class Layout {
        @JsonProperty("廳")
        private int livingRoom;

        @JsonProperty("衛")
        private int bathroom;

        @JsonProperty("房")
        private int bedroom;

        public int getLivingRoom() {
            return livingRoom;
        }

        public void setLivingRoom(int livingRoom) {
            this.livingRoom = livingRoom;
        }

        public int getBathroom() {
            return bathroom;
        }

        public void setBathroom(int bathroom) {
            this.bathroom = bathroom;
        }

        public int getBedroom() {
            return bedroom;
        }

        public void setBedroom(int bedroom) {
            this.bedroom = bedroom;
        }
    }

    public static class RentalRange {
        private int minRental;
        private int maxRental;

        public int getMinRental() {
            return minRental;
        }

        public void setMinRental(int minRental) {
            this.minRental = minRental;
        }

        public int getMaxRental() {
            return maxRental;
        }

        public void setMaxRental(int maxRental) {
            this.maxRental = maxRental;
        }
    }

    public static class GenderLimit {
        @JsonProperty("男")
        private Integer male;

        @JsonProperty("女")
        private Integer female;

        public Integer getMale() {
            return male;
        }

        public void setMale(Integer male) {
            this.male = male;
        }

        public Integer getFemale() {
            return female;
        }

        public void setFemale(Integer female) {
            this.female = female;
        }

        public String getGenderLimitText() {
            if (male != null && female != null) {
                if (male == 1 && female == 1) {
                    return "不限";
                } else if (male == 1) {
                    return "限男";
                } else if (female == 1) {
                    return "限女";
                } else {
                    return "不限"; // 或 "無限制"、"不接受任何性別" 視邏輯需求
                }
            }
            return "未知";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContactInfo {
        @JsonProperty("聯絡人")
        private String contactPerson;

        @JsonProperty("手機")
        private List<String> phones;

        @JsonProperty("lineID")
        private List<String> lineIds;

        // 保留可擴充欄位
        @JsonProperty("lineLink")
        private List<String> lineLinks;

        @JsonProperty("others")
        private List<String> others;

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public List<String> getPhones() {
            return phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
        }

        public List<String> getLineIds() {
            return lineIds;
        }

        public void setLineIds(List<String> lineIds) {
            this.lineIds = lineIds;
        }

        public List<String> getLineLinks() {
            return lineLinks;
        }

        public void setLineLinks(List<String> lineLinks) {
            this.lineLinks = lineLinks;
        }

        public List<String> getOthers() {
            return others;
        }

        public void setOthers(List<String> others) {
            this.others = others;
        }
    }
}
