package com.hometask.model;


import java.net.URI;
import java.util.HashMap;
import java.util.List;

/*
    For HATOAS simple implementation to add value to the Rest solution
 */
public class ResourceSupport {

    private HashMap<String,URI> links = new HashMap<>();


    public HashMap<String, URI> getLinks() {
        return links;
    }

    public void setLinks(HashMap<String,URI> links) {
        this.links = links;
    }

    public void addLink(String rel, URI uri){
        this.links.put(rel, uri);
    }
}
