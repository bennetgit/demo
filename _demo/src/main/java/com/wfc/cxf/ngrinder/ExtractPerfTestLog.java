package com.wfc.cxf.ngrinder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfc.cxf.utils.HttpUtils;

public class ExtractPerfTestLog {

    private static final String URL = "http://localhost:8000/perftest/api";
    private static final String AUTHORIZATION = "Authorization";
    private static final String USER_INFO = "admin:admin";
    private static final String PAGE_URL = "http://192.168.3.111:8090/ngrinder/perftest/api/last?page=0&size=100";

    private static final String SHEET_NAME = "sheet_name";
    private static final String FINISHED = "FINISHED";

    private static final char BEGIN_CHAR = '-';
    private static final char END_CHAR = 'o';

    private static final String STATUS_KEY = "status";

    private static final String RESULT_FILE_NAME = "stress_testing_log_1.xls";

    private static final String[] COLUMNS_HEADER = { "TestName", "Threads", "tests counts", "MeanTestTime(ms)", "Tps",
            "Order/request", "total orders" };

    public static void main(String[] args) {
        System.out.println("=============start extract ngrinder log ===============");
        ExtractPerfTestLog log = new ExtractPerfTestLog();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("/home/fcw/Desktop/ngrider_log/" + RESULT_FILE_NAME);
            JSONArray json = log.sendPageRequest();
            sort(json);
            log.createXls(json, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        System.out.println("============= end ===============");

    }

    private static void sort(JSONArray json) {
        json.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                JSONObject object1 = JSONObject.parseObject(o1.toString());
                JSONObject object2 = JSONObject.parseObject(o2.toString());

                int result = object1.getInteger(Key_Map.THREAD_COUNT.name)
                        .compareTo(object2.getInteger(Key_Map.THREAD_COUNT.name));
                if (result == 0) {
                    result = object1.getDouble(Key_Map.MEAN_TEST_TIME.name)
                            .compareTo(object2.getDouble(Key_Map.MEAN_TEST_TIME.name));
                }

                return result;
            }
        });
    }

    private JSONObject sendSingleRequest() {
        String authEncoded = Base64.getEncoder().encodeToString(USER_INFO.getBytes());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, "Basic " + authEncoded);
        String response = HttpUtils.doGet(URL, headers);
        response = response.substring(response.indexOf("[") + 1, response.lastIndexOf("]") - 1);
        JSONObject json = JSONObject.parseObject(response);
        return json;
    }

    private JSONArray sendPageRequest() {
        String authEncoded = Base64.getEncoder().encodeToString(USER_INFO.getBytes());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, "Basic " + authEncoded);
        System.out.println("==========send request==========");
        String response = HttpUtils.doGet(PAGE_URL, headers);
        JSONArray json = JSONArray.parseArray(response);
        return json;
    }

    private void createXls(JSONArray json, FileOutputStream outputStream) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(SHEET_NAME);
        int rownum = 0;
        createHeader(sheet, rownum++);
        createOtherRow(sheet, rownum++, json);
        autoResizeColumn(sheet);
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void autoResizeColumn(HSSFSheet sheet) {
        for (int i = 0; i < COLUMNS_HEADER.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void createOtherRow(HSSFSheet sheet, int rownum, JSONArray json) {
        Row row = null;
        JSONObject object = null;
        for (int i = 0; i < json.size(); i++) {
            object = JSONObject.parseObject(json.get(i).toString());
            String status = object.getString(STATUS_KEY);
            if (!FINISHED.equals(status)) {
                continue;
            }

            row = sheet.createRow(rownum++);
            for (Key_Map keyMap : Key_Map.values()) {
                row.createCell(keyMap.index).setCellValue(object.getString(keyMap.name));
            }

            // create total test count
            String testName = object.getString(Key_Map.TEST_NAME.name);
            Integer eachOrdersInRequest = Integer
                    .valueOf(testName.substring(testName.indexOf(BEGIN_CHAR) + 1, testName.indexOf(END_CHAR)));
            row.createCell(COLUMNS_HEADER.length - 1)
                    .setCellValue(object.getInteger(Key_Map.TESTS_COUNT.name) * eachOrdersInRequest);
            // create order count of each request
            row.createCell(COLUMNS_HEADER.length - 2).setCellValue(eachOrdersInRequest);

        }
    }

    private void createHeader(HSSFSheet sheet, int rownum) {
        Row header = sheet.createRow(rownum);
        for (int i = 0; i < COLUMNS_HEADER.length; i++) {
            header.createCell(i).setCellValue(COLUMNS_HEADER[i]);
        }
    }

    enum Key_Map {
        TEST_NAME("testName", 0), //
        THREAD_COUNT("vuserPerAgent", 1), //
        TESTS_COUNT("tests", 2), //
        MEAN_TEST_TIME("meanTestTime", 3), //
        TPS("tps", 4);//
        final int index;
        final String name;

        private Key_Map(String name, int index) {
            this.index = index;
            this.name = name;
        }
    }
}
