
class Car {

    int yearModel;
    String make;
    int speed;
    
    public void accelerate() {
        this.speed = speed + 5;
    }
    
    public void brake() {
        if (this.speed > 4) {
            this.speed = speed - 5;
        } else {
            this.speed = 0;
        }
    }
}
