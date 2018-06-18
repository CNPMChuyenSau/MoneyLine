package com.vanluom.group11.quanlytaichinhcanhan.servicelayer.qif;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.adapter.AllDataAdapter;
import com.vanluom.group11.quanlytaichinhcanhan.core.file.TextFileExport;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

import timber.log.Timber;

/**
 * Handles export of transactions from AllDataListFragment into QIF format.
 * References:
 * http://en.wikipedia.org/wiki/Quicken_Interchange_Format
 */
public class QifExport
        extends TextFileExport {

    public QifExport(Context context) {
        super(context);

        mContext = context;
    }

    /**
     * Export the transactions into qif format and offer file for sharing.
     */
    public void export(AllDataAdapter adapter) {
        // just e errors here
        try {
            this.export_internal(adapter);
        } catch (Exception e) {
            Timber.e(e, ".qif export");
        }
    }

    // Private

    private void export_internal(AllDataAdapter adapter)
            throws Exception {
        String fileName = generateFileName();

        // get data into qif structure
        IQifGenerator generator = getQifGenerator();
        String content = generator.createFromAdapter(adapter);
        String title = getContext().getString(R.string.qif_export);

        boolean success = this.export(fileName, content, title);
    }

//    private void dumpContentIntoFile(String content, File file) {
//        // files created this way are located in private files, not cache!
//        try {
//            FileOutputStream stream = this.context.openFileOutput(
//                    file.getName(), Context.MODE_PRIVATE);
//            // use Context.MODE_PRIVATE for private-only files. Context.MODE_APPEND
//
//            stream.write(content.getBytes());
//            stream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private boolean dumpContentIntoFile(String content, File file) {
//        boolean result;
//
//        try {
//            result = file.createNewFile();
//            if(!result) {
//                throw new Exception("Error creating file!");
//            }
//
//            FileWriter writer = new FileWriter(file);
//            writer.write(content);
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            result = false;
//        }
//
//        return result;
//    }

    private String generateFileName() {
        // use just the date for now?
        String format = "yyyy-MM-dd_HHmmss";
        String result = new MmxDate().toString(format);

        // append file extension.
        result += ".qif";

        return result;
    }

    /**
     * factory method for Qif generator.
     * @return implementation of Qif generator interface.
     */
    private IQifGenerator getQifGenerator() {
        return new QifGenerator(this.mContext);
    }
}
