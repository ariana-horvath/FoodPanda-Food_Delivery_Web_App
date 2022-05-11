package app.foodpanda.controller;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    private static Logger logger = LogManager.getLogger(CategoryController.class);

    @GetMapping("/categories")
    List<CategoryDTO> findAll() {
        logger.info("Executing the get request for categories.");
        return categoryService.findAll();
    }
}
