package recipeApp;

public class Context {
  private StrategyInterface strategy;

  public void setStrategy(StrategyInterface theStrategy) {
    this.strategy = theStrategy;
  }

  public StrategyInterface getStrategy() {
    return strategy;
  }
}
