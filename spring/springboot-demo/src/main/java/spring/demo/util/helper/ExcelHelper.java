package spring.demo.util.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by facheng on 17-12-4.
 */
public final class ExcelHelper {

    /**
     * 要求excel版本在2007以上
     *
     * @param file
     *            文件信息
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("找不到文件");
        }
        List<List<Object>> list = new LinkedList<>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                Object value = null;
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                switch (cell.getCellTypeEnum()) {
                case STRING:
                    // String类型返回String数据
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    // 日期数据返回LONG类型的时间戳
                    // if
                    // ("yyyy\"年\"m\"月\"d\"日\";@".equals(cell.getCellStyle().getDataFormatString()))
                    // {
                    // //
                    // System.out.println(cell.getNumericCellValue()+":日期格式："+cell.getCellStyle().getDataFormatString());
                    // value =
                    // DateUtils.getMillis(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) /
                    // 1000;
                    // } else {
                    // 数值类型返回double类型的数字
                    // System.out.println(cell.getNumericCellValue()+":格式："+cell.getCellStyle().getDataFormatString());
                    value = cell.getNumericCellValue();
                    // }
                    break;
                case BOOLEAN:
                    // 布尔类型
                    value = cell.getBooleanCellValue();
                    break;
                case BLANK:
                    // 空单元格
                    break;
                default:
                    value = cell.toString();
                }
                if (value != null && !value.equals("")) {
                    // 单元格不为空，则加入列表
                    linked.add(value);
                }
            }
            if (linked.size() != 0) {
                list.add(linked);
            }
        }
        return list;
    }

    /**
     * 导出excel
     * 
     * @param excel_name
     *            导出的excel路径（需要带.xlsx)
     * @param headList
     *            excel的标题备注名称
     * @param fieldList
     *            excel的标题字段（与数据中map中键值对应）
     * @param dataList
     *            excel数据
     * @throws Exception
     */
    public static void createExcel(String excel_name, String[] headList, String[] fieldList,
            List<Map<String, Object>> dataList) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        // 设置excel头（第一行）的头名称
        for (int i = 0; i < headList.length; i++) {

            XSSFCell cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(headList[i]);
        }
        // 添加数据
        for (int n = 0; n < dataList.size(); n++) {
            // 在索引1的位置创建行（最顶端的行）
            XSSFRow row_value = sheet.createRow(n + 1);
            Map<String, Object> dataMap = dataList.get(n);
            for (int i = 0; i < fieldList.length; i++) {

                XSSFCell cell = row_value.createCell(i);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(escapeNull(dataMap.get(fieldList[i])));
            }
        }

        try (FileOutputStream fos = new FileOutputStream(excel_name)) {
            // 把相应的Excel 工作簿存盘
            workbook.write(fos);
            fos.flush();
            // 操作结束，关闭文件
            fos.close();
        }

    }

    private static String escapeNull(Object o) {
        if (o == null) {
            return StringUtils.EMPTY;
        }

        return o.toString();
    }
}
