package com.sunrisedental.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public final class PdfOpener {

    private PdfOpener() {
    }

    public static void open(
            File pdfFile) {

        if (pdfFile == null
                || !pdfFile.exists()) {

            throw new IllegalArgumentException(
                    "PDF report does not exist."
            );
        }

        try {

            if (!Desktop.isDesktopSupported()) {

                throw new IllegalStateException(
                        "Desktop operations are not supported."
                );
            }

            Desktop.getDesktop()
                    .browse(
                            pdfFile.toURI()
                    );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to open PDF report.",
                    e
            );
        }
    }
}