/*
 * Copyright (C) 2007 by Quentin Anciaux
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Library General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Library General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *    @author Quentin Anciaux
 */
package spring.demo.exercise.pdf.converter;

import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spring.demo.exercise.pdf.converter.CHtmlToPdfFlyingSaucerTransformer;
import spring.demo.exercise.pdf.converter.IHtmlToPdfTransformer;
import spring.demo.exercise.pdf.converter.IHtmlToPdfTransformer.CConvertException;
//import org.apache.log4j.PropertyConfigurator;

/**
 * This class convert an HTML input to a PDF.
 *
 * @author Quentin Anciaux
 * @version 0.94
 */
public class CYaHPConverter {
    static {
        try {
            System.setProperty("file.encoding", "utf-8");
        } catch (Exception ignore) {
        }
    }

    private IHtmlToPdfTransformer transformer = new CHtmlToPdfFlyingSaucerTransformer();

    /**
     * Creates a new CYaHPConverter object.
     */
    public CYaHPConverter() {

    } // end CYaHPConverter()

    public final void convertToPdf(final String html, final IHtmlToPdfTransformer.PageSize size, final OutputStream out,
            final Map fproperties) throws CConvertException {
        convertToPdf(html, null, size, Collections.emptyList(), out, fproperties);
    }

    /**
     * Convert the document in content in a PDF file. This method is thread
     * safe.
     *
     * @param content
     *            the html document as a string
     * @param size
     *            PDF Page size
     * @param hf
     *            header-footer list
     * @param furlForBase
     *            base url of the document, mandatory, must end with a '/'
     * @param out
     *            outputstream to render into
     * @param fproperties
     *            properties map
     *
     * @throws CConvertException
     *             if an unexpected error occurs
     */
    public final void convertToPdf(final String html, final String furlForBase,
            final IHtmlToPdfTransformer.PageSize size, final List hf, final OutputStream out, final Map fproperties)
            throws CConvertException {
        String urlForBase = furlForBase;
        Map properties = (fproperties != null) ? fproperties : new HashMap();

        if (urlForBase != null) {
            if (urlForBase.indexOf("://") != -1) {
                String tmp = urlForBase.substring(urlForBase.indexOf("://") + 3);
                if (tmp.indexOf('/') == -1) {
                    urlForBase += "/";
                }
            }
        } // end if

        transformer.transform(html, urlForBase, size, hf, properties, out);
    } // end convertToPdf()

} // end CYaHPConverter
