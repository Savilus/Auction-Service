package pl.sda.pol122.auctionservice.dao;

import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

@Repository
public class CategoryDaoDB implements CategoryDao {

    private final CategoryRepository categoryRepository;

    public CategoryDaoDB(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void saveCategory(CategoryEntity category) {
        categoryRepository.save(category);
    }

    @Override
    public List<ProductEntity> findProductsOfCategory(Integer categoryId) {
        CategoryEntity categoryEntityById = categoryRepository.findCategoryEntityById(categoryId);
        return categoryEntityById.getListOfProducts();
    }


    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
