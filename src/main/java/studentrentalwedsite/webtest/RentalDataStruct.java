package studentrentalwedsite.webtest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class RentalDataStruct {

    public static class Layout {
        private final int livingRoom;
        private final int bathroom;
        private final int bedroom;

        public Layout(int livingRoom, int bathroom, int bedroom) {
            this.livingRoom = livingRoom;
            this.bathroom = bathroom;
            this.bedroom = bedroom;
        }

        @Override
        public String toString() {
            return String.format("%d 廳, %d 衛, %d 房", livingRoom, bathroom, bedroom);
        }
    }

    public static class RentalRange {
        private final int minRental;
        private final int maxRental;

        public RentalRange(int minRental, int maxRental) {
            this.minRental = minRental;
            this.maxRental = maxRental;
        }

        @Override
        public String toString() {
            return minRental == maxRental ? minRental + " 元" : minRental + " ~ " + maxRental + " 元";
        }
    }

    public static class ContactInfo {
        private final String contactPerson;
        private final List<String> phones;
        private final List<String> lineIds;

        public ContactInfo(String contactPerson, List<String> phones, List<String> lineIds) {
            this.contactPerson = contactPerson;
            this.phones = phones;
            this.lineIds = lineIds;
        }

        @Override
        public String toString() {
            return String.format("%s，電話: %s，LineID: %s", contactPerson, phones, lineIds);
        }
    }



    // 基本屬性
    private final String id;
    private final int area;
    private final String allowPet;
    private final String allowCook;
    private final Layout layout;
    private final String genderLimit;
    private final String address;
    private final String allowFish;
    private final RentalRange rentalRange;
    private final List<ContactInfo> contactInfos;
    private final List<String> photos;
    private final String hasElevator;


    public RentalDataStruct(String id, int area, String allowPet, String allowCook,
                           Layout layout, String genderLimit, String address, String allowFish,
                           RentalRange rentalRange, List<ContactInfo> contactInfos, List<String> photos,
                           String hasElevator) {
        this.id = id;
        this.area = area;
        this.allowPet = allowPet;
        this.allowCook = allowCook;
        this.layout = layout;
        this.genderLimit = genderLimit;
        this.address = address;
        this.allowFish = allowFish;
        this.rentalRange = rentalRange;
        this.contactInfos = contactInfos;
        this.photos = photos;
        this.hasElevator = hasElevator;
    }

    public RentalDataStruct() {
        this.id = "1";
        this.area = 1 ;
        this.allowPet = "1";
        this.allowCook = "1";
        this.layout = new Layout(1, 1, 1);
        this.genderLimit = "1";
        this.address = "1";
        this.allowFish = "1";
        this.rentalRange = new RentalRange(500,5000);
        this.contactInfos = new ArrayList<>();
        this.photos = new ArrayList<>();
        this.hasElevator = "1";
    }

    public String getId() {
        return id;
    }

    public int getArea() {
        return area;
    }

    public String getAllowPet() {
        return allowPet;
    }

    public String getAllowCook() {
        return allowCook;
    }

    public String getLayout() {
        return layout.toString();
    }

    public String getGenderLimit() {
        return genderLimit;
    }

    public String getAddress() {
        return address;
    }

    public String getAllowFish() {
        return allowFish;
    }

    public String getRentalRange() {
        return rentalRange.toString();
    }

    public String getContactInfos() {
        return contactInfos.toString();
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getHasElevator() {
        return hasElevator;
    }

}
