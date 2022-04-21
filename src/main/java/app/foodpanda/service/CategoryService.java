package app.foodpanda.service;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.model.Category;
import app.foodpanda.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category c : categoryRepository.findAll()) {
            categoryDTOS.add(new CategoryDTO(c.getName()));
        }
        return categoryDTOS;
    }

}
