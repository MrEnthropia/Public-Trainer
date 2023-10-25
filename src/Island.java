public class Island {


    protected String name;
    protected int area;
    protected int quantity;

}


    class Ocean extends Island{

    }
    class Continent extends Island {

        String name = "Asia, Europe, N.America, S.America, Africa, Australia";
        int quantity = 6;

        protected String printNameContinent() {
            return name;
        }

        protected Integer printQuantity() {
            return quantity;
        }


    }
    class Planet extends Continent{

        String name="Earth";

        protected String printNamePlanet(){
            return this.name;
        }
    }


