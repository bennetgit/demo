package spring.demo.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;

public class PdfTest {

    private static CYaHPConverter converter = new CYaHPConverter(false);

    @Test
    public void createPdfTest() throws Exception {



        System.exit(0);

    } // end main()

    public static String readTxtFile(String filePath) {
        try {
            StringBuilder sb = new StringBuilder();
            String encoding = "UTF-8";
            File file = new File(filePath);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            read.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

} // end CSimpleConversion
