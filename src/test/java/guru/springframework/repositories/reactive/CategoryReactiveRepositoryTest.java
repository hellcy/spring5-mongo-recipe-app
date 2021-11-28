package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest extends TestCase {
  @Autowired
  CategoryReactiveRepository categoryReactiveRepository;

  @Override
  @Before
  public void setUp() throws Exception {
    categoryReactiveRepository.deleteAll().block();
  }

  @Test
  public void testSave() {
    Category category = new Category();
    category.setDescription("Foo");

    categoryReactiveRepository.save(category).block();
    Long count = categoryReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);
  }

  @Test
  public void testFindByDescription() {
    Category category = new Category();
    category.setDescription("Foo");

    categoryReactiveRepository.save(category).block();
    Category savedCategory = categoryReactiveRepository.findByDescription("Foo").block();

    assertNotNull(savedCategory.getId());
  }
}