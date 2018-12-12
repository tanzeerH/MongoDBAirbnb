/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.ArrayList;

/**
 *
 * @author hossa
 */
public class Listing 
{
        public  String _id;
        public String url;
        public String name;
        public String summary;
        public String street;
        public String guest;
        public String city;
        public String review;
        public String zip;
        public String country;
        public String latitude;
        public String longitude;
        public String bathrooms;
        public String bedroom;
      

    public String getUpdate() {
        return Update;
    }

    public void setUpdate(String Update) {
        this.Update = Update;
    }
        public String amenities;
        public String price;
        public String Remove ="Delete";
        public String Update ="Update";

    public String getRemove() {
        return Remove;
    }

    public void setRemove(String Remove) {
        this.Remove = Remove;
    }
        
        public Listing()
        {
            
        }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
        
        public static ArrayList<Listing> getListingFromDocObject(ArrayList<DBObject> objList)
        {
            ArrayList<Listing> list = new ArrayList<>();
            for( DBObject bo : objList)
            {
                System.out.println(bo.get("_id").toString());
                Listing listing = new Listing();
                listing._id = bo.get("_id").toString();
                listing.url=bo.get("url").toString();
                listing.name = bo.get("name").toString();
                listing.summary=bo.get("summary").toString();
                listing.street = bo.get("street").toString();
                if(bo.get("guest")!= null)
                        listing.guest=bo.get("guest").toString();
                listing.city = bo.get("city").toString();
                if(bo.get("review")!= null)
                   listing.review=bo.get("review").toString();
                listing.zip = bo.get("zip").toString();
                listing.country=bo.get("country").toString();
                listing.latitude = bo.get("latitude").toString();
                listing.longitude = bo.get("longitude").toString();
                 if(bo.get("bathrooms")!= null)
                        listing.bathrooms=bo.get("bathrooms").toString();
                if(bo.get("bedrooms")!= null)
                        listing.price = bo.get("bedrooms").toString();
                System.out.println(""+listing.name);
                
                list.add(listing);
            }
            
            return list;
        }
       
    
}
