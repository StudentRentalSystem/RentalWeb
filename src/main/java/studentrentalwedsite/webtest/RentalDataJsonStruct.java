package studentrentalwedsite.webtest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RentalDataJsonStruct {

    @JsonProperty("_id")
    private IdWrapper id;

    @JsonProperty("坪數")
    private List<Integer> area;

    @JsonProperty("是否可養寵物")
    private String allowPet;

    @JsonProperty("是否可開伙")
    private String allowCook;

    @JsonProperty("格局")
    private Layout layout;

    @JsonProperty("性別限制")
    private String genderLimit;

    @JsonProperty("地址")
    private String address;

    @JsonProperty("是否可養魚")
    private String allowFish;

    @JsonProperty("租金")
    private List<RentalRange> rentalRange;

    @JsonProperty("聯絡方式")
    private List<ContactInfo> contactInfos;

    @JsonProperty("照片")
    private List<String> photos;

    @JsonProperty("是否有電梯")
    private String hasElevator;

    public IdWrapper getId() {
        return id;
    }

    public void setId(IdWrapper id) {
        this.id = id;
    }

    public List<Integer> getArea() {
        return area;
    }

    public void setArea(List<Integer> area) {
        this.area = area;
    }

    public String getAllowPet() {
        return allowPet;
    }

    public void setAllowPet(String allowPet) {
        this.allowPet = allowPet;
    }

    public String getAllowCook() {
        return allowCook;
    }

    public void setAllowCook(String allowCook) {
        this.allowCook = allowCook;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public String getGenderLimit() {
        return genderLimit;
    }

    public void setGenderLimit(String genderLimit) {
        this.genderLimit = genderLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAllowFish() {
        return allowFish;
    }

    public void setAllowFish(String allowFish) {
        this.allowFish = allowFish;
    }

    public List<RentalRange> getRentalRange() {
        return rentalRange;
    }

    public void setRentalRange(List<RentalRange> rentalRange) {
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

    public String getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(String hasElevator) {
        this.hasElevator = hasElevator;
    }

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContactInfo {
        @JsonProperty("聯絡人")
        private String contactPerson;
        @JsonProperty("手機")
        private List<String> phones;
        @JsonProperty("lineID")
        private List<String> lineIds;

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
    }
}
