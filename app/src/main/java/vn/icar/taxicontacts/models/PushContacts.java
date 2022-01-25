package vn.icar.taxicontacts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PushContacts {
    @SerializedName("phone")
    @Expose
    private Integer phone;
    @SerializedName("additionalPhone")
    @Expose
    private List<Integer> additionalPhone = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses = null;

    public Integer getPhone() {
        return phone;
    }

    public PushContacts setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public List<Integer> getAdditionalPhone() {
        return additionalPhone;
    }

    public PushContacts setAdditionalPhone(List<Integer> additionalPhone) {
        this.additionalPhone = additionalPhone;
        return this;
    }

    public String getName() {
        return name;
    }

    public PushContacts setName(String name) {
        this.name = name;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public PushContacts setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Override
    public String toString() {
        return "PushContacts{" +
                "phone=" + phone +
                ", additionalPhone=" + additionalPhone +
                ", name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }

    public static class Address {

        @SerializedName("coords")
        @Expose
        private Coords coords;
        @SerializedName("address")
        @Expose
        private AddressChild address;

        public Coords getCoords() {
            return coords;
        }

        public Address setCoords(Coords coords) {
            this.coords = coords;
            return this;
        }

        public AddressChild getAddressChild() {
            return address;
        }

        public Address setAddressChild(AddressChild address) {
            this.address = address;
            return this;
        }


        @Override
        public String toString() {
            return "Address{" +
                    "coords=" + coords +
                    ", address=" + address +
                    '}';
        }
    }

    public static class AddressChild {

        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("province")
        @Expose
        private String province;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("ward")
        @Expose
        private String ward;
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("addressName")
        @Expose
        private String addressName;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }


        @Override
        public String toString() {
            return "AddressChild{" +
                    "country='" + country + '\'' +
                    ", province='" + province + '\'' +
                    ", district='" + district + '\'' +
                    ", ward='" + ward + '\'' +
                    ", street='" + street + '\'' +
                    ", addressName='" + addressName + '\'' +
                    '}';
        }
    }

    public static class Coords {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

        @Override
        public String toString() {
            return "Coords{" +
                    "type='" + type + '\'' +
                    ", coordinates=" + coordinates +
                    '}';
        }
    }
}
