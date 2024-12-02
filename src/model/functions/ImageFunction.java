package model.functions;

import model.ImageStorage;

/**
 * Represents a function that can be run on ImageStorage.
 */
public interface ImageFunction {
  /**
   * Runs a function.
   *
   * @param storage storage to run on
   */
  void run(ImageStorage storage);
}
