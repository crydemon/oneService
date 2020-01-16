package com.vova.trackingupload.repository;


import java.io.File;
import java.util.List;

public interface EventTrackingRepository {
    int[] batchInsert(List<EventTracking> eventTracking);
    String save(File file, String actName);
}

