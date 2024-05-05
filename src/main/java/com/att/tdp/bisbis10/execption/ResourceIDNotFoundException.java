package com.att.tdp.bisbis10.execption;

public class ResourceIDNotFoundException extends RuntimeException {
    public ResourceIDNotFoundException(String resource, long id) {
        super("Resource '" + resource + "' with id " + id + " not found");
    }
}
