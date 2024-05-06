package org.vtb.practice.task03;

public class CashedObject {
    Object cashe;
    long expiredTime;

    public CashedObject(Object cashe, long expiredTime) {
        this.cashe = cashe;
        this.expiredTime = expiredTime;
    }
}
