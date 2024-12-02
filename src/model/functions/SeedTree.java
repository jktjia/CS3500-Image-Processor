package model.functions;

public class SeedTree {
  private enum CD {
    X, Y;

    CD next() {
      if (this.equals(X)) {
        return Y;
      } else if (this.equals(Y)) {
        return X;
      } else {
        throw new IllegalArgumentException("Invalid CD");
      }
    }
  }

  private final int x;
  private final int y;
  private final int h;
  private final int w;
  private SeedTree left;
  private SeedTree right;
  private final CD cutDimension;

  public SeedTree(int pixel, int height, int width) {
    this(pixel % width, pixel / width, CD.X, height, width);
  }

  private SeedTree(int x, int y, CD cutDimension, int height, int width) {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Non-positive height/width");
    }
    if (x < 0 || y < 0 || x >= width || y >= height) {
      throw new IllegalArgumentException("Invalid x/y value");
    }
    this.x = x;
    this.y = y;
    this.cutDimension = cutDimension;
    this.h = height;
    this.w = width;
  }

  public void addSeed(int seed) {
    int seedX = seed % this.w;
    int seedY = seed / this.w;
    if (seedX < 0 || seedY < 0 || seedX >= this.w || seedY >= this.h) {
      throw new IllegalArgumentException("Seed has invalid x/y value");
    }

    if ((this.cutDimension.equals(CD.X) && seedX < this.x)
            || (this.cutDimension.equals(CD.Y) && seedY < this.y)) {
      if (this.left == null) {
        this.left = new SeedTree(seedX, seedY, this.cutDimension.next(), this.h, this.w);
      } else {
        this.left.addSeed(seed);
      }
    } else if ((this.cutDimension.equals(CD.X) && seedX >= this.x)
            || (this.cutDimension.equals(CD.Y) && seedY >= this.y)) {
      if (this.right == null) {
        this.right = new SeedTree(seedX, seedY, this.cutDimension.next(), this.h, this.w);
      } else {
        this.right.addSeed(seed);
      }
    } else {
      throw new IllegalStateException("Something has gone terribly wrong with the cut dimension");
    }
  }

  public int nearestSeed(int pixel) {
    int pixelX = pixel % this.w;
    int pixelY = pixel / this.w;
    if (pixelX < 0 || pixelY < 0 || pixelX >= this.w || pixelY >= this.h) {
      throw new IllegalArgumentException("Pixel has invalid x/y value");
    }

    return nearestSeed(pixelX, pixelY, 0, this.w, 0, this.h, this.x + (this.y * this.w));
  }

  private int nearestSeed(
          int x, int y, int minX, int maxX, int minY, int maxY, int bestPixel) {
    int bestX = bestPixel % this.w;
    int bestY = bestPixel / this.w;
    int best = bestPixel;
    int bestDist = distSqd(x, y, bestX, bestY);

    if (distToBoxSqd(x, y, minX, maxX, minY, maxY) > bestDist) {
      return bestPixel;
    } else {
      int dist = distSqd(x, y, this.x, this.y);
      if (dist < bestDist) {
        best = this.x + (this.y * this.w);
      }

      if (this.left == null && this.right != null) {
        if (this.cutDimension.equals(CD.X)) {
          best = this.right.nearestSeed(x, y, this.x, maxX, minY, maxY, best);
        } else {
          best = this.right.nearestSeed(x, y, minX, maxX, this.y, maxY, best);
        }
      } else if (this.left != null && this.right == null) {
        if (this.cutDimension.equals(CD.X)) {
          best = this.left.nearestSeed(x, y, minX, this.x, minY, maxY, best);
        } else {
          best = this.left.nearestSeed(x, y, minX, maxX, minY, this.y, best);
        }
      } else if (this.left != null && this.right != null) {
        if (this.cutDimension.equals(CD.X) && x < this.x) {
          best = this.left.nearestSeed(x, y, minX, this.x, minY, maxY, best);
          best = this.right.nearestSeed(x, y, this.x, maxX, minY, maxY, best);
        } else if (this.cutDimension.equals(CD.Y) && y < this.y) {
          best = this.left.nearestSeed(x, y, minX, maxX, minY, this.y, best);
          best = this.right.nearestSeed(x, y, minX, maxX, this.y, maxY, best);
        } else if (this.cutDimension.equals(CD.X) && x >= this.x) {
          best = this.right.nearestSeed(x, y, this.x, maxX, minY, maxY, best);
          best = this.left.nearestSeed(x, y, minX, this.x, minY, maxY, best);
        } else if (this.cutDimension.equals(CD.Y) && y >= this.y) {
          best = this.right.nearestSeed(x, y, minX, maxX, this.y, maxY, best);
          best = this.left.nearestSeed(x, y, minX, maxX, minY, this.y, best);
        }
      }
      return best;
    }
  }

  private static int distSqd(int x1, int y1, int x2, int y2) {
    return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  private static int distToBoxSqd(int x, int y, int minX, int maxX, int minY, int maxY) {
    if ((x >= minX && x < maxX) && (y >= minY && y < maxX)) {
      return 0;
    } else if (x >= minX && x < maxX) {
      return (int) Math.min(Math.pow(y - minY, 2), Math.pow(y - maxY, 2));
    } else if (y >= minY && y < maxY) {
      return (int) Math.min(Math.pow(x - minX, 2), Math.pow(x - maxX, 2));
    } else {
      return Math.min(distSqd(x, y, minX, minY),
              Math.min(distSqd(x, y, minX, maxY),
                      Math.min(distSqd(x, y, maxX, minY),
                              distSqd(x, y, maxX, maxY))));
    }
  }
}
