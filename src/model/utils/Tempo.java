package model.utils;

/**
 * Represent the time class of the model state.
 */
public class Tempo {
  double tempo = 0;

  public Tempo(int num) {
    this.tempo = num;
  }

  /**
   * A method to get the current tempo.
   *
   * @return double - the current method
   */
  public double getTempo() {
    return this.tempo;
  }

  /**
   * A method to add one on the tempo everytime it is called.
   */
  public void addTempo() {
    tempo++;
  }
}

