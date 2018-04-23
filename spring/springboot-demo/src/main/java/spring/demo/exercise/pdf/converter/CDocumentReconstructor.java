///*
// * Copyright (C) 2007 by Quentin Anciaux
// *
// * This library is free software; you can redistribute it and/or modify it
// * under the terms of the GNU Library General Public License as published by
// * the Free Software Foundation; either version 2 of the License, or (at your
// * option) any later version.
// *
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Library General Public License
// * for more details.
// *
// * You should have received a copy of the GNU Library General Public License
// * along with this library; if not, write to the Free Software Foundation,
// * Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
// *
// *    @author Quentin Anciaux
// */
//package spring.demo.exercise.pdf.converter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import spring.demo.exercise.pdf.converter.IHtmlToPdfTransformer;
//import spring.demo.exercise.pdf.converter.IHtmlToPdfTransformer.CConvertException;
//import spring.demo.exercise.pdf.converter.IHtmlToPdfTransformer.PageSize;
//
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Meta;
//import com.lowagie.text.pdf.PdfCopy;
//import com.lowagie.text.pdf.PdfImportedPage;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfStamper;
//import com.lowagie.text.pdf.PdfWriter;
//
///**
// * Use iText to construct a complete pdf document from differents pdf parts.
// * Apply configured security policies on the resulting pdf.
// *
// * @author Quentin Anciaux
// * @version 1.2.20b
// */
//public class CDocumentReconstructor {
//
//    /**
//     * construct a pdf document from pdf parts.
//     *
//     * @param files
//     *            list containing the pdf to assemble
//     * @param properties
//     *            converter properties
//     * @param fout
//     *            outputstream to write the new pdf
//     * @param base_url
//     *            base url of the document
//     * @param producer
//     *            producer of the pdf
//     *
//     * @throws CConvertException
//     *             if an error occured while reconstruct.
//     */
//    public static void reconstruct(final List<File> files, final Map properties, final OutputStream fout,
//            final String base_url, final String producer, final PageSize[] size, final List hf)
//            throws CConvertException {
//        OutputStream out = fout;
//        OutputStream out2 = fout;
//        File tmp = null;
//        File tmp2 = null;
//        try {
//            tmp = File.createTempFile("yahp", "pdf");
//            tmp2 = File.createTempFile("yahp", "pdf");
//            out2 = out;
//            out = new FileOutputStream(tmp);
//            com.lowagie.text.Document document = null;
//            PdfCopy writer = null;
//            boolean first = true;
//
//            Map<String, String> mapSizeDoc = new HashMap<>();
//
//            int totalPage = 0;
//
//            for (int i = 0; i < files.size(); i++) {
//                final File fPDF = (File) files.get(i);
//                final PdfReader reader = new PdfReader(fPDF.getAbsolutePath());
//                reader.consolidateNamedDestinations();
//
//                final int n = reader.getNumberOfPages();
//
//                if (first) {
//                    first = false;
//                    // step 1: creation of a document-object
//                    // set title/creator/author
//                    document = new com.lowagie.text.Document(reader.getPageSizeWithRotation(1));
//                    // step 2: we create a writer that listens to the document
//                    writer = new PdfCopy(document, out);
//                    writeDescription(document, writer, properties, base_url, producer);
//
//                    // step 3: we open the document
//                    document.open();
//                } // end if
//
//                PdfImportedPage page;
//
//                for (int j = 0; j < n;) {
//                    ++j;
//                    totalPage++;
//                    mapSizeDoc.put("" + totalPage, "" + i);
//                    page = writer.getImportedPage(reader, j);
//                    writer.addPage(page);
//                } // end for
//            } // end for
//
//            document.close();
//            out.flush();
//            out.close();
//
//            final PdfReader reader = new PdfReader(tmp.getAbsolutePath());
//            final PdfStamper stp = new PdfStamper(reader, out2);
//            stp.close();
//
//            try {
//                out2.flush();
//            } catch (Exception ignore) {
//            } finally {
//                try {
//                    out2.close();
//                } catch (Exception ignore) {
//                }
//            }
//        } // end try
//        catch (final Exception e) {
//            throw new CConvertException(
//                    "ERROR: An Exception occured while reconstructing the pdf document: " + e.getMessage(), e);
//        } // end catch
//        finally {
//            try {
//                tmp.delete();
//            } // end try
//            catch (final Exception ignore) {
//            }
//            try {
//                tmp2.delete();
//            } // end try
//            catch (final Exception ignore) {
//            }
//        } // end finally
//    } // end reconstruct()
//
//    protected static void writeDescription(com.lowagie.text.Document document, PdfCopy writer, final Map properties,
//            final String base_url, final String producer) throws DocumentException {
//        // use pdf version 1.5
//        writer.setPdfVersion(PdfWriter.VERSION_1_3);
//        // compress the pdf
//        writer.setFullCompression();
//
//        final String title = (String) properties.get(IHtmlToPdfTransformer.PDF_TITLE);
//
//        if (title != null) {
//            document.addTitle(title);
//        } // end if
//        else if (base_url != null) {
//            document.addTitle(base_url);
//        } // end else if
//
//        final String creator = (String) properties.get(IHtmlToPdfTransformer.PDF_CREATOR);
//
//        if (creator != null) {
//            document.addCreator(creator);
//        } // end if
//        else {
//            document.addCreator(IHtmlToPdfTransformer.VERSION);
//        } // end else
//
//        final String author = (String) properties.get(IHtmlToPdfTransformer.PDF_AUTHOR);
//
//        if (author != null) {
//            document.addAuthor(author);
//        } // end if
//
//        final String sproducer = (String) properties.get(IHtmlToPdfTransformer.PDF_PRODUCER);
//
//        if (sproducer != null) {
//            document.add(new Meta("Producer", sproducer));
//        } else {
//            document.add(new Meta("Producer", IHtmlToPdfTransformer.VERSION));
//        }
//    }
//
//} // end CDocumentReconstructor
