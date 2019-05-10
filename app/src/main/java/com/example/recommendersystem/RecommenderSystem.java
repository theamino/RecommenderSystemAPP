package com.example.recommendersystem;

import com.example.recommendersystem.Data_Classes.Rating;

import java.util.ArrayList;
import java.util.List;

public class RecommenderSystem {
    /**
     *
     */

    public List<Integer> separate(int testDataPercentage , List<Rating> ratings) {
        int startIndex = -1 , endIndex = 0 , userID = 1;
        List<Integer> indeces = new ArrayList<Integer>();
        while(true) {
            startIndex = endIndex;
            while(ratings.get(endIndex).getUserID() != ratings.get(endIndex + 1).getUserID()) {
                endIndex++;
            }
            int count = endIndex - startIndex + 1;
            count *= testDataPercentage / 100;
            List<Integer> RandForUserID = new ArrayList<Integer>();
            while(RandForUserID.size() < count) {
                long random = System.currentTimeMillis() % (endIndex - startIndex + 1) + startIndex;
                boolean isIn = false;
                for(int i : RandForUserID) {
                    if(i == random)
                        isIn = true;
                }
                if(!isIn) RandForUserID.add((int)random);
            }
            indeces.addAll(RandForUserID);
            break;
        }
        return indeces;
    }
}
