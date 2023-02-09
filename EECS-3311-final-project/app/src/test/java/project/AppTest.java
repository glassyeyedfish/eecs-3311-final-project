/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import org.junit.Test;
import project.objects.Product;
import project.objects.ProductList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test public void appHasAGreeting() {
    }

    @Test public void ProductListTableEntries() {
        Product p1 = new Product(0,"Shoe", 300, 1.5f, new Date(0));
        Product p2 = new Product(1,"Ball", 200, 3.5f, new Date(0));
        Product p3 = new Product(2,"Bat", 1000, 1.0f, new Date(0));

        List<Product> pl = new ArrayList<>();
        pl.add(p1);
        pl.add(p2);
        pl.add(p3);

        ProductList productList = new ProductList(pl);

        assertEquals(Arrays.deepToString(productList.getTableEntries()),
        "[[0, Shoe, 300, 1.50, Wed Dec 31 19:00:00 EST 1969], [1, Ball, 200, 3.50, Wed Dec 31 19:00:00 EST 1969], [2, Bat, 1000, 1.00, Wed Dec 31 19:00:00 EST 1969]]");

    }
}
