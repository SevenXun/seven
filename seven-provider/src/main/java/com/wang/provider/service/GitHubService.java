package com.wang.provider.service;

import com.google.common.collect.Sets;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Set;

public class GitHubService {

    public Set<Integer> getAuthorizedProductIds(String token) {
        Set<Integer> projectIdSet = Sets.newHashSet();

        return projectIdSet;
    }

    public static void main(String[] args) {
        String a = "1234";
        String b = "12" + "34";
        System.out.println(a == b);
        Integer ia = 127;
        Integer ib = 127;
        System.out.println(ia == ib);
        Integer ic = 129;
        Integer id = 129;
        System.out.println(ic == id);
    }
}

