package com.example.aayush.aburrido;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by Ayushgarg on 12-Jun-16.
 */

public class Data implements Serializable{
    public class Pagination{
        @SerializedName("object_count")
        int objectCount;
        @SerializedName("page_number")
        int pageNumber;
        @SerializedName("page_size")
        int pageSize;
        @SerializedName("page_count")
        int pageCount;
        public int getPageCount(){
            return pageCount;
        }
        public void setPageCount(int pageCount){
            this.pageCount=pageCount;
        }

        public int getObjectCount() {
            return objectCount;
        }

        public void setObjectCount(int objectCount) {
            this.objectCount = objectCount;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }
    Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public ArrayList<Events> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Events> events) {
        this.events = events;
    }

    public class Events{
        public class Name{
            String text;
            String html;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }
        }
        Name name;
        public  class Description{
            String text;
            String html;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }
        }
        Description description;
        String id;
        String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public class Start{
            String timezone;
            String local;
            String utc;

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getLocal() {
                return local;
            }

            public void setLocal(String local) {
                this.local = local;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
        Start start;
        public class End{
            String timezone;
            String local;
            String utc;

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getLocal() {
                return local;
            }

            public void setLocal(String local) {
                this.local = local;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
        End end;
        String created;
        String changed;
        int Capacity;
        String status;
        String currency;
        boolean listed;
        @SerializedName("online_event")
        boolean onlineEvent;
        @SerializedName("venue_id")
        String venueId;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getChanged() {
            return changed;
        }

        public void setChanged(String changed) {
            this.changed = changed;
        }

        public int getCapacity() {
            return Capacity;
        }

        public void setCapacity(int capacity) {
            Capacity = capacity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isListed() {
            return listed;
        }

        public void setListed(boolean listed) {
            this.listed = listed;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public boolean isOnlineEvent() {
            return onlineEvent;
        }

        public void setOnlineEvent(boolean onlineEvent) {
            this.onlineEvent = onlineEvent;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }
        public class Logo{
            String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
        Logo logo;

        public Logo getLogo() {
            return logo;
        }

        public void setLogo(Logo logo) {
            this.logo = logo;
        }

        public Start getStart() {
            return start;
        }

        public void setStart(Start start) {
            this.start = start;
        }

        public End getEnd() {
            return end;
        }

        public void setEnd(End end) {
            this.end = end;
        }

        public Description getDescription() {
            return description;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }
    }
    ArrayList<Events> events;
}
