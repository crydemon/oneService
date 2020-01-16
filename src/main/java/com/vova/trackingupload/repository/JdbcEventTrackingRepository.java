package com.vova.trackingupload.repository;

import com.vova.trackingupload.controller.EventTrackingParse;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcEventTrackingRepository implements EventTrackingRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public String save(File file, String actName) {
        String[][] evtArr = EventTrackingParse.readXLSX(file);
        System.out.println(Arrays.toString(evtArr[0]));
        String info = EventTrackingParse.trackingVerify(evtArr);
        if (info.equals("")) {
            saveToMySQL(evtArr, actName);
            return "upload success";
        } else {
            return info;
        }
    }


    private void saveToMySQL(String[][] evtArr, String actName) {
        List<EventTracking> list = new ArrayList<>();
        for (int j = 1; j < evtArr.length; j++) {
            String[] str = new String[evtArr[j].length + 2];
            str[0] = actName;
            str[1] = new Timestamp(System.currentTimeMillis()).toString();
            for (int i = 2; i < str.length; i++) {
                str[i] = evtArr[j][i - 2];
            }
            list.add(EventTracking.init(str));
            System.out.println(list.get(j - 1));
        }
        batchInsert(list);
    }

    @Override
    public int[] batchInsert(List<EventTracking> eventTracking) {
        System.out.println(jdbcTemplate == null);
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (int i = 1; i < EventTracking.class.getDeclaredFields().length; i++) {
            sb.append(",?");
        }
        return this.jdbcTemplate.batchUpdate(
                "replace into event_tracking_meta (" +
                        "act_name," +
                        "create_time," +
                        "page_code,\n" +
                        "event_name,\n" +
                        "trigger_desc,\n" +
                        "element_desc,\n" +
                        "list_type,\n" +
                        "`type` ,\n" +
                        "element_name,\n" +
                        "element_id,\n" +
                        "element_type,\n" +
                        "element_position,\n" +
                        "extra,\n" +
                        "report_display_name,\n" +
                        "report_display_name_desc) values(" + sb.toString() + ")",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, eventTracking.get(i).getActName());
                        ps.setTimestamp(2, eventTracking.get(i).getCreateTime());
                        ps.setString(3, eventTracking.get(i).getPageCode());
                        ps.setString(4, eventTracking.get(i).getEventName());
                        ps.setString(5, eventTracking.get(i).getTriggerDesc());
                        ps.setString(6, eventTracking.get(i).getElementDesc());
                        ps.setString(7, eventTracking.get(i).getListType());
                        ps.setString(8, eventTracking.get(i).getType());
                        ps.setString(9, eventTracking.get(i).getElementName());
                        ps.setString(10, eventTracking.get(i).getElementId());
                        ps.setString(11, eventTracking.get(i).getElementType());
                        ps.setString(12, eventTracking.get(i).getElementPosition());
                        ps.setString(13, eventTracking.get(i).getExtra());
                        ps.setString(14, eventTracking.get(i).getReportDisplayName());
                        ps.setString(15, eventTracking.get(i).getReportDisplayNameDesc());

                    }

                    public int getBatchSize() {
                        return eventTracking.size();
                    }

                });
    }


}
