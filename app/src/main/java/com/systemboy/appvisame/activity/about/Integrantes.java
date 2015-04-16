package com.systemboy.appvisame.activity.about;


public class Integrantes {

    public static final Integrantes[] CONTACTS = new Integrantes[] {
            new Integrantes("Cesar García", "#3F51B5", "+01 123456789", "john@example.com", "Venice"),
            new Integrantes("Daniel Carreto", "#E91E63", "+01 987654321", "valter@example.com", "Bologna"),
            new Integrantes("Edwin Hernández", "#3F51B5", "+01 123456789", "hgededwindaniel@gmail.com", "Verona"),
            new Integrantes("Llulian Soriano", "#E91E63", "+01 987654321", "teddy@example.com", "Rome"),
            new Integrantes("Diana García", "#3F51B5", "+01 11235813", "ives@example.com", "Milan"),
            new Integrantes("Cesar Santiago", "#E91E63", "+01 123456789", "alajos@example.com", "Bologna"),
            new Integrantes("Nadia", "#3F51B5", "+01 11235813", "me@gian.lu", "Padova"),
            new Integrantes("Luis", "#E91E63", "+01 987654321", "fane@example.com", "Venice"),
    };

    // The fields associated to the person
    private final String mName, mPhone, mEmail, mCity, mColor;

    Integrantes(String name, String color, String phone, String email, String city) {
        mName = name; mColor = color; mPhone = phone; mEmail = email; mCity = city;
    }



    // This method allows to get the item associated to a particular id,
    // uniquely generated by the method getId defined below
    public static Integrantes getItem(int id) {
        for (Integrantes item : CONTACTS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // since mName and mPhone combined are surely unique,
    // we don't need to add another id field
    public int getId() {
        return mName.hashCode() + mPhone.hashCode();
    }

    public static enum Field {
        NAME, COLOR, PHONE, EMAIL, CITY
    }
    public String get(Field f) {
        switch (f) {
            case COLOR: return mColor;
            case PHONE: return mPhone;
            case EMAIL: return mEmail;
            case CITY: return mCity;
            case NAME: default: return mName;
        }
    }


}
