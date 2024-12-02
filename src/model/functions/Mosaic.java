package model.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class for creating mosaic versions of images with a given number of seeds.
 */
public class Mosaic extends AbstractTransformImage {
  private final int numSeeds;

  /**
   * Creates a new Mosaic given the number of seeds, the name of the original image, and the name
   * of the new image.
   *
   * @param numSeeds  number of seeds
   * @param imageName name of original
   * @param saveName  name of new image
   * @throws IllegalArgumentException if the number of seeds is not positive
   */
  public Mosaic(int numSeeds, String imageName, String saveName) {
    super(imageName, saveName);
    if (numSeeds <= 0) {
      throw new IllegalArgumentException("Non-positive number of seeds");
    }
    this.numSeeds = numSeeds;
  }

  @Override
  protected StoredImage generateImage(StoredImage original) {
    if (original.getHeight() * original.getWidth() < numSeeds) {
      throw new IllegalArgumentException("Number of seeds is greater than number of pixels");
    }
    return clustersToImage(
            getClusters(numSeeds, original.getHeight(), original.getWidth()),
            original);
  }

  /**
   * Creates some given number of clusters that are the pixels closest to some randomly chosen n
   * seeds.
   *
   * @param numSeeds number of seeds
   * @param h        height of the image
   * @param w        width of the image
   * @return array of lists of all the pixels in each cluster
   */
  private static java.util.List<Integer>[] getClusters(int numSeeds, int h, int w) {
    Random r = new Random();
    List<Integer> seeds = r.ints(0, h * w)
            .distinct()
            .limit(numSeeds)
            .boxed()
            .collect(Collectors.toList());

    List<Integer>[] toReturn = new List[numSeeds];
    for (int i = 0; i < toReturn.length; i++) {
      toReturn[i] = new ArrayList<>();
    }

    for (int n = 0; n < h * w; n++) {
      if (seeds.contains(n)) {
        toReturn[seeds.indexOf(n)].add(n);
      } else {
        int x = n % w;
        int y = n / w;
        int closestSeedIdx = -1;
        double minDistance = Double.MAX_VALUE;

        for (int idx = 0; idx < seeds.size(); idx++) {
          int seedX = seeds.get(idx) % w;
          int seedY = seeds.get(idx) / w;
          double distance = Math.pow(Math.pow(x - seedX, 2) + Math.pow(y - seedY, 2), 0.5);
          if (distance < minDistance) {
            closestSeedIdx = idx;
            minDistance = distance;
          }
        }
        toReturn[closestSeedIdx].add(n);
      }
    }
    return toReturn;
  }

  /**
   * Creates a new image where every pixel in a cluster to the average color in given image of
   * pixels in the cluster.
   *
   * @param clusters array of clusters
   * @param img      image to take colors from
   * @return mosaic image
   */
  private static StoredImage clustersToImage(List<Integer>[] clusters, StoredImage img) {
    int w = img.getWidth();
    int h = img.getHeight();
    int[][] r = new int[h][w];
    int[][] g = new int[h][w];
    int[][] b = new int[h][w];
    for (List<Integer> cluster : clusters) {
      int red = cluster.stream().mapToInt(p -> img.getRed(p % w, p / w)).sum()
              / cluster.size();
      int green = cluster.stream().mapToInt(p -> img.getGreen(p % w, p / w)).sum()
              / cluster.size();
      int blue = cluster.stream().mapToInt(p -> img.getBlue(p % w, p / w)).sum()
              / cluster.size();

      for (int n : cluster) {
        int y = n / w;
        int x = n % w;
        r[y][x] = red;
        g[y][x] = green;
        b[y][x] = blue;
      }
    }
    return new SimpleRGBImage(w, h, r, g, b);
  }
}
