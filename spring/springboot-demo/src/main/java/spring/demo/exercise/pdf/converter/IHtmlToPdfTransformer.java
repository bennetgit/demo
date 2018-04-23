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
//import java.io.OutputStream;
//import java.util.List;
//import java.util.Map;
//
///**
// * Interface for an html to pdf transformer.
// *
// * @author Quentin Anciaux
// * @version 1.2.17
// */
//public interface IHtmlToPdfTransformer {
//    /**
//     * An exception indicating a conversion error.
//     *
//     * @author Quentin Anciaux
//     * @version 0.1
//     */
//    public static class CConvertException extends Exception {
//        /** serialVersionUID */
//        static final long serialVersionUID = -6405771637273004347L;
//
//        /**
//         * Creates a new CConvertException object.
//         *
//         * @param message
//         *            Error message
//         * @param cause
//         *            DOCUMENT ME!
//         */
//        public CConvertException(final String message, final Throwable cause) {
//            super(message, cause);
//        } // end CConvertException()
//    } // end CConvertException
//
//    /**
//     * Represent a Header or a Footer
//     *
//     * @author Quentin Anciaux
//     * @version 0.3
//     */
//    public static class CHeaderFooter {
//        /** header/footer constant */
//        public static final String ALL_PAGES = "all";
//
//        /** header/footer constant */
//        public static final String EVEN_PAGES = "even";
//
//        /** header/footer constant */
//        public static final String FOOTER = "footer";
//
//        /** header/footer constant */
//        public static final String HEADER = "header";
//
//        /** header/footer constant */
//        public static final String ODD_PAGES = "odd";
//
//        /** html content of the element */
//        private final String content;
//
//        /** for : even, odd or all */
//        private final String sfor;
//
//        /**
//         * type: header or footer
//         */
//        private String type;
//
//        /**
//         * create a new CHeaderFooter
//         *
//         * @param content
//         *            html content of the element
//         * @param type
//         *            header or footer
//         */
//        public CHeaderFooter(final String content, final String type) {
//            this(content, CHeaderFooter.HEADER, CHeaderFooter.ALL_PAGES);
//            this.type = type;
//            if (!type.equals(CHeaderFooter.HEADER) && !type.equals(CHeaderFooter.FOOTER)) {
//                throw new IllegalArgumentException("type must be : [header|footer].");
//            } // end if
//        } // end CHeaderFooter()
//
//        /**
//         * create a new CHeaderFooter
//         *
//         * @param content
//         *            html content of the element
//         * @param type
//         *            header or footer
//         * @param sfor
//         *            even, odd or all
//         *
//         * @throws IllegalArgumentException
//         *             if type or header have invalid value
//         */
//        public CHeaderFooter(final String content, final String type, final String sfor) {
//            this.content = content;
//            this.type = type;
//            if (!type.equals(CHeaderFooter.HEADER) && !type.equals(CHeaderFooter.FOOTER)) {
//                throw new IllegalArgumentException("type must be : [header|footer].");
//            } // end if
//
//            if (!sfor.equals(CHeaderFooter.ALL_PAGES) && !sfor.equals(CHeaderFooter.ODD_PAGES)
//                    && !sfor.equals(CHeaderFooter.EVEN_PAGES)) {
//                throw new IllegalArgumentException("for is either : all,even or odd.");
//            } // end if
//
//            this.sfor = sfor;
//        } // end CHeaderFooter()
//
//        /**
//         * html content of the element
//         *
//         * @return html content of the element
//         */
//        public final String getContent() {
//            return this.content;
//        } // end getContent()
//
//        /**
//         * even, odd or all
//         *
//         * @return even, odd or all
//         */
//        public final String getSfor() {
//            return this.sfor;
//        } // end getSfor()
//
//        /**
//         * type: header or footer
//         *
//         * @return header or footer type
//         */
//        public final String getType() {
//            return this.type;
//        } // end getType()
//    } // end CHeaderFooter
//
//    /**
//     * A class to setup the size of a pdf page.
//     *
//     * @author Quentin Anciaux
//     * @version 0.1
//     */
//    public static final class PageSize {
//        /** 1 centimeter is screenresolution in DPI/1 inch (2.54 cm) pixels */
//        public static final double _1CMPIXEL = PageSize.getScreenResolution();
//
//        private static final double getScreenResolution() {
//            return 72.0d / 2.54d;
//        }
//
//        /** page bottom margin in pixel */
//        private final int bmargin;
//
//        /** page bottom margin in centimeter */
//        private final double cmbmargin;
//
//        /** page height in centimeter */
//        private final double cmheight;
//
//        /** page left margin in centimeter */
//        private final double cmlmargin;
//
//        /**
//         * index 0 page left margin in centimeter index 1 page right margin in
//         * centimeter index 2 page bottom margin in centimeter index 3 page top
//         * margin in centimeter
//         */
//        private final double cmmargin[] = new double[4];
//
//        /** page right margin in centimeter */
//        private final double cmrmargin;
//
//        /** page top margin in centimeter */
//        private final double cmtmargin;
//
//        /**
//         * index 0 page width in centimeter index 1 page height in centimeter
//         */
//        private final double cmwh[] = new double[2];
//
//        /** page width in centimeter */
//        private final double cmwidth;
//
//        /** page height in pixel */
//        private final int height;
//
//        /** page left margin in pixel */
//        private final int lmargin;
//
//        /**
//         * index 0 page left margin in pixel index 1 page right margin in pixel
//         * index 2 page bottom margin in pixel index 3 page top margin in pixel
//         */
//        private final int margin[] = new int[4];
//
//        /** page right margin in pixel */
//        private final int rmargin;
//
//        /** page top margin in pixel */
//        private final int tmargin;
//
//        /** index 0 page width in pixel index 1 page height in pixel */
//        private final int wh[] = new int[2];
//
//        /** page width in pixel */
//        private final int width;
//
//        /**
//         * Creates a new PageSize object.
//         *
//         * @param width
//         *            page width in centimeter
//         * @param height
//         *            page height in centimeter
//         */
//        public PageSize(final double width, final double height) {
//            this(width, height, 0, 0, 0, 0);
//        } // end PageSize()
//
//        /**
//         * Creates a new PageSize object.
//         *
//         * @param width
//         *            page width in centimeter
//         * @param height
//         *            page height in centimeter
//         * @param margin
//         *            page margin in centimeter
//         */
//        public PageSize(final double width, final double height, final double margin) {
//            this(width, height, margin, margin, margin, margin);
//        } // end PageSize()
//
//        /**
//         * Creates a new PageSize object.
//         *
//         * @param width
//         *            page width in centimeter
//         * @param height
//         *            page height in centimeter
//         * @param lmargin
//         *            page left margin in centimeter
//         * @param rmargin
//         *            page right margin in centimeter
//         * @param bmargin
//         *            page bottom margin in centimeter
//         * @param tmargin
//         *            page top margin in centimeter
//         */
//        public PageSize(final double width, final double height, final double lmargin, final double rmargin,
//                final double bmargin, final double tmargin) {
//            this((int) Math.round(PageSize._1CMPIXEL * width), (int) Math.round(PageSize._1CMPIXEL * height),
//                    (int) Math.round(PageSize._1CMPIXEL * lmargin), (int) Math.round(PageSize._1CMPIXEL * rmargin),
//                    (int) Math.round(PageSize._1CMPIXEL * bmargin), (int) Math.round(PageSize._1CMPIXEL * tmargin));
//        } // end PageSize()
//
//        /**
//         * Creates a new PageSize object.
//         *
//         * @param width
//         *            page width in pixel
//         * @param height
//         *            page height in pixel
//         */
//        public PageSize(final int width, final int height) {
//            this(width, height, 0, 0, 0, 0);
//        } // end PageSize()
//
//        /**
//         * Creates a new PageSize object.
//         *
//         * @param width
//         *            page width in pixel
//         * @param height
//         *            page height in pixel
//         * @param margin
//         *            page margin in pixel
//         */
//        public PageSize(final int width, final int height, final int margin) {
//            this(width, height, margin, margin, margin, margin);
//        } // end PageSize()
//
//        /**
//         * Creates a new PageSize object.
//         *
//         * @param width
//         *            page width in pixel
//         * @param height
//         *            page height in pixel
//         * @param lmargin
//         *            page left margin in pixel
//         * @param rmargin
//         *            page right margin in pixel
//         * @param bmargin
//         *            page bottom margin in pixel
//         * @param tmargin
//         *            page top margin in pixel
//         *
//         * @throws AssertionError
//         *             if margin or width or height invalid
//         */
//        public PageSize(final int width, final int height, final int lmargin, final int rmargin, final int bmargin,
//                final int tmargin) {
//            if ((width < 0) || ((height < 0) || (lmargin < 0) || (rmargin < 0) || (bmargin < 0) || (tmargin < 0))) {
//                throw new AssertionError("Margin / Width / height invalid, aborting. - lmargin: " + lmargin
//                        + " - rmargin: " + rmargin + " - bmargin: " + bmargin + " - tmargin: " + tmargin + " - w: "
//                        + width + " - h: " + height);
//            } // end if
//
//            this.width = width;
//            this.height = height;
//            this.cmwidth = width / PageSize._1CMPIXEL;
//            this.cmheight = height / PageSize._1CMPIXEL;
//            this.lmargin = lmargin;
//            this.rmargin = rmargin;
//            this.bmargin = bmargin;
//            this.tmargin = tmargin;
//            this.cmlmargin = lmargin / PageSize._1CMPIXEL;
//            this.cmrmargin = rmargin / PageSize._1CMPIXEL;
//            this.cmbmargin = bmargin / PageSize._1CMPIXEL;
//            this.cmtmargin = tmargin / PageSize._1CMPIXEL;
//        } // end PageSize()
//
//        /**
//         * index 0 page left margin in centimeter index 1 page right margin in
//         * centimeter index 2 page bottom margin in centimeter index 3 page top
//         * margin in centimeter
//         *
//         * @return double array size 4
//         */
//        public double[] getCMMargin() {
//            this.cmmargin[0] = this.cmlmargin;
//            this.cmmargin[1] = this.cmrmargin;
//            this.cmmargin[2] = this.cmbmargin;
//            this.cmmargin[3] = this.cmtmargin;
//
//            return this.cmmargin;
//        } // end getCMMargin()
//
//        /**
//         * index 0 page width in centimeter index 1 page height in centimeter
//         *
//         * @return double array size 2
//         */
//        public double[] getCMSize() {
//            this.cmwh[0] = this.cmwidth;
//            this.cmwh[1] = this.cmheight;
//
//            return this.cmwh;
//        } // end getCMSize()
//
//        /**
//         * index 0 page left margin in pixel index 1 page right margin in pixel
//         * index 2 page bottom margin in pixel index 3 page top margin in pixel
//         *
//         * @return int array size 4
//         */
//        public int[] getMargin() {
//            this.margin[0] = this.lmargin;
//            this.margin[1] = this.rmargin;
//            this.margin[2] = this.bmargin;
//            this.margin[3] = this.tmargin;
//
//            return this.margin;
//        } // end getMargin()
//
//        /**
//         * index 0 page width in pixel index 1 page height in pixel
//         *
//         * @return int array size 2
//         */
//        public int[] getSize() {
//            this.wh[0] = this.width;
//            this.wh[1] = this.height;
//
//            return this.wh;
//        } // end getSize()
//    } // end PageSize
//
//    /** A4 portrait size */
//    public static final PageSize A4P = new PageSize(20.8d, 29.6d, 1d); // 1184
//                                                                       // x
//                                                                       // 0832
//                                                                       // pixels
//                                                                       // (029.6
//                                                                       // x
//                                                                       // 020.8
//                                                                       // cm)
//                                                                       // (diagonal
//                                                                       // 36.3
//                                                                       // cm or
//                                                                       // 15
//                                                                       // inch)
//
//    /** Default PDF renderer class, use flying sauce to render HTML. */
//    public static final String DEFAULT_PDF_RENDERER = spring.demo.exercise.pdf.converter.IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER;
//
//    /** PDF renderer class, use Flying Saucer Project to render HTML. */
//    public static final String FLYINGSAUCER_PDF_RENDERER = "org.allcolor.yahp.cl.converter.CHtmlToPdfFlyingSaucerTransformer";
//
//    /** Path to TTF font for embedding with FOP */
//    public static final String FOP_TTF_FONT_PATH = "yahp.FOP_TTF_FONT_PATH";
//
//    /** Set this property to set the pdf author metadata. */
//    public static final String PDF_AUTHOR = "yahp.PDF_AUTHOR";
//
//    /** Set this property to set the pdf creator metadata. */
//    public static final String PDF_CREATOR = "yahp.PDF_CREATOR";
//
//    /** Set this property to set the pdf producer metadata. */
//    public static final String PDF_PRODUCER = "yahp.PDF_PRODUCER";
//
//    /** name of a custom pdf renderer class. */
//    public static final String PDF_RENDERER_CLASS = "yahp.PDF_RENDERER_CLASS";
//
//    /** Set this property to set the pdf title metadata. */
//    public static final String PDF_TITLE = "yahp.PDF_TITLE";
//
//    /** YaHP Converter version */
//    public static final String VERSION = "www.otms.com";
//
//    /**
//     * Transform the html document in the inputstream to a pdf in the
//     * outputstream
//     *
//     * @param html
//     *            html source code
//     * @param urlForBase
//     *            base url of the document
//     * @param size
//     *            pdf document page size
//     * @param hf
//     *            header-footer list
//     * @param properties
//     *            pdf renderer properties list
//     * @param out
//     *            out stream to the pdf file precondition: in != null
//     *            precondition: urlForBase != null precondition: size != null
//     *            precondition: != null precondition: properties != null
//     *            precondition: out != null
//     *
//     * @throws CConvertException
//     *             if a conversion exception occurs
//     */
//    public void transform(final String html, final String urlForBase, final PageSize size, final List hf,
//            final Map properties, final OutputStream out) throws CConvertException;
//} // end IHtmlToPdfTransformer
