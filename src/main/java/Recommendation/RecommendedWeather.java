package Recommendation;

public enum RecommendedWeather {
    COLD {
        @Override
        public boolean recommendedTemperature(double temperature){
            return temperature <= 16;
        }
    },
    NORMAL{
      @Override
      public boolean recommendedTemperature(double temperature){
          return false;
      }
    },
    HOT {
        @Override
        public boolean recommendedTemperature(double temperature){
            return temperature >= 24;
        }
    };

    public abstract boolean recommendedTemperature(double temperature);
}
