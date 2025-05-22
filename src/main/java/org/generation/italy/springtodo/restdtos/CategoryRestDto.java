package org.generation.italy.springtodo.restdtos;

import org.generation.italy.springtodo.models.enitites.Category;

public class CategoryRestDto {
    private int categoryId;
    private String categoryName;

    public CategoryRestDto() {
    }

    public CategoryRestDto(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryRestDto{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Category toCategory(){
        Category c = new Category(categoryId, categoryName);
        return c;
    }

    public static CategoryRestDto toDto(Category c){
        return new CategoryRestDto(c.getCategoryId(), c.getCategoryName());
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
