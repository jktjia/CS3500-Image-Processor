import org.junit.Test;

import model.functions.SeedTree;

import static org.junit.Assert.assertEquals;

/**
 * A class to test SeedTree.
 */
public class SeedTreeTest {
  @Test
  public void testTree() {
    SeedTree tree = new SeedTree(12, 5, 5);
    tree.addSeed(15);
    tree.addSeed(17);
    tree.addSeed(2);
    tree.addSeed(5);
    tree.addSeed(8);

    assertEquals(5, tree.nearestSeed(0));
    assertEquals(2, tree.nearestSeed(1));
    assertEquals(2, tree.nearestSeed(2));
    assertEquals(2, tree.nearestSeed(3));
    assertEquals(8, tree.nearestSeed(4));

    assertEquals(5, tree.nearestSeed(5));
    assertEquals(5, tree.nearestSeed(6));
    assertEquals(12, tree.nearestSeed(7));
    assertEquals(8, tree.nearestSeed(8));
    assertEquals(8, tree.nearestSeed(9));

    assertEquals(15, tree.nearestSeed(10));
    assertEquals(12, tree.nearestSeed(11));
    assertEquals(12, tree.nearestSeed(12));
    assertEquals(12, tree.nearestSeed(13));
    assertEquals(8, tree.nearestSeed(14));

    assertEquals(15, tree.nearestSeed(15));
    assertEquals(17, tree.nearestSeed(16));
    assertEquals(17, tree.nearestSeed(17));
    assertEquals(17, tree.nearestSeed(18));
    assertEquals(17, tree.nearestSeed(19));

    assertEquals(15, tree.nearestSeed(20));
    assertEquals(15, tree.nearestSeed(21));
    assertEquals(17, tree.nearestSeed(22));
    assertEquals(17, tree.nearestSeed(23));
    assertEquals(17, tree.nearestSeed(24));
  }
}
