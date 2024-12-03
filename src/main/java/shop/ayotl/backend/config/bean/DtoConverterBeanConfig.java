package shop.ayotl.backend.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.ayotl.backend.converter.category.CategoryDtoConverter;
import shop.ayotl.backend.converter.product.ProductDtoConverter;
import shop.ayotl.backend.converter.role.RoleDtoConverter;
import shop.ayotl.backend.converter.user.UserDtoConverter;
import shop.ayotl.backend.converter.user.data.UserDataDtoConverter;

@Configuration
public class DtoConverterBeanConfig {
    @Bean
    public UserDtoConverter userDtoConverter() {
        return new UserDtoConverter();
    }

    @Bean
    public UserDataDtoConverter userDataDtoConverter() {
        return new UserDataDtoConverter();
    }

    @Bean
    public RoleDtoConverter roleDtoConverter() {
        return new RoleDtoConverter();
    }

    @Bean
    public CategoryDtoConverter categoryDtoConverter() {
        return new CategoryDtoConverter();
    }

    @Bean
    public ProductDtoConverter productDtoConverter() {return new ProductDtoConverter();}
}
