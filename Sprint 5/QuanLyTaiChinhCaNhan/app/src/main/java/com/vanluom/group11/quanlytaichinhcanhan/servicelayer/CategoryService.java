package com.vanluom.group11.quanlytaichinhcanhan.servicelayer;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountTransactionRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.CategoryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.Select;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.SubcategoryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Category;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Subcategory;

import java.util.List;

/**
 * Category
 */
public class CategoryService
    extends ServiceBase {

    public CategoryService(Context context) {
        super(context);

    }

    private CategoryRepository mRepository;

    public int loadIdByName(String name) {
        return getRepository().loadIdByName(name);
    }

    public int createNew(String name) {
        if (TextUtils.isEmpty(name)) return Constants.NOT_SET;

        name = name.trim();

        ContentValues values = new ContentValues();
        values.put(Category.CATEGNAME, name);

        CategoryRepository repo = new CategoryRepository(getContext());

        Uri result = getContext().getContentResolver()
                .insert(repo.getUri(), values);
        long id = ContentUris.parseId(result);

        return ((int) id);
    }

    public String getCategorySubcategoryName(int categoryId, int subCategoryId) {
        String categoryName = "";
        String subCategoryName = "";

        if (categoryId != Constants.NOT_SET) {
            CategoryRepository categoryRepository = new CategoryRepository(getContext());
            Category category = categoryRepository.load(categoryId);
            categoryName = category != null
                ? category.getName()
                : "n/a";
        }
        if (subCategoryId != Constants.NOT_SET) {
            SubcategoryRepository subcategoryRepository = new SubcategoryRepository(getContext());
            Subcategory subcategory = subcategoryRepository.load(subCategoryId);
            subCategoryName = subcategory != null 
                ? subcategory.getName()
                : "n/a";
        }

        String result = "";
        if (!TextUtils.isEmpty(categoryName)) result += categoryName;
        if (!TextUtils.isEmpty(subCategoryName)) result += ":" + subCategoryName;

        return result;
    }

    /**
     * Return a list of all categories. Ordered by name.
     */
    public List<Category> getList() {
        Select query = new Select().orderBy(Category.CATEGNAME);

        return getRepository().query(Category.class, query);
    }

    public int update(int id, String name) {
        if(TextUtils.isEmpty(name)) return Constants.NOT_SET;

        name = name.trim();

        ContentValues values = new ContentValues();
        values.put(Category.CATEGNAME, name);

        CategoryRepository repo = new CategoryRepository(getContext());

        int result = getContext().getContentResolver().update(repo.getUri(),
                values,
                Category.CATEGID + "=" + id, null);

        return result;
    }

    /**
     * Checks account transactions to find any that use given category
     * @param categoryId Id of the category for which to check.
     * @return A boolean indicating if the category is in use.
     */
    public boolean isCategoryUsed(int categoryId) {
        AccountTransactionRepository repo = new AccountTransactionRepository(getContext());
        int links = repo.count(Category.CATEGID + "=?", new String[]{Integer.toString(categoryId)});
        return links > 0;
    }

    public boolean isSubcategoryUsed(int subcategoryId) {
        AccountTransactionRepository repo = new AccountTransactionRepository(getContext());
        int links = repo.count(Subcategory.SUBCATEGID + "=?", new String[] { Integer.toString(subcategoryId)});
        return links > 0;
    }

    private CategoryRepository getRepository() {
        if (mRepository == null) {
            mRepository = new CategoryRepository(getContext());
        }
        return mRepository;
    }
}
