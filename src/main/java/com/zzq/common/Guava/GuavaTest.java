package com.zzq.common.Guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GuavaTest {
    public static void main(String[] args) {

        HashMap<String, Map<String, String>> map = Maps.newHashMap();
        List<String> list = Lists.newArrayList();
        HashSet<Object> set = Sets.newHashSet();
        ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
        String s = of.get(1);
        System.out.println(s);
    }
}
