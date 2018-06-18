
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.report;

import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;

import java.util.Locale;

/**
 * Formats the Asset Allocation for the HTML report.
 */
public class ReportHtmlFormatter {
    public static final String VALUE_FORMAT = "%,.2f";

    public ReportHtmlFormatter(AssetClass allocation, String color) {
        this.allocation = allocation;
        this.color = color;
    }

    private AssetClass allocation;
    private String color;

    public String getName() {
        return allocation.getName();
    }

    public String getDiffPerc() {
        String html = String.format("<span style='color: %s;'>", color);
        html += allocation.getDiffAsPercentOfSet();
        html += "%</span>";

        return html;
    }

    public String getDiffAmount() {
        String html = String.format("<span style='color: %s;'>", color);
        html += String.format(Locale.UK, VALUE_FORMAT, allocation.getDifference().toDouble());
        html += "</span>";
        return html;
    }

    public String getAllocation() {
        return String.format(Locale.UK, VALUE_FORMAT, allocation.getAllocation().toDouble()) + "/";
    }

    public String getCurrentAllocation() {
        String html = String.format("<span style='color: %s; font-weight: bold;'>", color);
        html += String.format(Locale.UK, VALUE_FORMAT, allocation.getCurrentAllocation().toDouble()) +
                "</span>";
        return html;
    }

    public String getValue() {
        return String.format(Locale.UK, VALUE_FORMAT + "/" + VALUE_FORMAT,
                allocation.getValue().toDouble(), allocation.getCurrentValue().toDouble());
    }
}
