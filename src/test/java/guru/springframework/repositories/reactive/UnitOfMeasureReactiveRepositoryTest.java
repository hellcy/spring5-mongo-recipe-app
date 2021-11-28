package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest extends TestCase {
  public static final String EACH = "Each";
  @Autowired
  UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

  @Override
  @Before
  public void setUp() throws Exception {
    unitOfMeasureReactiveRepository.deleteAll().block();
  }

  @Override
  @After
  public void tearDown() throws Exception {
    unitOfMeasureReactiveRepository.deleteAll().block();
  }

  @Test
  public void testSaveUom() {
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setDescription(EACH);

    unitOfMeasureReactiveRepository.save(uom).block();

    Long count = unitOfMeasureReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);
  }

  @Test
  public void testFindByDescription() {
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setDescription(EACH);

    unitOfMeasureReactiveRepository.save(uom).block();

    UnitOfMeasure savedUom = unitOfMeasureReactiveRepository.findByDescription(EACH).block();

    assertEquals(uom.getDescription(), savedUom.getDescription());
  }
}