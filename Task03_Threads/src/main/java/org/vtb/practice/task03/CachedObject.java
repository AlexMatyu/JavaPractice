package org.vtb.practice.task03;

public class CachedObject {
    Object cashe;
    long expiredTime;

    public CachedObject(Object cashe, long expiredTime) {
        this.cashe = cashe;
        this.expiredTime = expiredTime;
    }
}
