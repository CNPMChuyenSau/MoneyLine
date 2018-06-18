package com.vanluom.group11.quanlytaichinhcanhan.servicelayer.qif;

import com.vanluom.group11.quanlytaichinhcanhan.adapter.AllDataAdapter;

import java.text.ParseException;

/**
 * QIF generator interface.
 * Used in case there are multiple implementations.
 */
public interface IQifGenerator {
    // todo: replace AllDataAdapter with generic adapter (i.e. CursorAdapter).
    String createFromAdapter(AllDataAdapter adapter) throws ParseException;
}
