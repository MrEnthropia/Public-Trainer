public class Wheel {

    boolean wheel1;
    int wheel1Condition;
    boolean wheel2;
    int wheel2Condition;
    boolean wheel3;
    int wheel3Condition;
    boolean wheel4;
    int wheel4Condition;

    public Wheel(boolean wheel1,
                 int wheel1Condition,
                 boolean wheel2,
                 int wheel2Condition,
                 boolean wheel3,
                 int wheel3Condition,
                 boolean wheel4,
                 int wheel4Condition){
        this.wheel1=wheel1;
        this.wheel2=wheel1;
        this.wheel3=wheel1;
        this.wheel4=wheel1;

        this.wheel1Condition=wheel1Condition;
        this.wheel2Condition=wheel2Condition;
        this.wheel3Condition=wheel3Condition;
        this.wheel4Condition=wheel4Condition;
    }


}
    class Engine extends Wheel{
         boolean start=false;
         int fuel=0;

        public Engine(boolean wheel1, int wheel1Condition, boolean wheel2, int wheel2Condition, boolean wheel3, int wheel3Condition, boolean wheel4, int wheel4Condition) {
            super(wheel1, wheel1Condition, wheel2, wheel2Condition, wheel3, wheel3Condition, wheel4, wheel4Condition);
        }

        public void startEngine(){
             if (fuel>0) {
                 this.start = true;
                 System.out.println("Вруууууууууум");
             }
         }
    
    }
    class Car extends Engine{

        String carBrand;

        public Car(String carBrand, boolean wheel1, int wheel1Condition, boolean wheel2, int wheel2Condition, boolean wheel3, int wheel3Condition, boolean wheel4, int wheel4Condition) {
            super(wheel1, wheel1Condition, wheel2, wheel2Condition, wheel3, wheel3Condition, wheel4, wheel4Condition);
            this.carBrand=carBrand;
        }

        public String getCarBrand() {
            return carBrand;
        }



        void replacementWheel(){
            super.wheel1=true;
            super.wheel2=true;
            super.wheel3=true;
            super.wheel4=true;

            super.wheel1Condition=100;
            super.wheel2Condition=100;
            super.wheel3Condition=100;
            super.wheel4Condition=100;

            System.out.println("Все колёса заменены");
        }
        void refill(){
            do{++fuel;

            } while (fuel!=50);
            System.out.println("Полный бак!");
        }



    }
