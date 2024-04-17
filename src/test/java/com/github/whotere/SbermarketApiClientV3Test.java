package com.github.whotere;

import com.github.whotere.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SbermarketApiClientV3Test {

    private static final Integer STORE_ID = 92;
    private static final String CATEGORY_SLUG = "rekomenduem-668d4d3/voda-soki-napitki-515b671";
    private static final int PRODUCT_ID = 511118;

    private final ModelMapper modelMapper = new ModelMapperJackson();

    private SbermarketApiClientV3 apiClient;

    @BeforeEach
    public void initClient() {
        apiClient = new SbermarketApiClientV3(modelMapper);
    }

    @Test
    public void testGetStoreById() {
        Store store = assertDoesNotThrow(() -> apiClient.getStoreById(STORE_ID));
        assertNotNull(store);
    }

    @Test
    public void testGetRetailerById() {
        Retailer retailer = assertDoesNotThrow(() -> apiClient.getRetailerById(15));
        assertNotNull(retailer);
    }

    @Test
    public void testGetCategoriesByStoreId() {
        CategoriesResponseBody categoriesResponseBody =
                assertDoesNotThrow(() -> apiClient.getCategoriesByStoreId(STORE_ID));
        assertNotNull(categoriesResponseBody);
    }

    @Test
    public void testGetProductsByStoreIdAndCategorySlug() {
        ProductsResponseBody productsResponseBody =
                assertDoesNotThrow(() -> apiClient.getProductsByStoreIdAndCategorySlug(
                        STORE_ID,
                        CATEGORY_SLUG,
                        1,
                        10,
                        null));
        assertNotNull(productsResponseBody);
    }

    @Test
    public void testGetProductByIdAndStoreId() {
        ProductDetailedInfoResponseBody responseBody = assertDoesNotThrow(() -> apiClient.getProductByIdAndStoreId(
                PRODUCT_ID,
                STORE_ID));
        assertNotNull(responseBody);
    }

    @Test
    public void testGetProductsByStoreIdAndQuery() {
        ProductsResponseBody responseBody = assertDoesNotThrow(() -> apiClient.getProductsByStoreIdAndQuery(
                STORE_ID,
                "alpen gold",
                1,
                10,
                "price_desc"));
        assertNotNull(responseBody);
    }
}
