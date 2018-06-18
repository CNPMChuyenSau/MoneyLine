package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * This class is used to interact with application preferences/preferences.
 * Expand with additional methods as needed.
 */
public class AppSettings
    extends SettingsBase {

    @Inject
    public AppSettings(Context context) {
        super(context);

        // DI
        MoneyManagerApplication.getApp().iocComponent.inject(this);
    }

    @Inject Lazy<SharedPreferences> sharedPreferences;

    // setting groups

    private GeneralSettings mGeneral;
    private LookAndFeelSettings mLookAndFeel;
    private BehaviourSettings mBehaviour;
    private InvestmentSettings mInvestment;
    private BudgetSettings mBudget;
    private DatabaseSettings mDatabase;

    @Override
    protected SharedPreferences getPreferences() {
        return sharedPreferences.get();
    }

    public DatabaseSettings getDatabaseSettings() {
        if (mDatabase == null) {
            mDatabase = new DatabaseSettings(this);
        }
        return mDatabase;
    }

    public GeneralSettings getGeneralSettings() {
        if (mGeneral == null) {
            mGeneral = new GeneralSettings(getContext());
        }
        return mGeneral;
    }

    public LookAndFeelSettings getLookAndFeelSettings() {
        if (mLookAndFeel == null) mLookAndFeel = new LookAndFeelSettings(getContext());

        return mLookAndFeel;
    }

    public BehaviourSettings getBehaviourSettings() {
        if (mBehaviour == null) {
            mBehaviour = new BehaviourSettings(getContext());
        }
        return mBehaviour;
    }

    public InvestmentSettings getInvestmentSettings() {
        if (mInvestment == null) {
            mInvestment = new InvestmentSettings(getContext());
        }
        return mInvestment;
    }

    public BudgetSettings getBudgetSettings() {
        if (mBudget == null) {
            mBudget = new BudgetSettings(getContext());
        }
        return mBudget;
    }

    // Individual preferences.

    public int getPayeeSort() {
        int sort = get(R.string.pref_sort_payee, 0);
        return sort;
    }
}
