package app.foodpanda;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.model.Category;
import app.foodpanda.repository.CategoryRepository;
import app.foodpanda.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testFindAll() {
        List<Category> categories = new ArrayList<>(Arrays.asList(new Category("Breakfast"),
                                                                  new Category("Lunch"),
                                                                  new Category("Dinner")));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.findAll();

        categories.sort(Comparator.comparing(Category::getName));
        categoryDTOS.sort(Comparator.comparing(CategoryDTO::getName));
        boolean equals = true;

        if (categories.size() != categoryDTOS.size())
            equals = false;
        else {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getName().compareTo(categoryDTOS.get(i).getName()) != 0)
                    equals = false;
            }
        }

        Assert.assertTrue(equals);
    }
}
