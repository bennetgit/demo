package spring.demo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.demo.util.helper.ExcelHelper;

/**
 * Created by wangfacheng on 2017-11-09.
 */
public class OTherTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OTherTest.class);

    private static final String REGEX_PATTERN_START = "\"\"\"\"";
    private static final String REGEX_PATTERN_END = "\"";

    private static final String INVALID_RECORD_PATTERN = "///";

    private static final String FIELD_NAME_PREFIX = "field_name_";
    private static final String FIELD_PHONE_PREFIX = "field_phone_";
    private static final String BLANK_PREFIX = "field_blank_";

    private static final String[] FIELDS = { FIELD_NAME_PREFIX + "1", FIELD_PHONE_PREFIX + "2", "field_blank_3",
            "field_blank_4", FIELD_NAME_PREFIX + "5", FIELD_PHONE_PREFIX + "6", "field_blank_7", "field_blank_8",
            FIELD_NAME_PREFIX + "9", FIELD_PHONE_PREFIX + "10", "field_blank_11", "field_blank_12",
            FIELD_NAME_PREFIX + "13", FIELD_PHONE_PREFIX + "14", "field_blank_15", "field_blank_16",
            FIELD_NAME_PREFIX + "17", FIELD_PHONE_PREFIX + "18", "field_blank_19", "field_blank_20",
            FIELD_NAME_PREFIX + "21", FIELD_PHONE_PREFIX + "22", "field_blank_23", "field_blank_24",
            FIELD_NAME_PREFIX + "25", FIELD_PHONE_PREFIX + "26" };

    private static final List<String> UN_NEED_RECORDS = new ArrayList<String>() {
        {
            add("徐远远");
            add("徐乐然");
            add("徐先生");
            add("徐正华");
        }
    };

    @Ignore
    @Test
    public void test() throws Exception {

        List<List<Object>> results = ExcelHelper.readExcel("/home/facheng/Downloads/11_report.xlsx");

        Set<TestUserInfo> userInfos = results.stream().skip(1)
                .filter(r -> !r.toString().contains(INVALID_RECORD_PATTERN) && isNeed(r.toString()))
                .map(r -> convert(r)).collect(Collectors.toSet());

        Set<TestUserInfo> unUserInfos = results.stream().skip(1)
                .filter(r -> r.toString().contains(INVALID_RECORD_PATTERN) && isNeed(r.toString())).map(r -> convert(r))
                .collect(Collectors.toSet());

        String validFilePath = "/home/facheng/backup/excel/convert_report.xlsx";
        String unValidFilePath = "/home/facheng/backup/excel/convert_report_un.xlsx";

        export(new ArrayList<>(userInfos), validFilePath);
        export(new ArrayList<>(unUserInfos), unValidFilePath);

        System.out.println("xxxxx user size -->" + userInfos.size());
        System.out.println("xxxxx un valid user size -->" + unUserInfos.size());

    }

    private boolean isNeed(String record) {
        return UN_NEED_RECORDS.stream().allMatch(t -> !record.contains(t));
    }

    private void export(List<TestUserInfo> userInfos, String exportPath) throws Exception {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> rowData = new LinkedHashMap<>();

        int index = 0;
        int fieldLength = FIELDS.length;
        String fieldFirst;
        String fieldSec;
        for (TestUserInfo tu : userInfos) {

            if ((index % fieldLength) == 0) {
                index = 0;
                rowData = new LinkedHashMap<>();
                dataList.add(rowData);
            }

            fieldFirst = FIELDS[index++];
            fieldSec = FIELDS[index++];
            if (fieldFirst.startsWith(FIELD_NAME_PREFIX)) {
                rowData.put(fieldFirst, tu.getUsername());
            }

            if (fieldSec.startsWith(FIELD_PHONE_PREFIX)) {
                rowData.put(fieldSec, tu.getPhone());
            }

            if (fieldFirst.startsWith(BLANK_PREFIX)) {
                rowData.put(fieldFirst, StringUtils.EMPTY);
            }

            if (fieldSec.startsWith(BLANK_PREFIX)) {
                rowData.put(fieldSec, StringUtils.EMPTY);

                // plus two column
                fieldFirst = FIELDS[index++];
                fieldSec = FIELDS[index++];
                if (fieldFirst.startsWith(FIELD_NAME_PREFIX)) {
                    rowData.put(fieldFirst, tu.getUsername());
                }

                if (fieldSec.startsWith(FIELD_PHONE_PREFIX)) {
                    rowData.put(fieldSec, tu.getPhone());
                }
            }

        }

        ExcelHelper.createExcel(exportPath, new String[] {}, FIELDS, dataList);
    }

    private TestUserInfo convert(List<Object> row) {

        String row1 = StringUtils.replace(String.valueOf(row.get(0)), "\t", "");
        int customerNameStartIndex = row1.indexOf(REGEX_PATTERN_START) + 5;
        int customerNameEndIndex = row1.indexOf(REGEX_PATTERN_END, customerNameStartIndex);
        int phoneNumberStartIndex = customerNameEndIndex + 1;
        int phoneNumberEndIndex = row1.indexOf(REGEX_PATTERN_END, customerNameEndIndex + 2);

        return new TestUserInfo(row1.substring(customerNameStartIndex, customerNameEndIndex),
                row1.substring(phoneNumberStartIndex, phoneNumberEndIndex));
    }

    class TestUserInfo {
        private String username;
        private String phone;

        public TestUserInfo(String username, String phone) {
            this.username = username;
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone() {
            return phone;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            TestUserInfo that = (TestUserInfo) o;

            if (username != null ? !username.equals(that.username) : that.username != null)
                return false;
            return phone != null ? phone.equals(that.phone) : that.phone == null;
        }

        @Override
        public int hashCode() {
            int result = username != null ? username.hashCode() : 0;
            result = 31 * result + (phone != null ? phone.hashCode() : 0);
            return result;
        }
    }

    @Ignore
    @Test
    public void createFile() throws ClassNotFoundException, InterruptedException {

        Class.forName(ClassLoaderTest.class.getName());
        System.out.println("测试" + ClassLoaderTest.class.getName());
    }

    @Test
    public void log4jTest() {
        LOGGER.info("hello world");
    }
}
