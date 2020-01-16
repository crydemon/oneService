package com.vova.trackingupload.controller;

import com.vova.trackingupload.repository.EventTracking;

import org.apache.poi.ss.usermodel.*;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.*;

public class EventTrackingParse {
    public static void main(String[] args) {
        File file = new File("D:\\coin_game.xls");
        String[][] s = readXLSX(file);
    }


    public static String[][] readXLSX(File file) {

        String[][] evtArr = null;
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rows = datatypeSheet.getPhysicalNumberOfRows();
            int cols = EventTracking.class.getDeclaredFields().length;
            evtArr = new String[rows][cols];
            System.out.println(datatypeSheet.getSheetName() + "???????");
            Iterator<Row> iterator = datatypeSheet.iterator();
            int row = 0;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int col = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    String cell = "";
                    if (currentCell.getCellType() == CellType.STRING) {
                        cell = currentCell.getStringCellValue();
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        cell = String.valueOf(currentCell.getNumericCellValue());
                    }
                    evtArr[row][col++] = cell;
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return evtArr;
    }

    public static String trackingVerify(String[][] evtArr) {
        String[] header = evtArr[0];
        StringBuilder info = new StringBuilder();
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < header.length; i++) {
            for (int j = 1; j < evtArr.length; j++) {
                String field = evtArr[j][i];
                hashMap.put(header[i], field);
            }
            for (int j = 1; j < evtArr.length; j++) {
                String field = evtArr[j][i];
                StringBuilder tmp = new StringBuilder();
                if (field.equals("page_code")) {
                    tmp.append(pageCodeSpec(field));
                } else if (field.equals("event_name")) {
                    tmp.append(eventNameSpec(field));
                } else if (field.equals("trigger_desc")) {
                    tmp.append(triggerDescSpec(field));
                } else if (field.equals("element_desc")) {
                    tmp.append(elementDescSpec(field));
                } else if (field.equals("type")) {
                    tmp.append(typeSpec(field));
                } else if (field.equals("list_type")) {
                    tmp.append(listTypeSpec(field));
                } else if (field.equals("element_name")) {
                    tmp.append(elementNameSpec(field));
                } else if (field.equals("element_id")) {
                    tmp.append(elementIdSpec(field));
                } else if (field.equals("element_type")) {
                    tmp.append(elementTypeSpec(field));
                } else if (field.equals("element_position")) {
                    tmp.append(elementPositionSpec(field));
                } else if (field.equals("extra")) {
                    tmp.append(extraSpec(field));
                } else if (field.equals("report_display_name")) {
                    tmp.append(reportDisplayNameSpec(field));
                } else if (field.equals("report_display_name_desc")) {
                    tmp.append(reportDisplayNameDescSpec(field));
                }
                if (tmp.length() != 0) {
                    info.append("第" + j + "行:" + tmp.toString() + "\n");
                }
            }
        }
        return info.toString();
    }



    private static String pageCodeSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "page_code:" + info;
    }

    private static String eventNameSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "event_name:" + info;
    }

    private static String triggerDescSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "trigger:" + info;
    }

    private static String elementDescSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "element_desc:" + info;
    }

    private static String typeSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "type:" + info;
    }

    private static String listTypeSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "list_type:" + info;
    }

    private static String elementNameSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "element_name:" + info;
    }

    private static String elementIdSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "page_code:" + info;
    }

    private static String elementTypeSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "element_type:" + info;
    }

    private static String elementPositionSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "element_position:" + info;
    }

    private static String extraSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "extra:" + info;
    }

    private static String reportDisplayNameSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "report_display_name:" + info;
    }

    private static String reportDisplayNameDescSpec(String field) {
        String info = commonSpec(field);
        return info.equals("") ? "" : "report_display_name_dec:" + info;
    }

    private static String commonSpec(String fieldName) {
        String info = "";
        info += specNotEmpty(fieldName);
        if (info.equals("")) {
            specNotUpper(fieldName);
        } else {

        }
        return info;
    }

    private static String specNotEmpty(String s) {
        return s == null || s.isEmpty() ? " 不能为空;" : "";
    }

    private static String specNotUpper(String s) {
        return s.compareTo(s.toLowerCase()) != 0 ? "单词之间请用_(下划线)分割;" : "";
    }


}
