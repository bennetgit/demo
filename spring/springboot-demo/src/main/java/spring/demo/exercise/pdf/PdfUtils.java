package spring.demo.exercise.pdf;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.hibernate.sql.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * Created by wangfacheng on 2018-03-27.
 */
public class PdfUtils {

//    public static void main(String[] args) {
//
//
//
//        String fileName = "POD_" + pods.get(0).getShipmentNo() + ".pdf";
//        resp.setContentType("application/pdf;charset=UTF-8");
//        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//        PodPdfUtils.buildSingleLocationPdf(resp, list);
//    }
//
//    public static void buildPodPdf(Template template, HttpServletResponse resp, List<PodPrintDto> datas)
//            throws Exception {
//        logger.info("before conversion");
//        //freemarker process and generate html
//        StringWriter writer = new StringWriter();
//        template.process(new PodTemplateDataModel(datas, PropertyConfig.getContextProperty("pod.logo.filepath")),
//                writer);
//        // html to pdf
//        OutputStream out = resp.getOutputStream();
//        String html = writer.toString();
//        converter.convertToPdf(html, IHtmlToPdfTransformer.A4P, out,
//                getProperties());
//        out.flush();
//        logger.info("after conversion");
//    }
}
