package Recomendacion;

public enum ClimaPredominante {
    FRIO{
        @Override
        public boolean recomendadoTemperatura(double temperatura){
            return temperatura <= 16;
        }
    },
    NORMAL{
      @Override
      public boolean recomendadoTemperatura(double temepratura){
          return false;
      }
    },
    CALOR{
        @Override
        public boolean recomendadoTemperatura(double temperatura){
            return temperatura >= 24;
        }
    };

    public abstract boolean recomendadoTemperatura(double temperatura);
}
