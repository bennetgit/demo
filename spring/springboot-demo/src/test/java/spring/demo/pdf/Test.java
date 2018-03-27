package spring.demo.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

/**
 * A simple example to convert an html file into a PDF.
 * 将此类移到src/main/java, 将字体移到src/main/resources下方能正常测试
 *
 * @author James Zhang
 */
public class Test {
//    private static CYaHPConverter converter = new CYaHPConverter();
//
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    public static void main(final String args[]) throws Exception {
//        String outfile = "/home/j3z/123.pdf";
//
//        String baseFolder = "/home/j3z/Downloads/YaHP-Converter/";
//        String fontPath = "/font/simhei.ttf";
//
//        try {
//            String content = readTxtFile(baseFolder + "YaHPConverter/test/123.html");
//            File fout = new File(outfile);
//
//            FileOutputStream out = new FileOutputStream(fout);
//            System.out.println("before conversion");
//
//            Map properties = new HashMap();
//            if (fontPath != null) {
//                properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
//            }
//            properties.put(IHtmlToPdfTransformer.PDF_CREATOR, "J3z");
//            properties.put(IHtmlToPdfTransformer.PDF_PRODUCER, "oTMS");
//
//            converter.convertToPdf(content, IHtmlToPdfTransformer.A4P, out, properties);
//            System.out.println("after conversion");
//            out.flush();
//            out.close();
//        } // end try
//        catch (final Throwable t) {
//            t.printStackTrace();
//            System.exit(-1);
//        } // end catch
//
//        System.exit(0);
//    } // end main()
//
//    public static String readTxtFile(String filePath) {
//        try {
//            StringBuilder sb = new StringBuilder();
//            String encoding = "UTF-8";
//            File file = new File(filePath);
//            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
//            BufferedReader bufferedReader = new BufferedReader(read);
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//            read.close();
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

} // end CSimpleConversion
