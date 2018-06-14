//package spring.demo.exercise.pdf;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapperBuilder;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.allcolor.yahp.converter.CYaHPConverter;
//
///**
// * Created by wangfacheng on 2018-04-26.
// */
//public class MyTest {
//
//    private static CYaHPConverter converter = new CYaHPConverter();
//
//    public void test() throws IOException, TemplateException, IHtmlToPdfTransformer.CConvertException {
//
//        FileOutputStream out = new FileOutputStream(new File("/home/facheng/backup/pdf/123.pdf"));
//
//        String fontPath = getClass().getResource("/fonts").getFile();
//        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
//        config.setDirectoryForTemplateLoading(new File(getClass().getResource("/").getFile()));
//        config.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23).build());
//        Template template = config.getTemplate("test.html", "UTF-8");
//        Template footTemplate = config.getTemplate("foot.html", "UTF-8");
//
//        StringWriter footWriter = new StringWriter();
//        footTemplate.process(null, footWriter);
//        Map properties = new HashMap();
//        properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, fontPath);
//
//        List headerFooterList = new ArrayList();
//        headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter(footWriter.toString(),
//                IHtmlToPdfTransformer.CHeaderFooter.FOOTER));
//
//        StringWriter writer = new StringWriter();
//
//        List<Integer> test = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            test.add(i);
//        }
//
//        Map data = new HashMap();
//
//        int listSize = test.size();
//        System.out.println("xxxx -> " + ((listSize - 45) % 70));
//        boolean needBreak = listSize > 15 && (listSize < 45 || ((listSize - 45) % 70 > 35));
//
//        System.out.println("needBreak --> " + needBreak);
//
//        data.put("data", new Data(test, needBreak));
//
//        template.process(data, writer);
//
//        converter.convertToPdf(writer.toString(), null, IHtmlToPdfTransformer.A4P, headerFooterList, out, properties);
//    }
//
//    public static class Data {
//        List test;
//        boolean isBreak;
//
//        public Data(List test, boolean isBreak) {
//            this.test = test;
//            this.isBreak = isBreak;
//        }
//
//        public List getTest() {
//            return test;
//        }
//
//        public void setTest(List test) {
//            this.test = test;
//        }
//
//        public boolean isBreak() {
//            return isBreak;
//        }
//
//        public void setBreak(boolean aBreak) {
//            isBreak = aBreak;
//        }
//    }
//}
