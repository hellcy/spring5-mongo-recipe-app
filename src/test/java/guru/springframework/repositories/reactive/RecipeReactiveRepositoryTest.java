package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest extends TestCase {

  @Autowired
  RecipeReactiveRepository recipeReactiveRepository;

  @Override
  @Before
  public void setUp() throws Exception {
    recipeReactiveRepository.deleteAll().block();
  }

  @Test
  public void testReciepSave() throws Exception{
    Recipe recipe = new Recipe();
    recipe.setDescription("Yummy");

    recipeReactiveRepository.save(recipe).block();
    Long count = recipeReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);
  }
}