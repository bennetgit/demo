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
//
//package spring.demo.exercise.pdf.converter;
//
////import java.awt.Font;
////import java.awt.GraphicsEnvironment;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.io.StringReader;
//import java.lang.ref.Reference;
//import java.lang.ref.SoftReference;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.w3c.dom.CDATASection;
//import org.w3c.dom.Comment;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Text;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import com.lowagie.text.pdf.BaseFont;
//
//import spring.demo.exercise.pdf.converter.CDocumentCut.DocumentAndSize;
//
//;
//
///**
// * This class transform an html document in a PDF.
// *
// * @author Quentin Anciaux
// * @version 0.02
// */
//public final class CHtmlToPdfFlyingSaucerTransformer implements IHtmlToPdfTransformer {
//    private static class _ITextRenderer extends ITextRenderer {
//        private final Map knownFont = new HashMap();
//
//        private void addKnown(final String path) {
//            this.knownFont.put(path, path);
//        }
//
//        private boolean isKnown(final String path) {
//            return this.knownFont.get(path) != null;
//        }
//    }
//
//    private static final Logger log = Logger
//            .getLogger(spring.demo.exercise.pdf.converter.CHtmlToPdfFlyingSaucerTransformer.class);
//
//    private static boolean accept(final File dir, final String name) {
//        return name.toLowerCase().endsWith(".ttf");
//    }
//
//    private static void registerTTF(final File f, final _ITextRenderer renderer) {
//        if (f.isDirectory()) {
//            final File[] list = f.listFiles();
//            if (list != null) {
//                for (int i = 0; i < list.length; i++) {
//                    spring.demo.exercise.pdf.converter.CHtmlToPdfFlyingSaucerTransformer.registerTTF(list[i], renderer);
//                }
//            }
//        } else if (spring.demo.exercise.pdf.converter.CHtmlToPdfFlyingSaucerTransformer.accept(f.getParentFile(),
//                f.getName())) {
//            if (!renderer.isKnown(f.getAbsolutePath())) {
//                try {
//                    renderer.getFontResolver().addFont(f.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//                    renderer.addKnown(f.getAbsolutePath());
//                } catch (final Throwable ignore) {
//                    ignore.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private final ThreadLocal tlparser = new ThreadLocal();
//
//    private final ThreadLocal tlrenderer = new ThreadLocal();
//
//    /**
//     * Creates a new CHtmlToPdfFlyingSaucerTransformer object.
//     */
//    public CHtmlToPdfFlyingSaucerTransformer() {
//    }
//
//    private CShaniDomParser getCShaniDomParser() {
//        final Reference ref = (Reference) this.tlparser.get();
//        final CShaniDomParser parser = (CShaniDomParser) (ref == null ? null : ref.get());
//        if (parser != null) {
//            return parser;
//        }
//        final CShaniDomParser ret = new CShaniDomParser(true, false);
//        ret.setAutodoctype(false);
//        ret.setIgnoreDTD(true);
//        this.tlparser.set(new SoftReference(ret));
//        return ret;
//    }
//
//    private _ITextRenderer getITextRenderer() {
//        final Reference ref = (Reference) this.tlrenderer.get();
//        final _ITextRenderer renderer = (_ITextRenderer) (ref == null ? null : ref.get());
//        if (renderer != null) {
//            return renderer;
//        }
//        final _ITextRenderer ret = new _ITextRenderer();
//        this.tlrenderer.set(new SoftReference(ret));
//        return ret;
//    }
//
//    /**
//     * Transform the html document in the inputstream to a pdf in the
//     * outputstream
//     *
//     * @param in
//     *            html document stream
//     * @param urlForBase
//     *            base url of the document
//     * @param size
//     *            pdf document page size
//     * @param hf
//     *            header-footer list
//     * @param properties
//     *            transform properties
//     * @param out
//     *            out stream to the pdf file
//     */
//    public final void transform(final String html, String urlForBase, final PageSize size, final List hf,
//            final Map properties, final OutputStream out) throws CConvertException {
//        final List files = new ArrayList();
//        try {
//            final CShaniDomParser parser = this.getCShaniDomParser();
//            final _ITextRenderer renderer = this.getITextRenderer();
//            Document theDoc = parser.parse(new StringReader(html));
//            final NodeList styles = theDoc.getElementsByTagName("style");
//            for (int i = 0; i < styles.getLength(); i++) {
//                final Node n = styles.item(i);
//                final StringBuffer style = new StringBuffer();
//                while (n.getChildNodes().getLength() > 0) {
//                    final Node child = n.getChildNodes().item(0);
//                    if (child.getNodeType() == Node.COMMENT_NODE) {
//                        final Comment c = (Comment) child;
//                        style.append(c.getData());
//                    } else if (child.getNodeType() == Node.TEXT_NODE) {
//                        final Text c = (Text) child;
//                        style.append(c.getData());
//                    } else if (child.getNodeType() == Node.CDATA_SECTION_NODE) {
//                        final CDATASection c = (CDATASection) child;
//                        style.append(c.getData());
//                    }
//                    n.removeChild(child);
//                }
//
//                final String content = style.toString().trim();
//                final Text start = theDoc.createTextNode("/*");
//                final CDATASection cd = theDoc.createCDATASection("*/\n" + content + "\n/*");
//                final Text end = theDoc.createTextNode("*/\n");
//                n.appendChild(start);
//                n.appendChild(cd);
//                n.appendChild(end);
//            }
//            final List toRemove = new ArrayList();
//            final NodeList tnl = theDoc.getChildNodes();
//            for (int i = 0; i < tnl.getLength(); i++) {
//                final Node n = tnl.item(i);
//                if (n != theDoc.getDocumentElement()) {
//                    toRemove.add(n);
//                }
//            }
//            final Node title = theDoc.getDocumentElement().getElementsByTagName("title").item(0);
//            if ((title != null) && (properties.get(IHtmlToPdfTransformer.PDF_TITLE) == null)) {
//                properties.put(IHtmlToPdfTransformer.PDF_TITLE, title.getTextContent());
//            }
//
//            Node body = theDoc.getDocumentElement().getElementsByTagName("body").item(0);
//            Node head = theDoc.getDocumentElement().getElementsByTagName("head").item(0);
//            for (int i = 0; i < toRemove.size(); i++) {
//                final Node n = (Node) toRemove.get(i);
//                n.getParentNode().removeChild(n);
//                if (n.getNodeType() == Node.TEXT_NODE) {
//                    final Text t = (Text) n;
//                    if (t.getData().trim().length() == 0) {
//                        continue;
//                    }
//                }
//                if ("link".equals(n.getNodeName()) || "style".equals(n.getNodeName())) {
//                    head.appendChild(n);
//                } else {
//                    body.appendChild(n);
//                }
//            }
//            final DocumentAndSize docs[] = CDocumentCut.cut(theDoc, size);
//            for (int jj = 0; jj < docs.length; jj++) {
//                Document mydoc = docs[jj].doc;
//                body = mydoc.getDocumentElement().getElementsByTagName("body").item(0);
//                head = mydoc.getDocumentElement().getElementsByTagName("head").item(0);
//                try {
//                    String surlForBase = ((Element) mydoc.getElementsByTagName("base").item(0)).getAttribute("href");
//                    if ((surlForBase == null) || "".equals(surlForBase)) {
//                        surlForBase = null;
//                    }
//                    if (surlForBase != null) {
//                        urlForBase = surlForBase;
//                    }
//                } catch (final Exception ignore) {
//                }
//                if (urlForBase != null) {
//                    ((ADocument) mydoc).setDocumentURI(urlForBase);
//                }
//                final NodeList nl = mydoc.getElementsByTagName("base");
//
//                if (nl.getLength() == 0) {
//                    final ADocument doc = (ADocument) mydoc;
//                    final Element base = doc.createElement("base");
//                    base.setAttribute("href", urlForBase);
//
//                    if (head.getFirstChild() != null) {
//                        head.insertBefore(base, head.getFirstChild());
//                    } // end if
//                    else {
//                        head.appendChild(base);
//                    } // end else
//                } else {
//                    final Element base = (Element) nl.item(0);
//                    base.setAttribute("href", urlForBase);
//                }
//
//                final NumberFormat nf = NumberFormat.getInstance(Locale.US);
//                nf.setMaximumFractionDigits(2);
//                nf.setMinimumFractionDigits(0);
//                final Element style = mydoc.createElement("style");
//                style.setAttribute("type", "text/css");
//                final double[] dsize = docs[jj].size.getCMSize();
//                final double[] dmargin = docs[jj].size.getCMMargin();
//                style.setTextContent("\n@page {\n" + "size: " + nf.format(dsize[0] / 2.54) + "in "
//                        + nf.format(dsize[1] / 2.54) + "in;\n" + "margin-left: " + nf.format(dmargin[0] / 2.54)
//                        + "in;\n" + "margin-right: " + nf.format(dmargin[1] / 2.54) + "in;\n" + "margin-bottom: "
//                        + nf.format(dmargin[2] / 2.54) + "in;\n" + "margin-top: " + nf.format(dmargin[3] / 2.54)
//                        + "in;\npadding: 0in;\n" + "}\n"
//
//                );
//                head.appendChild(style);
//                if (properties.get(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH) != null) {
//                    final File fontFile = new File((String) properties.get(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH));
//                    spring.demo.exercise.pdf.converter.CHtmlToPdfFlyingSaucerTransformer.registerTTF(fontFile,
//                            renderer);
//                }
//                ((ADocument) mydoc).setInputEncoding("utf-8");
//                ((ADocument) mydoc).setXmlEncoding("utf-8");
//                renderer.getSharedContext().setBaseURL(urlForBase);
//
//                mydoc = parser.parse(new StringReader(mydoc.toString()));
//                mydoc.getDomConfig().setParameter("entities", Boolean.FALSE);
//                mydoc.normalizeDocument();
//                renderer.setDocument(mydoc, urlForBase);
//                renderer.layout();
//
//                final File f = File.createTempFile("pdf", "yahp");
//                files.add(f);
//                final OutputStream fout = new BufferedOutputStream(new FileOutputStream(f));
//                renderer.createPDF(fout, true);
//                fout.flush();
//                fout.close();
//            }
//            final PageSize[] sizes = new PageSize[docs.length];
//            for (int i = 0; i < docs.length; i++) {
//                sizes[i] = docs[i].size;
//
//            }
//            CDocumentReconstructor.reconstruct(files, properties, out, urlForBase,
//                    "Flying Saucer Renderer (https://xhtmlrenderer.dev.java.net/)", sizes, hf);
//        } catch (final Throwable e) {
//            spring.demo.exercise.pdf.converter.CHtmlToPdfFlyingSaucerTransformer.log.error(e);
//            throw new CConvertException("ERROR: An unhandled exception occured: " + e.getMessage(), e);
//        } finally {
//            try {
//                out.flush();
//            } catch (final Exception ignore) {
//            }
//            for (final Iterator it = files.iterator(); it.hasNext();) {
//                final File f = (File) it.next();
//
//                try {
//                    f.delete();
//                } // end try
//                catch (final Exception ignore) {
//                }
//            } // end for
//        }
//    }
//
//}
