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

        String outfile = "/home/facheng/backup/pdf/123.pdf";
        // String fontPath =
        // "/home/facheng/source/fc/demo/spring/springboot-demo/src/test/resources/font";
        String fontPath = getClass().getResource("/font").getFile();

        /// simhei.ttf

        Configuration config = new Configuration(Configuration.VERSION_2_3_23);

        // 设置要解析的模板所在的目录，并加载模板文件
        config.setDirectoryForTemplateLoading(
                new File("/home/facheng/source/fc/demo/spring/springboot-demo/src/test/resources/"));
        // 设置包装器，并将对象包装为数据模型
        config.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23).build());
        // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
        // 否则会出现乱码
        Template template = config.getTemplate("123.html", "UTF-8");
        // 合并数据模型与模板
        StringWriter writer = new StringWriter();

        Map<String, Object> dataMap = new HashMap<>();

        PdfPrintModel model = PdfPrintModel.emptyInstance();
        model.withName("wangfacheng");
        model.withOtmsLogPath(getClass().getResource("/otmspdflogo.png").getFile());

        dataMap.put("data", model);
        template.process(dataMap, writer);
        System.out.println(writer.toString());

        try {
            // String content = readTxtFile(
            // "/home/facheng/source/fc/demo/spring/springboot-demo/src/test/resources/123.html");
            File fout = new File(outfile);

            FileOutputStream out = new FileOutputStream(fout);
            System.out.println("before conversion");

            Map properties = new HashMap();
            if (fontPath != null) {
                properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
            }
            properties.put(IHtmlToPdfTransformer.PDF_CREATOR, "J3z");
            properties.put(IHtmlToPdfTransformer.PDF_PRODUCER, "oTMS");

            converter.convertToPdf(writer.toString(), IHtmlToPdfTransformer.A4P, Collections.EMPTY_LIST, null, out,
                    properties);
            System.out.println("after conversion");
            out.flush();
            out.close();
        } // end try
        catch (final Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        } // end catch

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
