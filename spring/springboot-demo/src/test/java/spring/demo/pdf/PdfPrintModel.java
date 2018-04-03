package spring.demo.pdf;

import java.io.Serializable;

/**
 * Created by wangfacheng on 2018-03-28.
 */
public class PdfPrintModel implements Serializable {
    private static final long serialVersionUID = -584652795930265814L;

    private String name;

    private String otmsLogoPath;

    public static PdfPrintModel emptyInstance() {
        return new PdfPrintModel();
    }

    public PdfPrintModel withName(String name) {
        this.name = name;
        return this;
    }

    public PdfPrintModel withOtmsLogPath(String otmsLogoPath) {
        this.otmsLogoPath = otmsLogoPath;
        return this;
    }

    public String getOtmsLogoPath() {
        return otmsLogoPath;
    }

    public void setOtmsLogoPath(String otmsLogoPath) {
        this.otmsLogoPath = otmsLogoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
