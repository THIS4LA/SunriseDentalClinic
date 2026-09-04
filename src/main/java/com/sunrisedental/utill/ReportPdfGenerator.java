package com.sunrisedental.utill;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportPdfGenerator {

    private static final float MARGIN = 40;

    private static final float ROW_HEIGHT = 22;

    private ReportPdfGenerator() {
    }

    public static File generateReport(
            String reportType,
            LocalDate fromDate,
            LocalDate toDate,
            JTable table) {

        if (reportType == null
                || reportType.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Report type is required."
            );
        }

        if (fromDate == null
                || toDate == null) {

            throw new IllegalArgumentException(
                    "Report date range is required."
            );
        }

        if (table == null) {

            throw new IllegalArgumentException(
                    "Report table is not available."
            );
        }

        try {

            File reportDirectory
                    = new File("reports");

            if (!reportDirectory.exists()) {

                reportDirectory.mkdirs();
            }

            String safeReportName
                    = reportType
                            .replaceAll(
                                    "[^a-zA-Z0-9]+",
                                    "_"
                            );

            String timestamp
                    = LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd_HHmmss"
                                    )
                            );

            File pdfFile
                    = new File(
                            reportDirectory,
                            safeReportName
                            + "_"
                            + timestamp
                            + ".pdf"
                    );

            createPdf(
                    pdfFile,
                    reportType,
                    fromDate,
                    toDate,
                    table
            );

            return pdfFile;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to generate PDF report.",
                    e
            );
        }
    }

    private static void createPdf(
            File file,
            String reportType,
            LocalDate fromDate,
            LocalDate toDate,
            JTable table)
            throws IOException {

        try (PDDocument document
                = new PDDocument()) {

            createReportPages(
                    document,
                    reportType,
                    fromDate,
                    toDate,
                    table
            );

            document.save(
                    file
            );
        }
    }

    private static void createReportPages(
            PDDocument document,
            String reportType,
            LocalDate fromDate,
            LocalDate toDate,
            JTable table)
            throws IOException {

        int totalRows
                = table.getRowCount();

        int currentRow
                = 0;

        boolean firstPage
                = true;

        do {

            PDPage page
                    = new PDPage(
                            new PDRectangle(
                                    PDRectangle.A4.getHeight(),
                                    PDRectangle.A4.getWidth()
                            )
                    );

            document.addPage(
                    page
            );

            try (PDPageContentStream content
                    = new PDPageContentStream(
                            document,
                            page
                    )) {

                float y
                        = page.getMediaBox()
                                .getHeight()
                        - MARGIN;

                y = drawHeader(
                        content,
                        reportType,
                        fromDate,
                        toDate,
                        y,
                        firstPage
                );

                y -= 20;

                y = drawTableHeader(
                        content,
                        table,
                        page,
                        y
                );

                while (currentRow < totalRows) {

                    if (y < MARGIN + ROW_HEIGHT) {

                        break;
                    }

                    y = drawTableRow(
                            content,
                            table,
                            currentRow,
                            page,
                            y
                    );

                    currentRow++;
                }

                drawFooter(
                        content,
                        page
                );
            }

            firstPage = false;

        } while (currentRow < totalRows);
    }

    private static float drawHeader(
            PDPageContentStream content,
            String reportType,
            LocalDate fromDate,
            LocalDate toDate,
            float y,
            boolean firstPage)
            throws IOException {

        content.beginText();

        content.setFont(
                PDType1Font.HELVETICA_BOLD,
                20
        );

        content.newLineAtOffset(
                MARGIN,
                y
        );

        content.showText(
                "SUNRISE DENTAL CLINIC"
        );

        content.endText();

        y -= 30;

        content.beginText();

        content.setFont(
                PDType1Font.HELVETICA_BOLD,
                16
        );

        content.newLineAtOffset(
                MARGIN,
                y
        );

        content.showText(
                reportType
        );

        content.endText();

        y -= 25;

        content.beginText();

        content.setFont(
                PDType1Font.HELVETICA,
                10
        );

        content.newLineAtOffset(
                MARGIN,
                y
        );

        content.showText(
                "From: "
                + fromDate
                + "     To: "
                + toDate
        );

        content.endText();

        y -= 18;

        content.beginText();

        content.setFont(
                PDType1Font.HELVETICA,
                9
        );

        content.newLineAtOffset(
                MARGIN,
                y
        );

        String generatedAt
                = LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HH:mm"
                                )
                        );

        content.showText(
                "Generated: "
                + generatedAt
        );

        content.endText();

        return y;
    }

    private static float drawTableHeader(
            PDPageContentStream content,
            JTable table,
            PDPage page,
            float y)
            throws IOException {

        int columnCount
                = table.getColumnCount();

        float availableWidth
                = page.getMediaBox()
                        .getWidth()
                - (MARGIN * 2);

        float columnWidth
                = availableWidth
                / columnCount;

        float x
                = MARGIN;

        for (int column = 0;
                column < columnCount;
                column++) {

            drawCellBorder(
                    content,
                    x,
                    y - ROW_HEIGHT,
                    columnWidth,
                    ROW_HEIGHT
            );

            String columnName
                    = table
                            .getColumnName(
                                    column
                            );

            drawText(
                    content,
                    columnName,
                    x + 4,
                    y - 15,
                    true
            );

            x += columnWidth;
        }

        return y - ROW_HEIGHT;
    }

    private static float drawTableRow(
            PDPageContentStream content,
            JTable table,
            int row,
            PDPage page,
            float y)
            throws IOException {

        int columnCount
                = table.getColumnCount();

        float availableWidth
                = page.getMediaBox()
                        .getWidth()
                - (MARGIN * 2);

        float columnWidth
                = availableWidth
                / columnCount;

        float x
                = MARGIN;

        for (int column = 0;
                column < columnCount;
                column++) {

            drawCellBorder(
                    content,
                    x,
                    y - ROW_HEIGHT,
                    columnWidth,
                    ROW_HEIGHT
            );

            Object value
                    = table.getValueAt(
                            row,
                            column
                    );

            String text
                    = value == null
                    ? ""
                    : value.toString();

            text = shortenText(
                    text,
                    columnWidth
            );

            drawText(
                    content,
                    text,
                    x + 4,
                    y - 15,
                    false
            );

            x += columnWidth;
        }

        return y - ROW_HEIGHT;
    }

    private static void drawCellBorder(
            PDPageContentStream content,
            float x,
            float y,
            float width,
            float height)
            throws IOException {

        content.addRect(
                x,
                y,
                width,
                height
        );

        content.stroke();
    }

    private static void drawText(
            PDPageContentStream content,
            String text,
            float x,
            float y,
            boolean bold)
            throws IOException {

        content.beginText();

        content.setFont(
                bold
                ? PDType1Font.HELVETICA_BOLD
                : PDType1Font.HELVETICA,
                8
        );

        content.newLineAtOffset(
                x,
                y
        );

        content.showText(
                cleanText(
                        text
                )
        );

        content.endText();
    }

    private static String cleanText(
            String text) {

        if (text == null) {

            return "";
        }

        return text
                .replace("\n", " ")
                .replace("\r", " ");
    }

    private static String shortenText(
            String text,
            float columnWidth) {

        if (text == null) {

            return "";
        }

        int maxCharacters
                = Math.max(
                        5,
                        (int) (
                                columnWidth
                                / 5
                        )
                );

        if (text.length()
                <= maxCharacters) {

            return text;
        }

        return text.substring(
                0,
                maxCharacters - 3
        ) + "...";
    }

    private static void drawFooter(
            PDPageContentStream content,
            PDPage page)
            throws IOException {

        content.beginText();

        content.setFont(
                PDType1Font.HELVETICA,
                8
        );

        content.newLineAtOffset(
                MARGIN,
                20
        );

        content.showText(
                "Sunrise Dental Clinic - Administrative Report"
        );

        content.endText();
    }
}