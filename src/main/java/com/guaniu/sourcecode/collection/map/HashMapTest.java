package com.guaniu.sourcecode.collection.map;

import java.util.*;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 21:14 2021/1/4
 * @Modified
 */
public class HashMapTest {
    public static void main(String[] args) {
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList();
        if(buildings.length == 0){
            return res;
        }
        int[] heights = new int[(buildings[buildings.length - 1][1]) + 1];
        for(int i = 0; i < buildings.length; i++){
            int[] building = buildings[i];
            for(int j = building[0]; j < building[1]; j++){
                if(building[2] > heights[j]){
                    heights[j] = building[2];
                }
            }
        }
        int height = 0;
        for(int i = buildings[0][0]; i <= buildings[buildings.length - 1][1]; i++){
            if(heights[i] != height){
                height = heights[i];
                res.add(Arrays.asList(new Integer[]{i, height}));
            }
        }
        return res;
    }
}
