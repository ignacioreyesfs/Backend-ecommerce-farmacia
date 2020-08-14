package Recommendation;

public enum RecommendedWeather {
    COLD {
        @Override
        public boolean recommendedForTemperature(double temperature){
            return temperature <= 16;
        }
    },
    NORMAL{
      @Override
      public boolean recommendedForTemperature(double temperature){
          return false;
      }
    },
    HOT {
        @Override
        public boolean recommendedForTemperature(double temperature){
            return temperature >= 24;
        }
    };

    public abstract boolean recommendedForTemperature(double temperature);
}
