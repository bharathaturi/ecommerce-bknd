package com.ecommerce.config;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() > 0) {
                System.out.println("Data already seeded, skipping...");
                return;
            }

            System.out.println("Seeding data...");
            List<Product> products = new ArrayList<>();
            Random random = new Random();

            // Electronics
            String[] electroncisBrands = { "Samsung", "Apple", "Sony", "LG", "Dell", "HP", "Asus", "Lenovo" };
            String[] electronicsTypes = { "Smartphone", "Laptop", "Headphones", "Smartwatch", "Monitor", "Camera",
                    "Tablet", "Speaker" };
            String[] electronicsColors = { "Black", "White", "Silver", "Gray", "Blue", "Gold" };

            for (int i = 0; i < 200; i++) {
                String brand = electroncisBrands[random.nextInt(electroncisBrands.length)];
                String type = electronicsTypes[random.nextInt(electronicsTypes.length)];
                String color = electronicsColors[random.nextInt(electronicsColors.length)];
                String name = brand + " " + type + " " + (100 + random.nextInt(900));

                Product product = new Product();
                product.setName(name);
                product.setDescription("High quality " + color + " " + type + " from " + brand
                        + ". Features advanced technology and premium build quality.");
                product.setPrice(BigDecimal.valueOf(5000 + random.nextInt(95000)));
                product.setDiscountPrice(BigDecimal.valueOf(product.getPrice().doubleValue() * 0.9));
                product.setCategory("Electronics");
                product.setBrand(brand);
                product.setColor(color);
                product.setSize("Standard");
                product.setRating(3.5 + (random.nextDouble() * 1.5));
                product.setStockQuantity(10 + random.nextInt(100));
                String encodedName = (color + " " + type + " product photography").replace(" ", "%20");
                product.setImageUrl("https://image.pollinations.ai/prompt/" + encodedName + "?nologo=true");

                products.add(product);
            }

            // Fashion
            String[] fashionBrands = { "Nike", "Adidas", "Puma", "Zara", "H&M", "Levis", "Gucci", "Uniqlo" };
            String[] fashionTypes = { "T-Shirt", "Jeans", "Jacket", "Sneakers", "Dress", "Hoodie", "Skirt", "Shorts" };
            String[] fashionColors = { "Red", "Blue", "Green", "Black", "White", "Yellow", "Pink", "Navy" };
            String[] fashionSizes = { "S", "M", "L", "XL", "XXL" };

            for (int i = 0; i < 200; i++) {
                String brand = fashionBrands[random.nextInt(fashionBrands.length)];
                String type = fashionTypes[random.nextInt(fashionTypes.length)];
                String color = fashionColors[random.nextInt(fashionColors.length)];
                String size = fashionSizes[random.nextInt(fashionSizes.length)];
                String name = brand + " " + color + " " + type;

                Product product = new Product();
                product.setName(name);
                product.setDescription(
                        "Stylish " + color + " " + type + " from " + brand + ". Comfortable fit and trendy design.");
                product.setPrice(BigDecimal.valueOf(500 + random.nextInt(4500)));
                product.setDiscountPrice(BigDecimal.valueOf(product.getPrice().doubleValue() * 0.85));
                product.setCategory("Fashion");
                product.setBrand(brand);
                product.setColor(color);
                product.setSize(size);
                product.setRating(3.8 + (random.nextDouble() * 1.2));
                product.setStockQuantity(20 + random.nextInt(200));

                String encodedName = (color + " " + type + " fashion model photography").replace(" ", "%20");
                product.setImageUrl("https://image.pollinations.ai/prompt/" + encodedName + "?nologo=true");

                products.add(product);
            }

            // Home
            String[] homeBrands = { "IKEA", "Philips", "Dyson", "Bosch", "LG", "Samsung", "Nestle", "Pepperfry" };
            String[] homeTypes = { "Sofa", "Lamp", "Vacuum", "Blender", "Washing Machine", "Microwave", "Chair",
                    "Table" };
            String[] homeColors = { "Beige", "Brown", "White", "Black", "Grey", "Wood" };

            for (int i = 0; i < 200; i++) {
                String brand = homeBrands[random.nextInt(homeBrands.length)];
                String type = homeTypes[random.nextInt(homeTypes.length)];
                String color = homeColors[random.nextInt(homeColors.length)];
                String name = brand + " " + type + " " + (random.nextInt(50) + 1000);

                Product product = new Product();
                product.setName(name);
                product.setDescription("Modern " + color + " " + type + " by " + brand + ". Perfect for your home.");
                product.setPrice(BigDecimal.valueOf(1000 + random.nextInt(20000)));
                product.setDiscountPrice(BigDecimal.valueOf(product.getPrice().doubleValue() * 0.95));
                product.setCategory("Home");
                product.setBrand(brand);
                product.setColor(color);
                product.setSize("Standard");
                product.setRating(4.0 + (random.nextDouble() * 1.0));
                product.setStockQuantity(5 + random.nextInt(50));

                String encodedName = (color + " " + type + " interior design photography").replace(" ", "%20");
                product.setImageUrl("https://image.pollinations.ai/prompt/" + encodedName + "?nologo=true");

                products.add(product);
            }

            productRepository.saveAll(products);
            System.out.println("Data seeding completed with " + products.size() + " products.");
        };
    }
}
