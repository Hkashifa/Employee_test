package com.example.Employee.service.impl;

import javax.management.RuntimeMBeanException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String id, long id1) {
        System.out.println("The Post" + resource + "id" + id + "id_num:" + id1 + "is not found");
    }
}
